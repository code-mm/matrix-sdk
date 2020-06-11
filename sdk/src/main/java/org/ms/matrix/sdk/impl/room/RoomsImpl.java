package org.ms.matrix.sdk.impl.room;

import android.os.SystemClock;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ms.matrix.sdk.model.rooms.Candidates;
import org.ms.matrix.sdk.model.rooms.Content;
import org.ms.matrix.sdk.model.rooms.Event;
import org.ms.matrix.sdk.model.rooms.Offer;
import org.ms.matrix.sdk.model.SyncModel;
import org.ms.matrix.sdk.model.rooms.Unsigned;
import org.ms.matrix.sdk.net.RoomParticipation;
import org.ms.matrix.sdk.supper.Client;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.matrix.sdk.supper.inter.room.IRoom;
import org.ms.matrix.sdk.supper.inter.room.IRoomsAdapter;
import org.ms.matrix.sdk.utils.RetrofitUtils;
import org.ms.module.supper.client.Modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RoomsImpl extends IRoomsAdapter {


    private static final String TAG = "RoomsImpl";

    private Map<String, RoomImpl> roomsMap = new HashMap<>();


    private RoomParticipation roomParticipation = RetrofitUtils.getInstance().getRetrofitClient(Client.getConfig().getHomeServer()).create(RoomParticipation.class);

    @Override
    public void getRoom(String roomId, MatrixCallBack<IRoom, Throwable> callBack) {


        RoomImpl room = roomsMap.get(roomId);

        if (room != null) {
            if (callBack != null) {
                callBack.onSuccess(room);
            }
        } else {
            RoomImpl room1 = new RoomImpl();
            room1.setRoomId(roomId);
            roomsMap.put(roomId, room1);
            if (callBack != null) {
                callBack.onSuccess(room1);
            }
        }
    }

    @Override
    public void synn(final SyncModel syncModel) {


        roomParticipation._matrix_client_v0_sync(syncModel.getSince(), Client.getData().getUserData().getAccessToken())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            String body = responseBody.string();

                            Modules.getLogModule().json(TAG, body);

                            try {
                                JSONObject jsonObject = new JSONObject(body);

                                JSONObject account_data = jsonObject.getJSONObject("account_data");
                                account_data(account_data);

                                JSONObject to_device = jsonObject.getJSONObject("to_device");
                                to_device(to_device);

                                JSONObject device_lists = jsonObject.getJSONObject("device_lists");
                                device_lists(device_lists);

                                JSONObject presence = jsonObject.getJSONObject("presence");
                                presence(presence);

                                JSONObject rooms = jsonObject.getJSONObject("rooms");
                                rooms(rooms);

                                JSONObject groups = jsonObject.getJSONObject("groups");
                                groups(groups);

                                JSONObject device_one_time_keys_count = jsonObject.getJSONObject("device_one_time_keys_count");
                                device_one_time_keys_count(device_one_time_keys_count);

                                String next_batch = jsonObject.getString("next_batch");
                                Log.e(TAG, "onSuccess next_batch : " + next_batch);
                                syncModel.setSince(next_batch);
                                SystemClock.sleep(1000);
                                synn(syncModel);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Log.e(TAG, "onSuccess: " + body);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


    }

    private void device_one_time_keys_count(JSONObject device_one_time_keys_count) {


    }

    private void groups(JSONObject groups) {


    }

    private void presence(JSONObject presence) {


    }

    private void device_lists(JSONObject device_lists) {


    }

    private void to_device(JSONObject to_device) {


    }

    private void account_data(JSONObject account_data) throws JSONException {
        JSONArray account_data_events = account_data.getJSONArray("events");

        for (int i = 0; i < account_data.length(); i++) {

            JSONObject eventJSONObject = new JSONObject(account_data_events.get(i).toString());
            String type = eventJSONObject.getString("type");

            if ("m.direct".equals(type)) {

            } else if ("im.vector.setting.breadcrumbs".equals(type)) {

            } else if ("m.push_rules".equals(type)) {

            }
        }
    }

    private void rooms(JSONObject rooms) throws JSONException {
        JSONObject join = rooms.getJSONObject("join");

        Iterator<String> keys = join.keys();

        while (keys.hasNext()) {
            // 此节点为房间ID
            String next = keys.next();


            // 获取房间里面的内容
            JSONObject roomContent = join.getJSONObject(next);
            JSONObject timeline = roomContent.getJSONObject("timeline");

            JSONArray events = timeline.getJSONArray("events");

            for (int i = 0; i < events.length(); i++) {

                Event.EventBuilder eventBuilder = Event.builder();

                eventBuilder.roomId(next);


                JSONObject event = events.getJSONObject(i);
                // 事件类型
                String type = event.getString("type");

                eventBuilder.type(type);

                // 发送者
                String sender = event.getString("sender");

                eventBuilder.sender(sender);


                // 内容
                JSONObject content = event.getJSONObject("content");


                // 语音通话
                if ("m.call.invite".equals(type)) {

                    // 版本
                    int version = content.getInt("version");
                    // callid
                    String call_id = content.getString("call_id");

                    int lifetime = content.getInt("lifetime");

                    JSONObject offer = content.getJSONObject("offer");

                    //sdp
                    String sdp = offer.getString("sdp");

                    // type offer
                    String type1 = offer.getString("type");


                    eventBuilder.content(Content.builder().version(version).call_id(call_id).lifetime(lifetime).offer(Offer.builder().sdp(sdp).type(type1).build()).build());


                    // 文字消息
                } else if ("m.room.message".equals(type)) {

                    // 消息类型
                    String msgtype = content.getString("msgtype");

                    // 消息内容
                    String body1 = content.getString("body");

                    eventBuilder.content(Content.builder().body(body1).msgtype(msgtype).build());


                    Log.e(TAG, "onSuccess: 消息内容" + body1);
                } else if ("m.call.candidates".equals(type)) {

                    int version = content.getInt("version");

                    String call_id = content.getString("call_id");


                    JSONArray candidates = content.getJSONArray("candidates");


                    List<Candidates> candidatesList = new ArrayList<>();

                    for (int it = 0; it < candidates.length(); it++) {
                        JSONObject object = candidates.getJSONObject(it);
                        int sdpMLineIndex = object.getInt("sdpMLineIndex");
                        String sdpMid = object.getString("sdpMid");
                        String candidate = object.getString("candidate");

                        candidatesList.add(Candidates.builder().sdpMLineIndex(sdpMLineIndex).sdpMid(sdpMid).candidate(candidate).build());
                    }

                    eventBuilder.content(Content.builder().version(version).call_id(call_id).candidates(candidatesList).build());

                }


                long origin_server_ts = event.getLong("origin_server_ts");

                eventBuilder.origin_server_ts(origin_server_ts);

                JSONObject unsigned = event.getJSONObject("unsigned");


                int age = unsigned.getInt("age");

                eventBuilder.unsigned(Unsigned.builder().age(age).build());

                // 事件ID
                String event_id = event.getString("event_id");

                eventBuilder.event_id(event_id);

                if (Client.matrixListener != null) {
                    Client.matrixListener.onEvent(eventBuilder.build());
                }
            }
        }
    }
}

package org.ms.matrix.sdk.impl.room;

import android.os.SystemClock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.ms.matrix.sdk.model.event.Unsigned;
import org.ms.matrix.sdk.model.event.m_audio;
import org.ms.matrix.sdk.model.event.m_call_answer;
import org.ms.matrix.sdk.model.event.m_call_candidates;
import org.ms.matrix.sdk.model.event.m_call_invite;
import org.ms.matrix.sdk.model.event.m_image;
import org.ms.matrix.sdk.model.event.m_room_message;
import org.ms.matrix.sdk.model.event.m_text;
import org.ms.matrix.sdk.model.request.SyncRequest;
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
    public void synn(final SyncRequest syncRequest) {

        roomParticipation._matrix_client_v0_sync(syncRequest.getSince(), Client.getData().getUserData().getAccessToken())
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
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(body);

                                JsonAccountDataHandler.handler(jsonObject);
                                JsonRoomsHandler.handler(jsonObject);
                                JsontoDeviceHandler.handler(jsonObject);
                                JsonDeviceListsHandler.handler(jsonObject);
                                JsonpresenceHandler.handler(jsonObject);

                                JsonGroupsHandler.handler(jsonObject);
                                JSONObject rooms = jsonObject.getJSONObject("rooms");
                                rooms(rooms);

                                String next_batch = jsonObject.getString("next_batch");
                                syncRequest.setSince(next_batch);
                                SystemClock.sleep(1000);
                                synn(syncRequest);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }


    private void rooms(JSONObject rooms) throws JSONException {
        JSONObject join = rooms.getJSONObject("join");
        rooms_join(join);
        JSONObject invite = rooms.getJSONObject("invite");
        rooms_invite(invite);
        JSONObject leave = rooms.getJSONObject("leave");
        rooms_leave(leave);

    }

    private void rooms_invite(JSONObject invite) {


    }

    private void rooms_leave(JSONObject leave) {

    }

    private void rooms_join(JSONObject join) throws JSONException {
        Iterator<String> keys = join.keys();

        while (keys.hasNext()) {
            // 此节点为房间ID
            String roomId = keys.next();

            // 获取房间里面的内容
            JSONObject roomContent = join.getJSONObject(roomId);
            JSONObject timeline = roomContent.getJSONObject("timeline");
            rooms_join_timeline(timeline, roomId);


            JSONObject state = roomContent.getJSONObject("state");
            rooms_join_state(state, roomId);


            JSONObject account_data = roomContent.getJSONObject("account_data");
            rooms_join_account_data(account_data, roomId);

            JSONObject ephemeral = roomContent.getJSONObject("ephemeral");
            rooms_join_ephemeral(ephemeral, roomId);

            JSONObject unread_notifications = roomContent.getJSONObject("unread_notifications");
            rooms_join_unread_notifications(unread_notifications, roomId);

            JSONObject summary = roomContent.getJSONObject("summary");
            rooms_join_summary(summary, roomId);


        }
    }

    private void rooms_join_summary(JSONObject summary, String roomId) {


    }

    private void rooms_join_unread_notifications(JSONObject unread_notifications, String roomId) {
    }

    private void rooms_join_ephemeral(JSONObject ephemeral, String roomId) {


    }

    private void rooms_join_account_data(JSONObject account_data, String roomId) {

    }

    private void rooms_join_state(JSONObject state, String roomId) {

    }

    private void rooms_join_timeline(JSONObject timeline, String roomId) throws JSONException {

        boolean limited = timeline.getBoolean("limited");
        String prev_batch = timeline.getString("prev_batch");
        JSONArray events = timeline.getJSONArray("events");

        for (int i = 0; i < events.length(); i++) {


            JSONObject event = events.getJSONObject(i);
            // 事件类型
            String type = event.getString("type");
            // 发送者
            String sender = event.getString("sender");
            // 内容
            JSONObject content = event.getJSONObject("content");
            long origin_server_ts = event.getLong("origin_server_ts");
            JSONObject unsigned = event.getJSONObject("unsigned");
            int age = unsigned.getInt("age");
            // 事件ID
            String event_id = event.getString("event_id");

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

                m_call_invite invite =
                        m_call_invite
                                .builder()
                                .room_id(roomId)
                                .type(type)
                                .sender(sender)
                                .origin_server_ts(origin_server_ts)
                                .event_id(event_id)
                                .unsigned(Unsigned
                                        .builder()
                                        .age(age)
                                        .build())
                                .content(m_call_invite.Invtie
                                        .builder()
                                        .call_id(call_id)
                                        .version(version)
                                        .lifetime(lifetime)
                                        .offer(m_call_invite.Invtie.Offer
                                                .builder()
                                                .sdp(sdp)
                                                .type(type1)
                                                .build())
                                        .build())
                                .build();

                Client.onCallBackEvent(invite);


                // 消息
            } else if ("m.room.message".equals(type)) {
                // 消息类型
                String msgtype = content.getString("msgtype");
                String body = content.getString("body");
                // 文字消息
                if ("m.text".equals(msgtype)) {
                    // 消息内容

                    m_room_message<m_text> m_textm_room_message = new m_room_message<m_text>();
                    m_textm_room_message.setRoom_id(roomId);
                    m_textm_room_message.setEvent_id(event_id);
                    m_textm_room_message.setSender(sender);
                    m_textm_room_message.setOrigin_server_ts(origin_server_ts);
                    m_textm_room_message.setContent(m_text.builder().body(body).msgtype(msgtype).build());
                    m_textm_room_message.setUnsigned(Unsigned.builder().age(age).build());
                    m_textm_room_message.setType(type);
                    Client.onCallBackEvent(m_textm_room_message);

                } else if ("m.emote".equals(msgtype)) {

                } else if ("m.notice".equals(msgtype)) {

                } else if ("m.image".equals(msgtype)) {

                    JSONObject info = content.getJSONObject("info");
                    int w = info.getInt("w");
                    int h = info.getInt("h");
                    int size = info.getInt("size");
                    String mimetype = info.getString("mimetype");
                    String thumbnail_url = info.getString("thumbnail_url");
                    JSONObject thumbnail_info = info.getJSONObject("thumbnail_info");
                    int w1 = thumbnail_info.getInt("w");
                    int h1 = thumbnail_info.getInt("h");
                    int size1 = thumbnail_info.getInt("size");
                    String mimetype1 = thumbnail_info.getString("mimetype");
                    m_room_message<m_image> m_imagem_room_message = new m_room_message<>();

                    m_imagem_room_message.setRoom_id(roomId);
                    m_imagem_room_message.setType(type);
                    m_imagem_room_message.setOrigin_server_ts(origin_server_ts);
                    m_imagem_room_message.setSender(sender);
                    m_imagem_room_message.setEvent_id(event_id);
                    m_imagem_room_message.setUnsigned(Unsigned.builder().age(age).build());
                    m_imagem_room_message.setContent(m_image.builder()
                            .body(body)
                            .info(m_image.Info.builder().w(w).h(h).size(size).mimetype(mimetype)
                                    .thumbnail_url(thumbnail_url)
                                    .thumbnail_info(m_image.ThumbnailInfo.builder()
                                            .w(w1)
                                            .h(h1)
                                            .size(size1)
                                            .mimetype(mimetype1)
                                            .build())
                                    .build())
                            .msgtype(msgtype)
                            .url(thumbnail_url)
                            .build());

                    Client.onCallBackEvent(m_imagem_room_message);


                } else if ("m.file".equals(msgtype)) {

                } else if ("m.audio".equals(msgtype)) {

                    String url = content.getString("url");

                    JSONObject info = content.getJSONObject("info");

                    String mimetype = info.getString("mimetype");
                    int size = info.getInt("size");

                    m_room_message<m_audio> m_audiom_room_message = new m_room_message<>();

                    m_audiom_room_message.setSender(sender);
                    m_audiom_room_message.setType(type);
                    m_audiom_room_message.setEvent_id(event_id);
                    m_audiom_room_message.setOrigin_server_ts(origin_server_ts);
                    m_audiom_room_message.setRoom_id(roomId);
                    m_audiom_room_message.setUnsigned(Unsigned.builder().age(age).build());
                    m_audiom_room_message.setContent(m_audio.builder()
                            .body(body)
                            .msgtype(msgtype)
                            .url(url)
                            .info(m_audio.Info.builder()
                                    .duration(0)
                                    .mimetype(mimetype)
                                    .size(size)
                                    .build())
                            .build());

                    Client.onCallBackEvent(m_audiom_room_message);

                } else if ("m.location".equals(msgtype)) {

                } else if ("m.video".equals(msgtype)) {

                }

            } else if ("m.call.candidates".equals(type)) {
                int version = content.getInt("version");
                String call_id = content.getString("call_id");
                JSONArray candidates = content.getJSONArray("candidates");

                List<m_call_candidates.Candidate> candidateList = new ArrayList<>();
                for (int it = 0; it < candidates.length(); it++) {
                    JSONObject object = candidates.getJSONObject(it);
                    int sdpMLineIndex = object.getInt("sdpMLineIndex");
                    String sdpMid = object.getString("sdpMid");
                    String candidate = object.getString("candidate");

                    candidateList.add(m_call_candidates.Candidate.builder()
                            .sdpMid(sdpMid)
                            .sdpMLineIndex(sdpMLineIndex)
                            .candidate(candidate)
                            .build());
                }


                m_call_candidates build =
                        m_call_candidates.builder()
                                .room_id(roomId)
                                .event_id(event_id)
                                .sender(sender)
                                .origin_server_ts(origin_server_ts)
                                .type(type)
                                .unsigned(Unsigned.builder()
                                        .age(age)
                                        .build())
                                .content(m_call_candidates.Candidates
                                        .builder()
                                        .call_id(call_id)
                                        .version(version)
                                        .candidates(candidateList)
                                        .build()).build();

                Client.onCallBackEvent(build);

            } else if ("m.call.answer".equals(type)) {

                int version = content.getInt("version");
                String call_id = content.getString("call_id");
                int lifetime = content.getInt("lifetime");


                JSONObject answer = content.getJSONObject("answer");

                String sdp = answer.getString("sdp");
                String type1 = answer.getString("type");


                m_call_answer build = m_call_answer.builder()
                        .unsigned(Unsigned.builder().age(age).build())
                        .room_id(roomId)
                        .event_id(event_id)
                        .sender(sender)
                        .origin_server_ts(origin_server_ts)
                        .type(type)
                        .content(m_call_answer.Content.builder()
                                .version(version)
                                .lifetime(lifetime)
                                .call_id(call_id)
                                .answer(
                                        m_call_answer.Answer.builder()
                                                .sdp(sdp)
                                                .type(type1)
                                                .build()
                                )
                                .build())

                        .build();

                Client.onCallBackEvent(build);
            }
        }
    }
}

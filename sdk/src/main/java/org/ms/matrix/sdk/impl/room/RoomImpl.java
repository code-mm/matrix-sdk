package org.ms.matrix.sdk.impl.room;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.matrix.sdk.model.MessageModel;
import org.ms.matrix.sdk.model.RoomJoinedUserInfo;
import org.ms.matrix.sdk.model.event.m_call_answer;
import org.ms.matrix.sdk.model.event.m_call_candidates;
import org.ms.matrix.sdk.model.event.m_call_invite;
import org.ms.matrix.sdk.model.event.m_text;
import org.ms.matrix.sdk.model.response.EventIdResponse;
import org.ms.matrix.sdk.net.RoomDirectory;
import org.ms.matrix.sdk.net.RoomParticipation;
import org.ms.matrix.sdk.supper.Client;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.matrix.sdk.supper.inter.room.IRoomAdapter;
import org.ms.matrix.sdk.utils.RetrofitUtils;
import org.ms.module.supper.client.Modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class RoomImpl extends IRoomAdapter {

    private static final String TAG = "RoomImpl";

    private RoomParticipation roomParticipation = RetrofitUtils.getInstance().getRetrofitClient(Client.getConfig().getHomeServer()).create(RoomParticipation.class);

    private RoomDirectory roomDirectory = RetrofitUtils.getInstance().getRetrofitClient(Client.getConfig().getHomeServer()).create(RoomDirectory.class);


    private String roomId;

    private String displayName;

    private String avatarUrl;


    public String getDisplayName() {
        return displayName;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }


    @Override
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public void send(MessageModel messageModel, final MatrixCallBack callBack) {
        Log.e(TAG, "send: " + messageModel.getContent().toJson());
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), messageModel.getContent().toJson());

        roomParticipation._matrix_client_r0_rooms_roomId_send_eventType_txnId(roomId, messageModel.getEventType(), messageModel.getContent().eventId(), Client.getData().getUserData().getAccessToken(), requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            String body = responseBody.string();
                            Log.e(TAG, "onSuccess: " + body);

                            EventIdResponse eventIdResponse = Modules.getUtilsModule().getGsonUtils().fromJson(body, EventIdResponse.class);

                            if (callBack != null) {
                                callBack.onSuccess(eventIdResponse);
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

    @Override
    public void getJoinedMembers(MatrixCallBack<List<RoomJoinedUserInfo>, Throwable> callBack) {

        roomParticipation._matrix_client_r0_rooms_roomId_joined_members(roomId, Client.getData().getUserData().getAccessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {

                        String body = null;
                        try {
                            body = responseBody.string();
                            Log.e(TAG, "onSuccess: " + body);

                            List<RoomJoinedUserInfo> roomJoinedUserInfoList = new ArrayList<>();
                            roomJoinedUserInfoList.clear();
                            try {
                                JSONObject jsonObject = new JSONObject(body);
                                JSONObject joined = jsonObject.getJSONObject("joined");
                                Iterator<String> keys = joined.keys();
                                while (keys.hasNext()) {
                                    // 用户ID
                                    String next = keys.next();

                                    JSONObject jsonObject1 = joined.getJSONObject(next);
                                    // 头像
                                    String avatar_url = jsonObject1.getString("avatar_url");
                                    // 显示名称
                                    String display_name = jsonObject1.getString("display_name");
                                    roomJoinedUserInfoList.add(RoomJoinedUserInfo.builder().room_id(roomId).avatar_url(avatar_url).display_name(display_name).build());
                                }
                                if (callBack != null) {
                                    callBack.onSuccess(roomJoinedUserInfoList);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                if (callBack != null) {
                                    callBack.onFailure(e);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (callBack != null) {
                                callBack.onFailure(e);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (callBack != null) {
                            callBack.onFailure(e);
                        }
                    }
                });
    }


    @Override
    public void getRoomAliases(MatrixCallBack callBack) {
        roomDirectory._matrix_client_r0_rooms_roomId_aliases(roomId, Client.getData().getUserData().getAccessToken())

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        try {
                            String body = responseBody.string();
                            Log.e(TAG, "onSuccess: getRoomAliases body " + body);


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


    @Override
    public void sendText(m_text param, MatrixCallBack callBack) {
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param.toJson());

        roomParticipation._matrix_client_r0_rooms_roomId_send_eventType_txnId(roomId, "m.room.message", Modules.getUtilsModule().getMd5Utils().md5(param.toJson() + System.currentTimeMillis()), Client.getData().getUserData().getAccessToken(), requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {

                        try {
                            Log.e(TAG, "onSuccess: " + responseBody.string());
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

    @Override
    public void sendCallInvite(m_call_invite.Invtie param, MatrixCallBack callBack) {
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param.toJson());

        roomParticipation._matrix_client_r0_rooms_roomId_send_eventType_txnId(roomId, "m.call.invite", Modules.getUtilsModule().getMd5Utils().md5(param.toJson()), Client.getData().getUserData().getAccessToken(), requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {

                        try {
                            Log.e(TAG, "onSuccess: sendCallInvite  " + responseBody.string());
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

    @Override
    public void sendCallCandidates(m_call_candidates.Candidates param, MatrixCallBack callBack) {
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param.toJson());

        roomParticipation._matrix_client_r0_rooms_roomId_send_eventType_txnId(roomId, "m.call.candidates", Modules.getUtilsModule().getMd5Utils().md5(param.toJson()), Client.getData().getUserData().getAccessToken(), requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {

                        try {
                            Log.e(TAG, "onSuccess: sendCallCandidates " + responseBody.string());
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


    @Override
    public void sendCallAnswer(m_call_answer.Content param, MatrixCallBack callBack) {

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param.toJson());

        roomParticipation._matrix_client_r0_rooms_roomId_send_eventType_txnId(roomId, "m.call.answer", Modules.getUtilsModule().getMd5Utils().md5(param.toJson()), Client.getData().getUserData().getAccessToken(), requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {

                        try {
                            Log.e(TAG, "onSuccess: sendCallAnswer " + responseBody.string());
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
}

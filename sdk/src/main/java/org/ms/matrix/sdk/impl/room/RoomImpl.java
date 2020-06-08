package org.ms.matrix.sdk.impl.room;

import android.util.Log;

import org.ms.matrix.sdk.model.MessageModel;
import org.ms.matrix.sdk.model.response.EventIdResponse;
import org.ms.matrix.sdk.net.RoomParticipation;
import org.ms.matrix.sdk.supper.Client;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.matrix.sdk.supper.inter.room.IRoomAdapter;
import org.ms.matrix.sdk.utils.RetrofitUtils;
import org.ms.module.supper.client.Modules;

import java.io.IOException;

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


    private String roomId;

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

        roomParticipation._matrix_client_r0_rooms_roomId_send_eventType_txnId_(roomId, messageModel.getEventType(), messageModel.getContent().eventId(), Client.getData().getUserData().getAccessToken(), requestBody)
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
}

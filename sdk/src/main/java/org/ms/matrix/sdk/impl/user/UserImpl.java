package org.ms.matrix.sdk.impl.user;

import android.util.Log;


import org.ms.matrix.sdk.exception.MatrixException;
import org.ms.matrix.sdk.model.params.LoginParam;
import org.ms.matrix.sdk.model.LoginResultModel;
import org.ms.matrix.sdk.model.params.RegisteredParam;
import org.ms.matrix.sdk.model.response.RoomsResponse;
import org.ms.matrix.sdk.net.RoomMembership;
import org.ms.matrix.sdk.net.SessionManagement;
import org.ms.matrix.sdk.supper.Client;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.matrix.sdk.supper.inter.user.IUserAdapter;
import org.ms.matrix.sdk.utils.RetrofitUtils;
import org.ms.module.supper.client.Modules;

import java.io.IOException;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UserImpl extends IUserAdapter {


    private static final String TAG = "UserImpl";

    SessionManagement sessionManagement;
    RoomMembership roomMembership;


    private void checkServer() throws MatrixException {

        if (Client.getConfig().getHomeServer() == null || "".equals(Client.getConfig().getHomeServer())) {
            throw new MatrixException("未配置服务器");
        }

        if (sessionManagement == null) {
            sessionManagement = RetrofitUtils.getInstance().getRetrofitClient(Client.getConfig().getHomeServer()).create(SessionManagement.class);
        }

        if (roomMembership == null) {
            roomMembership = RetrofitUtils.getInstance().getRetrofitClient(Client.getConfig().getHomeServer()).create(RoomMembership.class);
        }

        if (sessionManagement == null) {
            throw new MatrixException("未配置服务器");
        }

        if (roomMembership == null) {
            throw new MatrixException("未配置服务器");
        }

    }


    @Override
    public void login(LoginParam loginParam, final MatrixCallBack<LoginResultModel, Throwable> callBack) {


        try {
            checkServer();
        } catch (MatrixException e) {
            e.printStackTrace();
        }

        Log.e(TAG, "login: " + loginParam.toJson());

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), loginParam.toJson());

        sessionManagement._matrix_client_r0_login(requestBody)
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
                            LoginResultModel loginResultModel = Modules.getUtilsModule().getGsonUtils().fromJson(body, LoginResultModel.class);

                            Client.getData().getUserData().setAccessToken(loginResultModel.getAccess_token());

                            if (callBack != null) {
                                callBack.onSuccess(loginResultModel);
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
                        if (callBack != null) {
                            callBack.onFailure(e);
                        }
                    }
                });

    }


    @Override
    public void registered(RegisteredParam registeredParam, MatrixCallBack callBack) {


        try {
            checkServer();
        } catch (MatrixException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getRoomList(final MatrixCallBack<List<String>, Throwable> callBack) {

        try {
            checkServer();
        } catch (MatrixException e) {
            e.printStackTrace();
        }

        roomMembership._matrix_client_r0_joined_rooms(Client.getData().getUserData().getAccessToken())
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
                            RoomsResponse roomsResponse = Modules.getUtilsModule().getGsonUtils().fromJson(body, RoomsResponse.class);

                            if (callBack != null) {
                                callBack.onSuccess(roomsResponse.getJoined_rooms());
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
                        if (callBack != null) {
                            callBack.onFailure(e);
                        }
                    }
                });
    }
}

package org.ms.matrix.sdk.impl.user;

import android.util.Log;

import org.ms.matrix.sdk.model.LoginModel;
import org.ms.matrix.sdk.model.LoginResultModel;
import org.ms.matrix.sdk.model.RegisteredModel;
import org.ms.matrix.sdk.net.SessionManagement;
import org.ms.matrix.sdk.supper.Client;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.matrix.sdk.supper.inter.user.IUserAdapter;
import org.ms.matrix.sdk.utils.RetrofitUtils;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.matrix.ISessionManagement;

import java.io.IOException;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class UserImpl extends IUserAdapter {


    private static final String TAG = "UserImpl";

    SessionManagement sessionManagement = RetrofitUtils.getInstance().getRetrofitClient(Client.getConfig().getHomeServer()).create(SessionManagement.class);


    @Override
    public void login(LoginModel loginModel, final MatrixCallBack<LoginResultModel, Throwable> callBack) {


        Log.e(TAG, "login: " + loginModel.toJson());

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), loginModel.toJson());

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
    public void registered(RegisteredModel registeredModel, MatrixCallBack callBack) {

    }
}

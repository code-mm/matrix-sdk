package org.ms.matrix.app.ui.activity.login;

import android.util.Log;

import org.ms.matrix.app.App;
import org.ms.matrix.app.db.user.User;
import org.ms.matrix.app.db.user.UserDbInjection;
import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.matrix.sdk.model.params.LoginParam;
import org.ms.matrix.sdk.model.LoginResultModel;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.module.base.module.BaseModel;
import org.ms.module.supper.client.Modules;

import java.util.Date;

public class LoginActivityModel extends BaseModel<LoginActivityPresenter> implements ILoginActivity {


    private static final String TAG = "LoginActivityModel";

    public LoginActivityModel(LoginActivityPresenter presenter) {
        super(presenter);
    }

    @Override
    public void queryLatestUser() {


        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                User user = UserDbInjection.providerUserDataSource().queryLatestUser();
                queryLatestUserCallBack(user);
            }
        });

    }

    @Override
    public void queryLatestUserCallBack(User user) {

        presenter.queryLatestUserCallBack(user);
    }

    @Override
    public void login(String username, String password) {
        Log.e(TAG, "login: " + username);


        LoginParam loginParam = LoginParam.builder().user(username).password(password).type("m.login.password").build();


        MatrixClient.getInstance().getUser().login(loginParam, new MatrixCallBack<LoginResultModel, Throwable>() {
            @Override
            public void onSuccess(LoginResultModel loginResultModel) {
                String access_token = loginResultModel.getAccess_token();
                String device_id = loginResultModel.getDevice_id();
                String home_server = loginResultModel.getHome_server();
                String user_id = loginResultModel.getUser_id();


                App.userId=user_id;
                Log.e(TAG, "onSuccess: " + access_token);
                Log.e(TAG, "onSuccess: " + device_id);
                Log.e(TAG, "onSuccess: " + home_server);
                Log.e(TAG, "onSuccess: " + user_id);




                User user = User.builder()._device_id(device_id)._username(username)._password(password)._user_id(user_id)._timestamp(new Date().getTime())._access_token(access_token)._home_server(MatrixClient.getInstance().getConfig().getHomeServer()).build();

                UserDbInjection.providerUserDataSource().insert(user);

                onLoginCallBack();

            }

            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();

            }
        });
    }

    @Override
    public void onLoginCallBack() {
        presenter.onLoginCallBack();
    }
}

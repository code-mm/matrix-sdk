package org.ms.matrix.app.ui.activity.login;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.ms.matrix.app.db.User;
import org.ms.module.base.presenter.BasePresenter;
import org.ms.module.supper.client.Modules;

@RequiresApi(api = Build.VERSION_CODES.N)
public class LoginActivityPresenter extends BasePresenter<LoginActivityModel, LoginActivity> implements ILoginActivity {
    public LoginActivityPresenter(LoginActivity view) {
        super(view);
    }

    @Override
    protected LoginActivityModel initModel() {
        return new LoginActivityModel(this);
    }

    @Override
    public void onDestory() {

    }

    @Override
    public void warning(String text) {

    }

    @Override
    public void queryLatestUser() {

        model.queryLatestUser();

    }

    @Override
    public void queryLatestUserCallBack(User user) {
        view.queryLatestUserCallBack(user);
    }

    @Override
    public void login(String username, String password) {
        if (username == null || "".equals(username)) {
            view.showToast("请输入账号");
            view.hideDialog();
            return;
        }

        // 数字开头
        if (Modules.getUtilsModule().getRegexUtils().isStartWithNumber(username)) {
            // 判断是不是 手机号
            if (!Modules.getUtilsModule().getRegexUtils().isMobileSimple(username)) {
                view.hideDialog();
                view.showToast("请输入正确的手机号");
                return;
            }
        } else {
            if (username.length() < 6) {
                view.hideDialog();
                view.showToast("请输入正确的用户名");
                return;
            }

            if (!Modules.getUtilsModule().getRegexUtils().isUsername(username)) {
                view.hideDialog();
                view.showToast("请输入正确的用户名");
                return;
            }
        }


        if (password == null || "".equals(password)) {
            view.showToast("请输入密码");
            view.hideDialog();
            return;
        }

        if (password.length() < 6) {
            view.showToast("请输入正确的密码");
            view.hideDialog();
            return;
        }

        model.login(username, password);
    }


    @Override
    public void onLoginCallBack() {
        view.onLoginCallBack();
    }
}

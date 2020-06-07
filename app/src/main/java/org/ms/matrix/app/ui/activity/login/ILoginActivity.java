package org.ms.matrix.app.ui.activity.login;

import org.ms.matrix.app.db.User;
import org.ms.module.base.inter.IView;

public interface ILoginActivity extends IView {


    void queryLatestUser();

    void queryLatestUserCallBack(User user);

    void login(String username, String password);

    void onLoginCallBack();

}

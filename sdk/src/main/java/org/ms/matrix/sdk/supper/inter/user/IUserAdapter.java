package org.ms.matrix.sdk.supper.inter.user;

import org.ms.matrix.sdk.model.LoginModel;
import org.ms.matrix.sdk.model.LoginResultModel;
import org.ms.matrix.sdk.model.RegisteredModel;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;

import java.util.List;

public class IUserAdapter implements IUser {

    @Override
    public void login(LoginModel loginModel, MatrixCallBack<LoginResultModel, Throwable> callBack) {

    }

    @Override
    public void registered(RegisteredModel registeredModel, MatrixCallBack callBack) {

    }

    @Override
    public void getRoomList(MatrixCallBack<List<String>, Throwable> callBack) {

    }
}

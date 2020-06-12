package org.ms.matrix.sdk.supper.inter.user;

import org.ms.matrix.sdk.model.params.LoginParam;
import org.ms.matrix.sdk.model.LoginResultModel;
import org.ms.matrix.sdk.model.params.RegisteredParam;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;

import java.util.List;

public class IUserAdapter implements IUser {

    @Override
    public void login(LoginParam loginParam, MatrixCallBack<LoginResultModel, Throwable> callBack) {

    }

    @Override
    public void registered(RegisteredParam registeredParam, MatrixCallBack callBack) {

    }

    @Override
    public void getRoomList(MatrixCallBack<List<String>, Throwable> callBack) {

    }
}

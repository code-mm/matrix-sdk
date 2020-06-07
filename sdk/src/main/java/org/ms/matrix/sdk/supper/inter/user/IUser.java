package org.ms.matrix.sdk.supper.inter.user;

import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.matrix.sdk.model.LoginModel;
import org.ms.matrix.sdk.model.LoginResultModel;
import org.ms.matrix.sdk.model.RegisteredModel;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;

import java.util.List;

public interface IUser {


    /**
     * @param loginModel
     * @param callBack
     */

    void login(LoginModel loginModel, MatrixCallBack<LoginResultModel, Throwable> callBack);


    /**
     * 注册
     *
     * @param registeredModel
     * @param callBack
     */
    void registered(RegisteredModel registeredModel, MatrixCallBack callBack);


    void getRoomList(MatrixCallBack<List<String>,Throwable> callBack);


}

package org.ms.matrix.sdk.supper.inter.user;

import org.ms.matrix.sdk.model.params.LoginParam;
import org.ms.matrix.sdk.model.LoginResultModel;
import org.ms.matrix.sdk.model.params.RegisteredParam;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;

import java.util.List;

public interface IUser {


    /**
     * @param loginParam
     * @param callBack
     */

    void login(LoginParam loginParam, MatrixCallBack<LoginResultModel, Throwable> callBack);


    /**
     * 注册
     *
     * @param registeredParam
     * @param callBack
     */
    void registered(RegisteredParam registeredParam, MatrixCallBack callBack);


    void getRoomList(MatrixCallBack<List<String>, Throwable> callBack);


}

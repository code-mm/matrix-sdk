package org.ms.matrix.sdk.supper.inter.room;

import org.ms.matrix.sdk.model.MessageModel;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;

public interface IRoom {



    String getRoomId();



    void send(MessageModel messageModel, MatrixCallBack callBack);


}

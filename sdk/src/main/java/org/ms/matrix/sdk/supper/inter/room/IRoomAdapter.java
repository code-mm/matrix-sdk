package org.ms.matrix.sdk.supper.inter.room;

import org.ms.matrix.sdk.model.MessageModel;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;

public class IRoomAdapter implements IRoom {
    @Override
    public String getRoomId() {
        return null;
    }

    @Override
    public void send(MessageModel messageModel, MatrixCallBack callBack) {

    }
}

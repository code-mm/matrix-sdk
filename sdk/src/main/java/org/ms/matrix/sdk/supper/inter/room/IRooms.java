package org.ms.matrix.sdk.supper.inter.room;

import org.ms.matrix.sdk.model.SyncModel;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;

public interface IRooms {


    /**
     * 开始同步
     *
     * @param syncModel
     */
    void synn(SyncModel syncModel);


    void getRoom(String roomId, MatrixCallBack<IRoom, Throwable> callBack);


}

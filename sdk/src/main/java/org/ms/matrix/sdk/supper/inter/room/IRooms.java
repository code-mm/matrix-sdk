package org.ms.matrix.sdk.supper.inter.room;

import org.ms.matrix.sdk.model.request.SyncRequest;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;

public interface IRooms {


    /**
     * 开始同步
     *
     * @param syncRequest
     */
    void synn(SyncRequest syncRequest);


    void getRoom(String roomId, MatrixCallBack<IRoom, Throwable> callBack);


}

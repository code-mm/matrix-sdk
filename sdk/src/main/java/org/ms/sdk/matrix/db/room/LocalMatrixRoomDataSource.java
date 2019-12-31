package org.ms.sdk.matrix.db.room;

import android.arch.lifecycle.LiveData;

import org.ms.module.supper.client.Modules;

import java.util.List;

public class LocalMatrixRoomDataSource implements MatrixRoomDataSource {


    private static final String TAG = "LocalMatrixRoomDataSour";

    private MatrixRoomDao matrixRoomDao;

    public LocalMatrixRoomDataSource(MatrixRoomDao matrixRoomDao) {
        this.matrixRoomDao = matrixRoomDao;
    }


    @Override
    public List<MatrixRoom> getRooms() {
        return matrixRoomDao.getRooms();
    }

    @Override
    public LiveData<MatrixRoom> getLast() {
        return matrixRoomDao.getLast();
    }

    @Override
    public MatrixRoom roomIdByRoom(String roomId) {
        return matrixRoomDao.roomIdByRoom(roomId);
    }

    @Override
    public void insert(MatrixRoom... rooms) {


        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                for(MatrixRoom it:rooms)
                {
                    if(roomIdByRoom(it.roomId)==null)
                    {
                        Modules.getLogModule().e(TAG,"房间不存在，存入");
                        matrixRoomDao.insert(it);
                    }else{
                        Modules.getLogModule().e(TAG,"房间已存在，");
                    }
                }
            }
        });
    }

    @Override
    public void delete(MatrixRoom... rooms) {

        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                matrixRoomDao.delete(rooms);
            }
        });
    }
}

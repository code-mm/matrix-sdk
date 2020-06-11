package org.ms.matrix.app.db.matrixroom;

import androidx.lifecycle.LiveData;

import org.ms.module.supper.client.Modules;

import java.util.List;

public class MatrixRoomLocalDataSource implements MatrixRoomDataSource {


    private MatrixRoomDao dao;


    public MatrixRoomLocalDataSource(MatrixRoomDao dao) {
        this.dao = dao;
    }

    @Override
    public void insert(MatrixRoom... rooms) {

        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                dao.insert(rooms);

            }
        });

    }

    @Override
    public void delete(MatrixRoom... rooms) {
        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                dao.delete(rooms);

            }
        });
    }

    @Override
    public void update(MatrixRoom... rooms) {
        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                dao.update(rooms);
            }
        });
    }

    @Override
    public MatrixRoom queryMatrixRoomByRoomId(String roomId) {
        return dao.queryMatrixRoomByRoomId(roomId);
    }

    @Override
    public LiveData<MatrixRoom> queryMatrixRoomLatest() {
        return dao.queryMatrixRoomLatest();
    }

    @Override
    public LiveData<List<MatrixRoom>> queryMatrixRooms() {
        return dao.queryMatrixRooms();
    }
}

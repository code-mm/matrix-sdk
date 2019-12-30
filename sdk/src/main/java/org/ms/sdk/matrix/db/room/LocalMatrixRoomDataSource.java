package org.ms.sdk.matrix.db.room;

import java.util.List;

public class LocalMatrixRoomDataSource implements MatrixRoomDataSource {

    private MatrixRoomDao matrixRoomDao;

    public LocalMatrixRoomDataSource(MatrixRoomDao matrixRoomDao) {
        this.matrixRoomDao = matrixRoomDao;
    }


    @Override
    public List<MatrixRoom> getRooms() {
        return matrixRoomDao.getRooms();
    }

    @Override
    public void insert(MatrixRoom... rooms) {
        matrixRoomDao.insert(rooms);
    }

    @Override
    public void delete(MatrixRoom... rooms) {
        matrixRoomDao.delete(rooms);
    }
}

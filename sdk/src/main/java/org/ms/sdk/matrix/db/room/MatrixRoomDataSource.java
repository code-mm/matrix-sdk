package org.ms.sdk.matrix.db.room;

import java.util.List;

public interface MatrixRoomDataSource {

    List<MatrixRoom> getRooms();

    void insert(MatrixRoom... rooms);

    void delete(MatrixRoom... rooms);
}

package org.ms.sdk.matrix.db.room;

import java.util.List;

public interface MatrixRoomDataSource {

    List<MatrixRoom> getRooms();

    MatrixRoom roomIdByRoom(String roomId);

    void insert(MatrixRoom... rooms);

    void delete(MatrixRoom... rooms);
}

package org.ms.sdk.matrix.db.room;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface MatrixRoomDataSource {

    List<MatrixRoom> getRooms();

    LiveData<MatrixRoom> getLast();

    MatrixRoom roomIdByRoom(String roomId);

    void insert(MatrixRoom... rooms);

    void delete(MatrixRoom... rooms);
}

package org.ms.sdk.matrix.db.room;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MatrixRoomDao {
    @Query("SELECT * FROM _matrix_room")
    List<MatrixRoom> getRooms();


    @Query("SELECT * FROM _matrix_room WHERE _roomid = (:roomId)")
    MatrixRoom roomIdByRoom(String roomId);

    @Insert
    void insert(MatrixRoom... rooms);

    @Delete
    void delete(MatrixRoom... rooms);
}

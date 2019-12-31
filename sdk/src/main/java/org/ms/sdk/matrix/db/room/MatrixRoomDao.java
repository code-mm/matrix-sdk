package org.ms.sdk.matrix.db.room;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MatrixRoomDao {
    // 查询所有的房间
    @Query("SELECT * FROM _matrix_room")
    List<MatrixRoom> getRooms();

    @Query("SELECT * FROM _matrix_room ORDER BY _id DESC LIMIT 1")
    LiveData<MatrixRoom> getLast();

    // 更具房间ID查询房间
    @Query("SELECT * FROM _matrix_room WHERE _roomid = (:roomId)")
    MatrixRoom roomIdByRoom(String roomId);
    // 插入房间
    @Insert
    void insert(MatrixRoom... rooms);
    // 删除房间
    @Delete
    void delete(MatrixRoom... rooms);
}

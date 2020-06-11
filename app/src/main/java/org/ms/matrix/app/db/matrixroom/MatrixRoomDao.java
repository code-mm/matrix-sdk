package org.ms.matrix.app.db.matrixroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MatrixRoomDao {


    @Insert
    void insert(MatrixRoom... rooms);

    @Delete
    void delete(MatrixRoom... rooms);

    @Update
    void update(MatrixRoom... rooms);


    @Query("SELECT * FROM _matrix_room WHERE _room_id=:roomId")
    MatrixRoom queryMatrixRoomByRoomId(String roomId);

    @Query("SELECT * FROM _matrix_room ORDER BY _timestamp DESC LIMIT 0,1")
    LiveData<MatrixRoom> queryMatrixRoomLatest();

    @Query("SELECT * FROM _matrix_room")
    LiveData<List<MatrixRoom>> queryMatrixRooms();
}

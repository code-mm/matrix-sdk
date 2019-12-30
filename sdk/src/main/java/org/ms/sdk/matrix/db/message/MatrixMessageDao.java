package org.ms.sdk.matrix.db.message;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
@Dao
public interface MatrixMessageDao {
    @Query("SELECT * FROM _matrix_message")
    List<MatrixMessage> getMessages();
    @Query("SELECT * FROM _matrix_message WHERE _eventid = (:eventId)")
    MatrixMessage eventIdByMessage(String eventId);
    @Insert
    void insert(MatrixMessage... matrixMessages);
    @Delete
    void delete(MatrixMessage... matrixMessages);
}
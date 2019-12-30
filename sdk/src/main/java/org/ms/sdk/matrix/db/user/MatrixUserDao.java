package org.ms.sdk.matrix.db.user;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface MatrixUserDao {
    @Query("SELECT * FROM _matrix_user ORDER BY _id DESC LIMIT 1")
    LiveData<MatrixUser> getLast();


    @Query("SELECT * FROM _matrix_user WHERE _userid = (:userId)")
    MatrixUser userIdByUser(String userId);

    @Insert
    void insert(MatrixUser... matrixUsers);

    @Delete
    void delete(MatrixUser... matrixUsers);
}

package org.ms.sdk.matrix.db.user;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface MatrixUserDao {
    // 观察用户数据变化
    @Query("SELECT * FROM _matrix_user ORDER BY _id DESC LIMIT 1")
    LiveData<MatrixUser> getLast();
    // 查询用户数据
    @Query("SELECT * FROM _matrix_user WHERE _userid = (:userId)")
    MatrixUser userIdByUser(String userId);
    // 插入用户数据
    @Insert
    void insert(MatrixUser... matrixUsers);
    // 删除用户
    @Delete
    void delete(MatrixUser... matrixUsers);
}

package org.ms.matrix.app.db.user;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {


    @Query("SELECT * FROM _matrix_user")
    List<User> query();

    @Query("SELECT * FROM _matrix_user ORDER BY _timestamp DESC LIMIT 0,1")
    User queryLatestUser();


    @Insert
    void insert(User... users);

    @Delete
    void delete(User... users);

    @Update
    void update(User... users);
}

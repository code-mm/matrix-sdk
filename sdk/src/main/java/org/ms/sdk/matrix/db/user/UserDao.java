package org.ms.sdk.matrix.db.user;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM _user ORDER BY _id DESC LIMIT 1")
    LiveData<User> getLast();

    @Insert
    void insert(User... users);

    @Delete
    void delete(User... users);
}

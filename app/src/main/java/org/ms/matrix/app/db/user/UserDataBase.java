package org.ms.matrix.app.db.user;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.ms.module.supper.client.Modules;

@Database(entities = {
        User.class
}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile UserDataBase INSTANCE;

    public static UserDataBase getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(Modules.getDataModule().getAppData().getApplication(),
                            UserDataBase.class, "com-bdlbsc-matrix-user.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
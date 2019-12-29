package org.ms.sdk.matrix.db.user;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import org.ms.module.supper.client.Modules;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile UserDatabase INSTANCE;

    public static UserDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(Modules.getDataModule().getApplication(),
                            UserDatabase.class, "_user.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
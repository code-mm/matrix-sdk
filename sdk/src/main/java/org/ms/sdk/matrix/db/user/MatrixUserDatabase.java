package org.ms.sdk.matrix.db.user;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import org.ms.module.supper.client.Modules;

@Database(entities = {MatrixUser.class}, version = 1, exportSchema = false)
public abstract class MatrixUserDatabase extends RoomDatabase {

    public abstract MatrixUserDao dao();

    private static volatile MatrixUserDatabase INSTANCE;

    public static MatrixUserDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (MatrixUserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(Modules.getDataModule().getAppData().getApplication(),
                            MatrixUserDatabase.class, "_matrix_user.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
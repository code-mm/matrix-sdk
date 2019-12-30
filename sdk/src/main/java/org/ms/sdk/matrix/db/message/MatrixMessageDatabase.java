package org.ms.sdk.matrix.db.message;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import org.ms.module.supper.client.Modules;

@Database(entities = {MatrixMessage.class}, version = 1, exportSchema = false)
public abstract class MatrixMessageDatabase extends RoomDatabase {

    public abstract MatrixMessageDao dao();

    private static volatile MatrixMessageDatabase INSTANCE;

    public static MatrixMessageDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (MatrixMessageDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(Modules.getDataModule().getApplication(),
                            MatrixMessageDatabase.class, "_matrix_message.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
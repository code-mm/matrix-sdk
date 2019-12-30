package org.ms.sdk.matrix.db.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import org.ms.module.supper.client.Modules;
import org.ms.sdk.matrix.db.message.MatrixMessage;
import org.ms.sdk.matrix.db.message.MatrixMessageDao;
import org.ms.sdk.matrix.db.message.MatrixMessageDatabase;

@Database(entities = {MatrixRoom.class}, version = 1, exportSchema = false)
public abstract class MatrixRoomDatabase extends RoomDatabase {

    public abstract MatrixRoomDao dao();

    private static volatile MatrixRoomDatabase INSTANCE;

    public static MatrixRoomDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (MatrixRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(Modules.getDataModule().getApplication(),
                            MatrixRoomDatabase.class, "_matrix_room.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
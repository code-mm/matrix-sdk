package org.ms.matrix.app.db.messagelist;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.ms.module.supper.client.Modules;

@Database(entities = {
        MessageList.class
}, version = 1, exportSchema = false)
public abstract class MatrixDatabase extends RoomDatabase {

    public abstract MessageListDao messageListDao();

    private static volatile MatrixDatabase INSTANCE;

    public static MatrixDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (MatrixDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(Modules.getDataModule().getAppData().getApplication(),
                            MatrixDatabase.class, "matrix.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
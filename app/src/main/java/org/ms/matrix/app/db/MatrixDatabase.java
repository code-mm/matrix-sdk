package org.ms.matrix.app.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import org.ms.matrix.app.App;
import org.ms.matrix.app.db.matrixroom.MatrixRoom;
import org.ms.matrix.app.db.matrixroom.MatrixRoomDao;
import org.ms.matrix.app.db.event.Event;
import org.ms.matrix.app.db.event.EventDao;
import org.ms.matrix.sdk.supper.Client;
import org.ms.module.supper.client.Modules;

@Database(entities = {
        Event.class,
        MatrixRoom.class
}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class MatrixDatabase extends RoomDatabase {

    public abstract EventDao messageListDao();
    public abstract MatrixRoomDao matrixRoomDao();

    private static volatile MatrixDatabase INSTANCE;

    public static MatrixDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (MatrixDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(Modules.getDataModule().getAppData().getApplication(),
                            MatrixDatabase.class, "matrix"+   App.userId+".db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
package org.ms.matrix.app.db;

import org.ms.matrix.app.db.matrixroom.MatrixRoomLocalDataSource;
import org.ms.matrix.app.db.messagelist.EventDataSource;
import org.ms.matrix.app.db.messagelist.EventLocalDataSource;

public class MatrixDbInjection {

    public static EventDataSource providerEventDataSource() {
        MatrixDatabase database = MatrixDatabase.getInstance();
        return new EventLocalDataSource(database.messageListDao());
    }

    public static MatrixRoomLocalDataSource providerMatrixRoomLocalDataSource() {
        MatrixDatabase database = MatrixDatabase.getInstance();
        return new MatrixRoomLocalDataSource(database.matrixRoomDao());
    }
}

package org.ms.matrix.app.db;

import org.ms.matrix.app.db.matrixroom.MatrixRoomLocalDataSource;
import org.ms.matrix.app.db.event.EventDataSource;
import org.ms.matrix.app.db.event.EventLocalDataSource;
import org.ms.matrix.sdk.supper.Client;

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

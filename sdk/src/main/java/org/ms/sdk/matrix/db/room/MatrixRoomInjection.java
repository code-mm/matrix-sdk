package org.ms.sdk.matrix.db.room;

import org.ms.sdk.matrix.db.message.LocalMatrixMessageDataSource;
import org.ms.sdk.matrix.db.message.MatrixMessageDataSource;
import org.ms.sdk.matrix.db.message.MatrixMessageDatabase;

public class MatrixRoomInjection {

    /**
     * 数据
     *
     * @return
     */
    public static MatrixRoomDataSource provideDataDataSource() {
        MatrixRoomDatabase database = MatrixRoomDatabase.getInstance();
        return new LocalMatrixRoomDataSource(database.dao());
    }

}
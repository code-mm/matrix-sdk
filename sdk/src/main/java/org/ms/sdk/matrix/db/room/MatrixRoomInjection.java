package org.ms.sdk.matrix.db.room;

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
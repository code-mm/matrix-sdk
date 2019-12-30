package org.ms.sdk.matrix.db.message;

public class MatrixMessageInjection {

    /**
     * 数据
     *
     * @return
     */
    public static MatrixMessageDataSource provideDataDataSource() {
        MatrixMessageDatabase database = MatrixMessageDatabase.getInstance();
        return new LocalMatrixMessageDataSource(database.dao());
    }

}
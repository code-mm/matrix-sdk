package org.ms.sdk.matrix.db.user;

public class MatrixUserInjection {

    /**
     * 数据
     *
     * @return
     */
    public static MatrixUserDataSource provideDataDataSource() {
        MatrixUserDatabase database = MatrixUserDatabase.getInstance();
        return new LocalMatrixMatrixUserDataSource(database.dao());
    }

}
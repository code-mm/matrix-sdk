package org.ms.sdk.matrix.db.message;

import java.util.List;

public class LocalMatrixMessageDataSource implements MatrixMessageDataSource {

    private MatrixMessageDao matrixMessageDao;

    public LocalMatrixMessageDataSource(MatrixMessageDao matrixMessageDao) {
        this.matrixMessageDao = matrixMessageDao;
    }


    @Override
    public List<MatrixMessage> getMessages() {
        return matrixMessageDao.getMessages();
    }

    @Override
    public void insert(MatrixMessage... matrixMessages) {

        matrixMessageDao.insert(matrixMessages);

    }

    @Override
    public void delete(MatrixMessage... matrixMessages) {
        matrixMessageDao.delete(matrixMessages);
    }
}

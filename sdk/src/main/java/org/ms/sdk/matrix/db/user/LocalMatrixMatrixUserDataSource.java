package org.ms.sdk.matrix.db.user;

import android.arch.lifecycle.LiveData;

public class LocalMatrixMatrixUserDataSource implements MatrixUserDataSource {


    private MatrixUserDao matrixUserDao;

    public LocalMatrixMatrixUserDataSource(MatrixUserDao matrixUserDao) {
        this.matrixUserDao = matrixUserDao;
    }

    @Override
    public LiveData<MatrixUser> getLastUser() {
        return matrixUserDao.getLast();
    }

    @Override
    public void insert(MatrixUser... matrixUsers) {
        matrixUserDao.insert(matrixUsers);
    }

    @Override
    public void delete(MatrixUser... matrixUsers) {

        matrixUserDao.delete(matrixUsers);

    }
}

package org.ms.sdk.matrix.db.user;

import android.arch.lifecycle.LiveData;

import org.ms.module.supper.client.Modules;

public class LocalMatrixMatrixUserDataSource implements MatrixUserDataSource {
    private static final String TAG = "LocalMatrixMatrixUserDa";
    private MatrixUserDao matrixUserDao;

    public LocalMatrixMatrixUserDataSource(MatrixUserDao matrixUserDao) {
        this.matrixUserDao = matrixUserDao;
    }

    @Override
    public LiveData<MatrixUser> getLastUser() {
        return matrixUserDao.getLast();
    }

    @Override
    public MatrixUser userIdByUser(String userId) {
        return matrixUserDao.userIdByUser(userId);
    }

    @Override
    public void insert(MatrixUser... matrixUsers) {

        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {

                for (MatrixUser it : matrixUsers) {
                    if (userIdByUser(it.userId) == null) {
                        Modules.getLogModule().e(TAG,"用户不存在");
                        matrixUserDao.insert(it);
                    }else{
                        Modules.getLogModule().e(TAG,"用户存在");
                    }
                }
            }
        });
    }

    @Override
    public void delete(MatrixUser... matrixUsers) {

        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                matrixUserDao.delete(matrixUsers);
            }
        });
    }
}
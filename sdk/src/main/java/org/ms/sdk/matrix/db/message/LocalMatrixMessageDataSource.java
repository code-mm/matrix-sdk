package org.ms.sdk.matrix.db.message;

import org.ms.module.supper.client.Modules;

import java.util.List;

public class LocalMatrixMessageDataSource implements MatrixMessageDataSource {


    private static final String TAG = "LocalMatrixMessageDataS";

    private MatrixMessageDao matrixMessageDao;

    public LocalMatrixMessageDataSource(MatrixMessageDao matrixMessageDao) {
        this.matrixMessageDao = matrixMessageDao;
    }


    @Override
    public List<MatrixMessage> getMessages() {
        return matrixMessageDao.getMessages();
    }

    @Override
    public MatrixMessage eventIdByMessage(String eventId) {
        return matrixMessageDao.eventIdByMessage(eventId);
    }

    @Override
    public void insert(MatrixMessage... matrixMessages) {
        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {

                for (MatrixMessage it : matrixMessages) {

                    if (it != null) {

                        if (eventIdByMessage(it.eventId) == null) {
                            Modules.getLogModule().e(TAG, "消息不存在，存入" + it.eventId);
                            matrixMessageDao.insert(matrixMessages);
                        } else {
                            Modules.getLogModule().e(TAG, "消息已存在，" + it.getMessage());

                        }
                    } else {
                        Modules.getLogModule().e(TAG, "消息为NULL，");
                    }

                }
            }
        });
    }

    @Override
    public void delete(MatrixMessage... matrixMessages) {
        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                matrixMessageDao.delete(matrixMessages);
            }
        });
    }
}
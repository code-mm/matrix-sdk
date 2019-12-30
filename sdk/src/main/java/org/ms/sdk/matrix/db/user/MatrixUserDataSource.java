package org.ms.sdk.matrix.db.user;

import android.arch.lifecycle.LiveData;

public interface MatrixUserDataSource {


    LiveData<MatrixUser> getLastUser();

    MatrixUser userIdByUser(String userId);

    void insert(MatrixUser... data);

    void delete(MatrixUser... data);

}

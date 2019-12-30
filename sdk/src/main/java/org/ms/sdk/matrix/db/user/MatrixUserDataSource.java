package org.ms.sdk.matrix.db.user;

import android.arch.lifecycle.LiveData;

public interface MatrixUserDataSource {



    LiveData<MatrixUser> getLastUser();

    void insert(MatrixUser... data);

    void delete(MatrixUser... data);


}

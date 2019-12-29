package org.ms.sdk.matrix.db.user;

import android.arch.lifecycle.LiveData;

public interface UserDataSource {



    LiveData<User> getLastUser();

    void insert(User... data);

    void delete(User... data);


}

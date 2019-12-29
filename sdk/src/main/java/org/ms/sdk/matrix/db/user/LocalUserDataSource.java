package org.ms.sdk.matrix.db.user;

import android.arch.lifecycle.LiveData;

public class LocalUserDataSource  implements UserDataSource{


    private UserDao userDao;

    public LocalUserDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public LiveData<User> getLastUser() {
        return userDao.getLast();
    }

    @Override
    public void insert(User... users) {

        userDao.insert(users);

    }

    @Override
    public void delete(User... users) {

        userDao.delete(users);

    }
}

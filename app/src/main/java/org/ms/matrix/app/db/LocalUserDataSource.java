package org.ms.matrix.app.db;

import org.ms.module.supper.client.Modules;

import java.util.List;

public class LocalUserDataSource implements UserDataSource {


    UserDao userDao;

    public LocalUserDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> query() {
        return userDao.query();
    }

    @Override
    public User queryLatestUser() {
        return userDao.queryLatestUser();
    }

    @Override
    public void insert(User... users) {

        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                userDao.insert(users);
            }
        });

    }

    @Override
    public void delete(User... users) {
        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                userDao.delete(users);
            }
        });

    }

    @Override
    public void update(User... users) {


        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                userDao.update(users);
            }
        });
    }
}

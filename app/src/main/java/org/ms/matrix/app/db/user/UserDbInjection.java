package org.ms.matrix.app.db.user;




// 用户数据库
public class UserDbInjection {

    public static UserDataSource providerUserDataSource() {
        UserDataBase database = UserDataBase.getInstance();
        return new LocalUserDataSource(database.userDao());
    }
}
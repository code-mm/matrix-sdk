package org.ms.sdk.matrix.db.user;
public class Injection {

    /**
     * 数据
     *
     * @return
     */
    public static UserDataSource provideDataDataSource() {
        UserDatabase database = UserDatabase.getInstance();
        return new LocalUserDataSource(database.userDao());
    }

}
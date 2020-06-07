package org.ms.matrix.sdk.impl.data;

import org.ms.matrix.sdk.supper.inter.data.IUserData;
import org.ms.matrix.sdk.supper.inter.user.IUser;

public class UserDataImpl implements IUserData {

    private static final UserDataImpl instance = new UserDataImpl();

    public static UserDataImpl getInstance() {
        return instance;
    }

    private String accessToken;

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }
}

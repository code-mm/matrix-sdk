package org.ms.matrix.sdk.impl.data;

import org.ms.matrix.sdk.supper.inter.data.IUserData;

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


    private String userId;

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserId() {
        return userId;
    }


    private String deviceId;

    @Override
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }
}

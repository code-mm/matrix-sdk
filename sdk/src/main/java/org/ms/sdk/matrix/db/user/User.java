package org.ms.sdk.matrix.db.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "_user")
public class User {
    @PrimaryKey
    int _id;
    @ColumnInfo(name = "_username")
    String username;
    @ColumnInfo(name = "_password")
    String password;
    @ColumnInfo(name = "_baseurl")
    String baseUrl;
    @ColumnInfo(name = "_userid")
    String userId;
    @ColumnInfo(name = "_lasterdatetime")
    long lasterDateTime;
    @ColumnInfo(name = "device_id")
    String deviceId;
    @ColumnInfo(name = "home_server")
    String homeServer;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getLasterDateTime() {
        return lasterDateTime;
    }

    public void setLasterDateTime(long lasterDateTime) {
        this.lasterDateTime = lasterDateTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHomeServer() {
        return homeServer;
    }

    public void setHomeServer(String homeServer) {
        this.homeServer = homeServer;
    }
}
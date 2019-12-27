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


}
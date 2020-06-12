package org.ms.matrix.app.db.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "_matrix_user")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int _id;
    @ColumnInfo(name = "_username")
    public String _username;
    @ColumnInfo(name = "_password")
    public String _password;

    @ColumnInfo(name = "_user_id")
    public String _user_id;

    @ColumnInfo(name = "_home_server")
    public String _home_server;
    @ColumnInfo(name = "_access_token")
    public String _access_token;
    @ColumnInfo(name = "_device_id")
    public String _device_id;
    @ColumnInfo(name = "_timestamp")
    public long  _timestamp;


}

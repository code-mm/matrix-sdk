package org.ms.sdk.matrix.db.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "_matrix_room")
public class MatrixRoom {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    int id;
    @ColumnInfo(name = "_roomid")
    String roomId;

    @ColumnInfo(name = "home_server")
    String homeServer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHomeServer() {
        return homeServer;
    }

    public void setHomeServer(String homeServer) {
        this.homeServer = homeServer;
    }
}

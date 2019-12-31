package org.ms.sdk.matrix.db.message;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "_matrix_message")
public class MatrixMessage {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    int id;
    @ColumnInfo(name = "_roomid")
    String roomId;
    @ColumnInfo(name = "_message")
    String message;
    @ColumnInfo(name = "_message_type")
    String messageType;
    @ColumnInfo(name = "_homeserver")
    String homeServer;
    @ColumnInfo(name = "_sender")
    String sender;
    @ColumnInfo(name = "_date")
    long date;
    @ColumnInfo(name = "device_id")
    String deviceId;
    @ColumnInfo(name = "_eventid")
    String eventId;
    @ColumnInfo(name = "_origin_server_ts")
    long originServerTs;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getHomeServer() {
        return homeServer;
    }

    public void setHomeServer(String homeServer) {
        this.homeServer = homeServer;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }


    public long getOriginServerTs() {
        return originServerTs;
    }

    public void setOriginServerTs(long originServerTs) {
        this.originServerTs = originServerTs;
    }
}

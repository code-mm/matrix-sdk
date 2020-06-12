package org.ms.matrix.app.db.event;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigInteger;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity(tableName = "_event_list")
public class Event {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    @ColumnInfo(name = "_room_id")
    private String _room_id;

    @ColumnInfo(name = "_type")
    private String _type;

    @ColumnInfo(name = "_event_id")
    private String _event_id;

    // 消息发送者
    @ColumnInfo(name = "_sender")
    private String _sender;

    @ColumnInfo(name = "_origin_server_ts")
    private long _origin_server_ts;

    // 存入时间戳
    @ColumnInfo(name = "_timestamp")
    private long _timestamp;


    @ColumnInfo(name = "_content")
    private String _content;

    @ColumnInfo(name = "_unsigned")
    private String _unsigned;


    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

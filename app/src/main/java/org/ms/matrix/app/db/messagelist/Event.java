package org.ms.matrix.app.db.messagelist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
    public int _id;

    @ColumnInfo(name = "_room_id")
    public String _room_id;

    @ColumnInfo(name = "_message_type")
    public String _message_type;

    @ColumnInfo(name = "_message_content")
    public String _message_content;

    @ColumnInfo(name = "_event_id")
    public String _event_id;

    // 消息发送者
    @ColumnInfo(name = "_sender")
    public String _sender;


    @ColumnInfo(name = "_origin_server_ts")
    public long _origin_server_ts;

    // 存入时间戳
    @ColumnInfo(name = "_timestamp")
    public long _timestamp;
}

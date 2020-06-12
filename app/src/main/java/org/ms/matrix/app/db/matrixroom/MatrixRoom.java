package org.ms.matrix.app.db.matrixroom;

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

@Entity(tableName = "_matrix_room")
public class MatrixRoom {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int _id;

    @ColumnInfo(name = "_room_name")
    public String _room_name;

    @ColumnInfo(name = "_room_id")
    public String _room_id;

    @ColumnInfo(name = "_room_avatar")
    public String _room_avatar;

    // 存入时间戳
    @ColumnInfo(name = "_timestamp")
    public long _timestamp;
}

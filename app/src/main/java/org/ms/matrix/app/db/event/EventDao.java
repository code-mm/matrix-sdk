package org.ms.matrix.app.db.event;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {


    @Query("SELECT * FROM _event_list WHERE _event_id=:eventId")
    Event messageListByEventId(String eventId);

    @Query("SELECT * FROM _event_list ")
    LiveData<List<Event>> liveDataMessage();

    @Query("SELECT * FROM _event_list ORDER BY _timestamp DESC LIMIT 0,1")
    LiveData<Event> liveDataLatestMessage();

    @Query("SELECT * FROM _event_list")
    List<Event> queryMessageList();

    @Query("SELECT * FROM _EVENT_LIST WHERE _room_id=:roomId")
    LiveData<List<Event>> liveDataEventByRoomId(String roomId);


    @Query("SELECT * FROM _event_list WHERE _type=:type")
    List<Event> queryEventByType(String type);


    @Query("SELECT * FROM _event_list WHERE _room_id=:roomId AND _type=:type")
    List<Event> queryEventByRoomIdAndType(String roomId, String type);

    @Query("SELECT * FROM _event_list WHERE _type=:type")
    LiveData<List<Event>> liveDataEventByType(String type);


    @Insert
    void insert(Event... event);

    @Update
    void update(Event... event);

    @Delete
    void delete(Event... event);


}

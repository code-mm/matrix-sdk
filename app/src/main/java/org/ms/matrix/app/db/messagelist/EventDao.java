package org.ms.matrix.app.db.messagelist;

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

    @Insert
    void insert(Event... event);

    @Update
    void update(Event... event);

    @Delete
    void delete(Event... event);




}

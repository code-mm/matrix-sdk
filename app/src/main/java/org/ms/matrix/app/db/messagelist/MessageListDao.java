package org.ms.matrix.app.db.messagelist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MessageListDao {

    @Query("SELECT * FROM _message_list ")
    LiveData<List<MessageList>> liveDataMessage();

    @Query("SELECT * FROM _message_list ORDER BY _timestamp DESC LIMIT 0,1")
    LiveData<MessageList> liveDataLatestMessage();

    @Query("SELECT * FROM _message_list")
    List<MessageList> queryMessageList();

    @Insert
    void insert(MessageList... messageList);

    @Update
    void update(MessageList... messageList);

    @Delete
    void delete(MessageList... messageList);




}

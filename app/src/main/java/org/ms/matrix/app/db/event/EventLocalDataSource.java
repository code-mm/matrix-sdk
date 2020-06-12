package org.ms.matrix.app.db.event;

import android.util.Log;

import androidx.lifecycle.LiveData;

import org.ms.module.supper.client.Modules;

import java.util.List;

public class EventLocalDataSource implements EventDataSource {


    private static final String TAG = "EventLocalDataSource";
    private EventDao dao;

    public EventLocalDataSource(EventDao dao) {
        this.dao = dao;
    }

    @Override
    public Event messageListByEventId(String eventId) {
        return dao.messageListByEventId(eventId);
    }

    @Override
    public LiveData<List<Event>> liveDataMessage() {
        return dao.liveDataMessage();
    }

    @Override
    public LiveData<Event> liveDataLatestMessage() {
        return dao.liveDataLatestMessage();
    }

    @Override
    public List<Event> queryMessageList() {
        return dao.queryMessageList();
    }

    @Override
    public void insert(Event... event) {
        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                for (Event it : event) {
                    Event event1 = messageListByEventId(it.get_event_id());
                    if (event1 == null) {
                        Log.e(TAG, "run: 不存在插入");
                        dao.insert(event);
                    } else {
                        Log.e(TAG, "run: 存在更新");
                        int id = event1.get_id();
                        event1 = it;
                        event1.set_id(id);
                        dao.update(event1);
                    }
                }
            }
        });
    }

    @Override
    public void update(Event... event) {


        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                dao.update(event);
            }
        });

    }

    @Override
    public void delete(Event... event) {
        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                dao.delete(event);
            }
        });

    }
}

package org.ms.matrix.app.db.messagelist;

import androidx.lifecycle.LiveData;

import org.ms.module.supper.client.Modules;

import java.util.List;

public class MessageListLocalDataSource implements MessageListDataSource {


    private MessageListDao dao;

    public MessageListLocalDataSource(MessageListDao dao) {
        this.dao = dao;
    }

    @Override
    public LiveData<List<MessageList>> liveDataMessage() {
        return dao.liveDataMessage();
    }

    @Override
    public LiveData<MessageList> liveDataLatestMessage() {
        return dao.liveDataLatestMessage();
    }

    @Override
    public List<MessageList> queryMessageList() {
        return dao.queryMessageList();
    }

    @Override
    public void insert(MessageList... messageList) {
        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                dao.insert(messageList);
            }
        });
    }

    @Override
    public void update(MessageList... messageList) {


        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                dao.update(messageList);
            }
        });

    }

    @Override
    public void delete(MessageList... messageList) {
        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {
                dao.delete(messageList);
            }
        });

    }
}

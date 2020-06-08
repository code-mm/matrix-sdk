package org.ms.matrix.app.db.messagelist;

public class MatrixDbInjection {

    public static MessageListDataSource providerCacheDataSource() {
        MatrixDatabase database = MatrixDatabase.getInstance();
        return new MessageListLocalDataSource(database.messageListDao());
    }
}

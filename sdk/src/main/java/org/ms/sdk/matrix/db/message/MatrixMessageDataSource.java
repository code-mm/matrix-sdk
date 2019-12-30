package org.ms.sdk.matrix.db.message;

import java.util.List;

public interface MatrixMessageDataSource {

    List<MatrixMessage> getMessages();

    MatrixMessage eventIdByMessage(String eventId);

    void insert(MatrixMessage... data);

    void delete(MatrixMessage... data);
}

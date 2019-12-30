package org.ms.sdk.matrix.db.message;

import java.util.List;

public interface MatrixMessageDataSource {

    List<MatrixMessage> getMessages();

    void insert(MatrixMessage... data);

    void delete(MatrixMessage... data);
}

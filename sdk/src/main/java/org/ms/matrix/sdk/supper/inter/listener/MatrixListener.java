package org.ms.matrix.sdk.supper.inter.listener;

import org.ms.matrix.sdk.model.rooms.Event;

public interface MatrixListener {

    void onEvent(Event event);

}

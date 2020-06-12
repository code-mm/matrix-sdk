package org.ms.matrix.sdk.supper.inter.listener;


import org.ms.matrix.sdk.model.event.IEvent;

public interface MatrixListener {

    void onEvent(IEvent event);

}

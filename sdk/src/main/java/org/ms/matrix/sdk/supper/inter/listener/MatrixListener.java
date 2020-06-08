package org.ms.matrix.sdk.supper.inter.listener;

import org.ms.matrix.sdk.model.rooms.Event;

public interface MatrixListener {

    void onEvent(Event event);

    void onRooms();

    void onPresence();

    void onAccountData();

    void onToDevice();

    void onDeviceLists();

    void onDeviceOneTimekeysCount();

}

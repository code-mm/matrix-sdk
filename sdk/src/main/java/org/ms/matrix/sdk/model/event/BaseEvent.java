package org.ms.matrix.sdk.model.event;

import org.ms.module.supper.client.Modules;

public class  BaseEvent  implements IEvent{


    @Override
    public String getType() {
        return null;
    }

    @Override
    public String toJson() {
        return Modules.getUtilsModule().getGsonUtils().toJson(this);
    }
}

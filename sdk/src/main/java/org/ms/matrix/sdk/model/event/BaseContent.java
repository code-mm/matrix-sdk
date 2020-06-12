package org.ms.matrix.sdk.model.event;

import org.ms.module.supper.client.Modules;

public class BaseContent implements IContent {

    @Override
    public String getMsgtype() {
        return null;
    }

    @Override
    public String toJsom() {
        return Modules.getUtilsModule().getGsonUtils().toJson(this);
    }
}

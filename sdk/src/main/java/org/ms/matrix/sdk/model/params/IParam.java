package org.ms.matrix.sdk.model.params;

import android.os.SystemClock;

import org.ms.module.supper.client.Modules;

public class IParam {

    public String toJson() {
        return Modules.getUtilsModule().getGsonUtils().toJson(this);
    }

    public String eventId() {
        return Modules.getUtilsModule().getMd5Utils().md5(toJson() + System.currentTimeMillis());
    }
}

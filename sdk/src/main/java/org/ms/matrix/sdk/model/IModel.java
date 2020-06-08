package org.ms.matrix.sdk.model;

import org.ms.module.supper.client.Modules;


/**
 * module 基类
 */
public class IModel {

    public String toJson() {
        return Modules.getUtilsModule().getGsonUtils().toJson(this);
    }

    public String eventId() {
        return Modules.getUtilsModule().getMd5Utils().md5(toJson());
    }
}

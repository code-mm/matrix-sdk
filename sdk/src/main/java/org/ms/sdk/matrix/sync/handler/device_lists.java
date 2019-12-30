package org.ms.sdk.matrix.sync.handler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ms.module.supper.client.Modules;

public class device_lists {
    public device_lists(JSONObject device_lists) {
        try {
            JSONArray changed = device_lists.getJSONArray("changed");
            for (int i = 0; i < changed.length(); i++) {
                //@maohuawei:mhw828.com
                String string = changed.getString(i);
            }

            JSONArray left = device_lists.getJSONArray("left");


        } catch (Exception e) {
            Modules.getExceptionModule().printStackTrace(e);
        }
    }
}
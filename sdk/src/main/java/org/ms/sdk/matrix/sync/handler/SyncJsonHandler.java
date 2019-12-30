package org.ms.sdk.matrix.sync.handler;

import org.json.JSONObject;
import org.ms.module.supper.client.Modules;

// 同步数据处理
public class SyncJsonHandler {

    public account_data account_data;
    public to_device to_device;
    public presence presence;
    public rooms rooms;
    public groups groups;
    public device_one_time_keys_count device_one_time_keys_count;
    public next_batch next_batch;

    public SyncJsonHandler(JSONObject jsonObject) {
        try {
            account_data = new account_data(jsonObject.getJSONObject("account_data"));
            to_device = new to_device(jsonObject.getJSONObject("to_device"));
            presence = new presence(jsonObject.getJSONObject("presence"));
            rooms = new rooms(jsonObject.getJSONObject("rooms"));
            groups = new groups(jsonObject.getJSONObject("groups"));
            device_one_time_keys_count = new device_one_time_keys_count(jsonObject.getJSONObject("device_one_time_keys_count"));
            next_batch = new next_batch(jsonObject.getString("next_batch"));
        } catch (Exception e) {
            Modules.getExceptionModule().printStackTrace(e);
        }
    }
}

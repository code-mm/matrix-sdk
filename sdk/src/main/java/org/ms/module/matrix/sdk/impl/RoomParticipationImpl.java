package org.ms.module.matrix.sdk.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.common.ICallBack;
import org.ms.module.supper.inter.matrix.IRoomParticipation;

public class RoomParticipationImpl implements IRoomParticipation {


    private static final String TAG = "RoomParticipationImpl";


    @Override
    public void sync(JSONObject jsonObject, ICallBack iCallBack) {
        try {
            String access_token = jsonObject.getString("access_token");
            String filter = jsonObject.getString("filter");
            String since = jsonObject.getString("since");
            boolean full_state = jsonObject.getBoolean("full_state");
            String set_presence = jsonObject.getString("set_presence");
            int timeout = jsonObject.getInt("timeout");
            String homeServer = Modules.getDataModule().getMatrixData().getHomeServer();
            Modules.getLogModule().d(TAG, " homeServer " + homeServer);
            String url = homeServer + "_matrix/client/r0/sync?access_token=" + access_token + "&filter=" + filter + "&since=" + since + "&full_state=" + full_state + "&set_presence=" + set_presence + "&timeout=" + timeout;
            Modules.getRequestModule().get(null, url, iCallBack);
        } catch (Exception e) {
            Modules.getExceptionModule().printStackTrace(e);
        }
    }

    //POST /{userId}/
    @Override
    public void userIdByFilter(JSONObject jsonObject, ICallBack iCallBack) {
        try {
            String body = jsonObject.getString("body");
            String homeServer = Modules.getDataModule().getMatrixData().getHomeServer();
            Modules.getLogModule().d(TAG, " homeServer " + homeServer);
            String url = homeServer + "_matrix/client/r0/user/" + Modules.getDataModule().getMatrixData().getUserId() + "/filter?access_token=" + Modules.getDataModule().getMatrixData().getAccessToken();
            Modules.getRequestModule().requestBody(null, url, body, iCallBack);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void userIdAndFilterByFilter(JSONObject jsonObject, ICallBack iCallBack) {

    }
}

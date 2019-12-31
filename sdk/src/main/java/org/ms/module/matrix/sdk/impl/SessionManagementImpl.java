package org.ms.module.matrix.sdk.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.common.ICallBack;
import org.ms.module.supper.inter.matrix.ISessionManagement;

import java.util.HashMap;
import java.util.Map;

public class SessionManagementImpl implements ISessionManagement {
    @Override
    public void getLoginType(ICallBack iCallBack) {
        String url = Modules.getDataModule().getMatrixData().getHomeServer()+"_matrix/client/r0/login";
        Modules.getRequestModule().get(null,url,iCallBack);
    }

    @Override
    public void login(JSONObject jsonObject, ICallBack iCallBack) {
        Map<String, String> headers = new HashMap<>();
        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        try {
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            obj1.put("type", "m.id.user");
            obj1.put("user", username);
            obj2.put("identifier", obj1);
            obj2.put("initial_device_display_name", "");
            obj2.put("password", password);
            obj2.put("type", "m.login.password");

        } catch (Exception e) {
            Modules.getExceptionModule().printStackTrace(e);
        }
        String bodyString = obj2.toString();
        Modules.getRequestModule().requestBody(headers, Modules.getDataModule().getMatrixData().getHomeServer() + "_matrix/client/r0/login", bodyString, iCallBack);
    }
}

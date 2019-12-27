package org.ms.sdk.matrix;

import org.json.JSONObject;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.common.CallBack;
import org.ms.module.supper.inter.module.Module;
import org.ms.sdk.matrix.MatrixCallBack;

import java.util.HashMap;
import java.util.Map;

public class MatrixRequest {

    private static final MatrixRequest request = new MatrixRequest();

    public static MatrixRequest getInstance() {
        return request;
    }

    private static String BASE_URL = "https://www.mhw828.com/";

    public void setBaseUrl(String url) {
        BASE_URL = url;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }


    // 获取登录类型
    public void getLoginType(MatrixCallBack callBack) {
        Modules.getRequestModule().get(null, BASE_URL + "_matrix/client/r0/login", callBack);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param callBack
     */
    public void login(String username, String password, final MatrixCallBack callBack) {
        Map<String, String> headers = new HashMap<>();
        JSONObject body = new JSONObject();
        try {
            body.put("username", username);
            body.put("password", password);
            body.put("auth", "m.login.password");
        } catch (Exception e) {
            Modules.getExceptionModule().printStackTrace(e);
        }

        String bodyString = body.toString();
        Modules.getRequestModule().requestBody(headers, BASE_URL + "_matrix/client/r0/login", bodyString, callBack);
    }


    /**
     * 过滤
     *
     * @param userId
     * @param bodyString {}
     * @param callBack
     */
    public void UserIdFilter(String userId, String bodyString, MatrixCallBack callBack) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        String url = BASE_URL + "_matrix/client/r0/user/" + userId + "/filter";
        Modules.getRequestModule().requestBody(headers, url, bodyString, callBack);

    }


    /**
     * @param access_token
     * @param filter
     * @param since
     * @param full_state
     * @param set_presence
     * @param timeout
     * @param callBack
     */
    public void sync(
            String access_token,
            String filter,
            String since,
            boolean full_state,
            String set_presence,
            int timeout,
            MatrixCallBack callBack) {
        String url = BASE_URL + "_matrix/client/r0/sync?filter=" + filter + "&since" + since + "&full_state" + full_state + "&set_presence" + set_presence + "&timeout" + timeout + "&access_token" + access_token;
        Modules.getRequestModule().get(null, url, callBack);
    }
}

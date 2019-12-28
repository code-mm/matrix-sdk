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

    private static String HOME_SERVER = "https://www.mhw828.com/";

    public void setHomeServer(String url) {
        HOME_SERVER = url;
    }

    public String getHomeServer() {
        return HOME_SERVER;
    }

    // 获取登录类型
    public void getLoginType(MatrixCallBack callBack) {
        Modules.getRequestModule().get(null, HOME_SERVER + "_matrix/client/r0/login", callBack);
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

        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
        try {
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
        Modules.getRequestModule().requestBody(headers, HOME_SERVER + "_matrix/client/r0/login", bodyString, callBack);
    }


    /**
     * 过滤
     *
     * @param userId
     * @param bodyString {}
     * @param callBack
     */
    public void userIdFilter(String userId, String bodyString, MatrixCallBack callBack) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        String url = HOME_SERVER + "_matrix/client/r0/user/" + userId + "/filter";
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
        String url = HOME_SERVER + "_matrix/client/r0/sync?filter=" + filter + "&since=" + since + "&full_state=" + full_state + "&set_presence=" + set_presence + "&timeout=" + timeout + "&access_token=" + access_token;
        Modules.getRequestModule().get(null, url, callBack);
    }
}

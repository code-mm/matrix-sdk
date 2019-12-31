package org.ms.sdk.matrix;

import org.json.JSONObject;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.common.ICallBack;

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
    public void login(String username, String password, final ICallBack callBack) {
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
    public void userIdFilter(String userId, String bodyString, ICallBack callBack) {
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
            ICallBack callBack) {
        String url = HOME_SERVER + "_matrix/client/r0/sync?access_token=" + access_token + "&filter=" + filter + "&since=" + since + "&full_state=" + full_state + "&set_presence=" + set_presence + "&timeout=" + timeout;
        Modules.getRequestModule().get(null, url, callBack);
    }


    /**
     * 5获取房间的事件
     *
     * @param roomId
     * @param eventId
     * @param callBack
     */
    public void roomIdAndEventIdByRoomEvent(String roomId, String eventId, ICallBack callBack) {
        //GET
        String url = HOME_SERVER + "_matrix/client/r0/rooms/" + roomId + "/event/" + eventId + "?access_token=" + Modules.getDataModule().getMatrixData().getAccessToken();
        Modules.getRequestModule().get(null, url, callBack);
    }


    public void roomIdAndEventTypeAndStateKeyByRoomEvent(String roomId, String eventType, String stateKey, ICallBack callBack) {
        //GET /_matrix/client/r0/rooms/{roomId}/state/{eventType}/{stateKey}
        String url = HOME_SERVER + "_matrix/client/r0/rooms/" + roomId + "/state/" + eventType + "/" + stateKey + "?access_token=" + Modules.getDataModule().getMatrixData().getAccessToken();
        Modules.getRequestModule().get(null, url, callBack);
    }

    public void roomIdByRoomStatus(String roomId, ICallBack callBack) {

        //9.5.3   GET /_matrix/client/r0/rooms/{roomId}/state
        String url = HOME_SERVER + "_matrix/client/r0/rooms/" + roomId + "/state?access_token=" + Modules.getDataModule().getMatrixData().getAccessToken();
        Modules.getRequestModule().get(null, url, callBack);
    }


    public void roomIdByMembers(String roomId, ICallBack callBack) {

        //9.5.4   GET /_matrix/client/r0/rooms/{roomId}/members
        String url = HOME_SERVER + "_matrix/client/r0/rooms/" + roomId + "/members?access_token=" + Modules.getDataModule().getMatrixData().getAccessToken();
        Modules.getRequestModule().get(null, url, callBack);

    }

    public void roomIdByJoinedMembers(String roomId, ICallBack callBack) {

        //5.5   GET /_matrix/client/r0/rooms/{roomId}/joined_members

        String url = HOME_SERVER + "_matrix/client/r0/rooms/" + roomId + "/joined_members?access_token=" + Modules.getDataModule().getMatrixData().getAccessToken();
        Modules.getRequestModule().get(null, url, callBack);

    }

    public void roomIdByMessage(String roomId, ICallBack callBack) {
        //9.5.6   GET /_matrix/client/r0/rooms/{roomId}/messages
        String url = HOME_SERVER + "_matrix/client/r0/rooms/" + roomId + "/messages?access_token=" + Modules.getDataModule().getMatrixData().getAccessToken();
        Modules.getRequestModule().get(null, url, callBack);
    }
}

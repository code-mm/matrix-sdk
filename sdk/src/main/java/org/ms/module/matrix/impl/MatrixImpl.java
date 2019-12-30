package org.ms.module.matrix.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.common.ICallBack;
import org.ms.module.supper.inter.matrix.IMatrix;
import org.ms.module.supper.inter.net.Response;
import org.ms.sdk.matrix.MatrixCallBack;
import org.ms.sdk.matrix.MatrixRequest;
import org.ms.sdk.matrix.sync.handler.SyncJsonHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * IMatrix 接口实现
 */
public class MatrixImpl implements IMatrix {
    private static final String TAG = "MatrixImpl";


    private String homeServer;


    private MatrixRequest request;


    public MatrixImpl() {
    }

    @Override
    public void setHomeServer(String s) {
        homeServer = s;
        request = MatrixRequest.getInstance();
        request.setHomeServer(s);
    }

    @Override
    public String getHomeServer() {
        return homeServer;
    }

    @Override
    public void getVersion(ICallBack iCallBack) {

    }

    @Override
    public void login(String s, String s1, ICallBack iCallBack) {

        request.login(s, s1, iCallBack);

    }

    @Override
    public void register(String s, String s1, ICallBack iCallBack) {

    }

    private List<String> recentRooms = new ArrayList<>();


    /**
     * 最近房间
     *
     * @param s
     */
    @Override
    public void addRecentRooms(String s) {
        if (s == null) {
            return;
        }
        boolean exists = false;
        for (String it : recentRooms) {
            if (s.equals(it)) {
                exists = true;
            }
        }
        if (!exists) {
            recentRooms.add(s);
        }
    }

    @Override
    public List<String> recentRooms() {
        return null;
    }

    private List<Map<String, String>> directs = new ArrayList<>();

    @Override
    public List<Map<String, String>> direct() {
        return directs;
    }

    @Override
    public void addDirect(Map<String, String> map) {
        boolean exists = false;
        for (Map<String, String> it : directs) {
            Set<String> keySet = it.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Iterator<String> iterator1 = map.keySet().iterator();
                while (iterator1.hasNext()) {
                    String key1 = iterator1.next();
                    if (key.equals(key1)) {
                        if (it.get(key).equals(map.get(key1))) {
                            exists = true;
                        }
                    }
                }
            }
        }
        if (!exists) {
            directs.add(map);
        }
    }

    @Override
    public void sync(String s, String s1, String s2, boolean b, String s3, int i, ICallBack iCallBack) {
        request.sync(s, s1, s2, b, s3, i, iCallBack);

    }

    @Override
    public void startSync() {
        Modules.getMatrixModule().sync(Modules.getDataModule().getAccessToken(), "1", since(), true, "offline", 3000, callBack);
    }

    @Override
    public void roomIdAndEventIdByRoomEvent(String s, String s1, ICallBack iCallBack) {
        request.roomIdAndEventIdByRoomEvent(s, s1, iCallBack);

    }

    @Override
    public void roomIdAndEventTypeAndStateKeyByRoomEvent(String s, String s1, String s2, ICallBack iCallBack) {
        request.roomIdAndEventTypeAndStateKeyByRoomEvent(s, s1, s2, iCallBack);
    }

    @Override
    public void roomIdByRoomStatus(String s, ICallBack iCallBack) {
        request.roomIdByRoomStatus(s, iCallBack);
    }

    @Override
    public void roomIdByMembers(String s, ICallBack iCallBack) {
        request.roomIdByMembers(s, iCallBack);
    }

    @Override
    public void roomIdByJoinedMembers(String s, ICallBack iCallBack) {
        request.roomIdByJoinedMembers(s, iCallBack);
    }


    @Override
    public void roomIdByMessage(String s, ICallBack iCallBack) {
        request.roomIdByMessage(s, iCallBack);
    }

    /**
     * 创建房间
     *
     * @param s
     * @param iCallBack
     */
    @Override
    public void createRoom(String s, ICallBack iCallBack) {
    }

    MatrixCallBack callBack = new MatrixCallBack() {

        @Override
        public void onSuccess(Object o) {
            Response response = (Response) o;
            String body = response.body;
            Modules.getLogModule().json(TAG, body);
            try {
                JSONObject jsonObject = new JSONObject(response.body);
                // 处理数据
                SyncJsonHandler jsonHandler = new SyncJsonHandler(jsonObject);
                String next_batch = jsonObject.getString("next_batch");
                Modules.getMatrixModule().sync(Modules.getDataModule().getAccessToken(), "1", next_batch, true, "offline", 3000, callBack);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 当前时间格式化
     * 1_1111_1111_1111
     *
     * @return
     */
    public String since() {
        String timeStr = System.currentTimeMillis() + "";
        return
                "s" + timeStr.substring(0, 1) + "_" +
                        timeStr.substring(1, 4) + "_" +
                        timeStr.substring(4, 8) + "_" +
                        timeStr.substring(8, 12);
    }
}
package org.ms.module.matrix.impl;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.common.CallBack;
import org.ms.module.supper.inter.common.ICallBack;
import org.ms.module.supper.inter.matrix.IMatrix;
import org.ms.module.supper.inter.net.Response;
import org.ms.sdk.matrix.MatrixCallBack;
import org.ms.sdk.matrix.MatrixRequest;
import org.ms.sdk.matrix.db.room.MatrixRoom;
import org.ms.sdk.matrix.db.room.MatrixRoomInjection;
import org.ms.sdk.matrix.db.user.MatrixUser;
import org.ms.sdk.matrix.db.user.MatrixUserInjection;
import org.ms.sdk.matrix.sync.handler.SyncJsonHandler;

/**
 * IMatrix 接口实现
 */
public class MatrixImpl implements IMatrix {
    private static final String TAG = "MatrixImpl";
    private String homeServer;

    public MatrixImpl() {
    }

    @Override
    public void setHomeServer(String homeServer) {
        Modules.getLogModule().i(TAG, "homeServer : " + homeServer);
        this.homeServer = homeServer;
        Modules.getDataModule().getMatrixData().setHomeServer(homeServer);
    }

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


    @Override
    public void login(String username, String password, ICallBack iCallBack) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Modules.getMatrixApiModule().getSessionManagement().login(jsonObject, new CallBack() {
            @Override
            public void onSuccess(Object o) {
                Response response = (Response) o;
                Modules.getLogModule().e(TAG, response.code + "");
                Modules.getLogModule().e(TAG, response.body);
                if (response.code == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body);
                        String user_id = jsonObject.getString("user_id");
                        String access_token = jsonObject.getString("access_token");
                        String home_server = jsonObject.getString("home_server");
                        String device_id = jsonObject.getString("device_id");
                        Modules.getDataModule().getMatrixData().setUserId(user_id);
                        Modules.getDataModule().getMatrixData().setDeviceId(device_id);
                        Modules.getDataModule().getMatrixData().setDeviceId(device_id);
                        Modules.getDataModule().getMatrixData().setAccessToken(access_token);

                        MatrixUser matrixUser = new MatrixUser();
                        matrixUser.setUserId(user_id);
                        matrixUser.setUsername(username);
                        matrixUser.setPassword(password);
                        matrixUser.setBaseUrl(Modules.getDataModule().getMatrixData().getHomeServer());
                        matrixUser.setDeviceId(device_id);
                        matrixUser.setHomeServer(Modules.getDataModule().getMatrixData().getHomeServer());
                        MatrixUserInjection.provideDataDataSource().insert(matrixUser);
                        if (iCallBack != null) {
                            iCallBack.onSuccess(o);
                        }


                        // 启动数据库观察

                        startRoomObserver();

                        // 登录成功 | 开始同步数据
                        startSync();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (response.code == 429) {
                    // 登录过于频繁
                }
            }

            @Override
            public void onFailure(Object o) {
                Modules.getUtilsModule().getToastUtils().show("onFailure");
            }

            @Override
            public void onException(Object o) {
                Modules.getUtilsModule().getToastUtils().show("onException");
            }
        });
    }

    // 启动数据观察
    // 观察房间数据变化
    private void startRoomObserver() {
        LiveData<MatrixRoom> last = MatrixRoomInjection.provideDataDataSource().getLast();
        if (!last.hasActiveObservers()) {
            last.observeForever(new Observer<MatrixRoom>() {
                @Override
                public void onChanged(@Nullable MatrixRoom matrixRoom) {
                    Modules.getLogModule().json(TAG, Modules.getUtilsModule().getGsonUtils().toJson(matrixRoom));


                }
            });
        }
    }

    // 同步数据回调 ，
    MatrixCallBack callBack = new MatrixCallBack() {
        @Override
        public void onSuccess(Object o) {
            Response response = (Response) o;
            Modules.getLogModule().i(TAG, "code : " + response.code);
            Modules.getLogModule().i(TAG, "body : " + response.body);
            try {
                JSONObject jsonObject = new JSONObject(response.body);
                // 处理数据
                SyncJsonHandler jsonHandler = new SyncJsonHandler(jsonObject);
                String next_batch = jsonObject.getString("next_batch");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    // 一直运行同步
    private void runSync(String next_batch) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("access_token", Modules.getDataModule().getMatrixData().getAccessToken());
            jsonObject.put("filter", "1");
            jsonObject.put("since", next_batch);
            jsonObject.put("full_state", true);
            jsonObject.put("set_presence", "offline");
            jsonObject.put("timeout", 30000);
            Modules.getMatrixApiModule().getRoomParticipation().sync(jsonObject, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 登录之后开始同步=启动同步
    private void startSync() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("access_token", Modules.getDataModule().getMatrixData().getAccessToken());
            jsonObject.put("filter", "1");
            jsonObject.put("since", since());
            jsonObject.put("full_state", true);
            jsonObject.put("set_presence", "offline");
            jsonObject.put("timeout", 30000);
            Modules.getMatrixApiModule().getRoomParticipation().sync(jsonObject, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @Override
//    public String getHomeServer() {
//        return homeServer;
//    }
//
//    @Override
//    public void getVersion(ICallBack iCallBack) {
//
//    }
//
//    @Override
//    public void login(String s, String s1, ICallBack iCallBack) {
//
//        request.login(s, s1, iCallBack);
//
//    }
//
//    @Override
//    public void register(String s, String s1, ICallBack iCallBack) {
//
//    }
//
//    private List<String> recentRooms = new ArrayList<>();
//
//
//    /**
//     * 最近房间
//     *
//     * @param s
//     */
//    @Override
//    public void addRecentRooms(String s) {
//        if (s == null) {
//            return;
//        }
//        boolean exists = false;
//        for (String it : recentRooms) {
//            if (s.equals(it)) {
//                exists = true;
//            }
//        }
//        if (!exists) {
//            recentRooms.add(s);
//        }
//    }
//
//    @Override
//    public List<String> recentRooms() {
//        return null;
//    }
//
//    private List<Map<String, String>> directs = new ArrayList<>();
//
//    @Override
//    public List<Map<String, String>> direct() {
//        return directs;
//    }
//
//    @Override
//    public void addDirect(Map<String, String> map) {
//        boolean exists = false;
//        for (Map<String, String> it : directs) {
//            Set<String> keySet = it.keySet();
//            Iterator<String> iterator = keySet.iterator();
//            while (iterator.hasNext()) {
//                String key = iterator.next();
//                Iterator<String> iterator1 = map.keySet().iterator();
//                while (iterator1.hasNext()) {
//                    String key1 = iterator1.next();
//                    if (key.equals(key1)) {
//                        if (it.get(key).equals(map.get(key1))) {
//                            exists = true;
//                        }
//                    }
//                }
//            }
//        }
//        if (!exists) {
//            directs.add(map);
//        }
//    }
//
//    @Override
//    public void sync(String s, String s1, String s2, boolean b, String s3, int i, ICallBack iCallBack) {
//        request.sync(s, s1, s2, b, s3, i, iCallBack);
//
//    }
//
//    @Override
//    public void startSync() {
//        Modules.getMatrixModule().sync(Modules.getDataModule().getMatrixData().getAccessToken(), "1", since(), true, "offline", 3000, callBack);
//    }
//
//    @Override
//    public void roomIdAndEventIdByRoomEvent(String s, String s1, ICallBack iCallBack) {
//        request.roomIdAndEventIdByRoomEvent(s, s1, iCallBack);
//
//    }
//
//    @Override
//    public void roomIdAndEventTypeAndStateKeyByRoomEvent(String s, String s1, String s2, ICallBack iCallBack) {
//        request.roomIdAndEventTypeAndStateKeyByRoomEvent(s, s1, s2, iCallBack);
//    }
//
//    @Override
//    public void roomIdByRoomStatus(String s, ICallBack iCallBack) {
//        request.roomIdByRoomStatus(s, iCallBack);
//    }
//
//    @Override
//    public void roomIdByMembers(String s, ICallBack iCallBack) {
//        request.roomIdByMembers(s, iCallBack);
//    }
//
//    @Override
//    public void roomIdByJoinedMembers(String s, ICallBack iCallBack) {
//        request.roomIdByJoinedMembers(s, iCallBack);
//    }
//
//
//    @Override
//    public void roomIdByMessage(String s, ICallBack iCallBack) {
//        request.roomIdByMessage(s, iCallBack);
//    }
//
//    /**
//     * 创建房间
//     *
//     * @param s
//     * @param iCallBack
//     */
//    @Override
//    public void createRoom(String s, ICallBack iCallBack) {
//    }
//


}
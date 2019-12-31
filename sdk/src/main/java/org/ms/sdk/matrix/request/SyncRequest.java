package org.ms.sdk.matrix.request;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.net.Response;
import org.ms.sdk.matrix.MatrixCallBack;
import org.ms.sdk.matrix.module.filter.Filter;
import org.ms.sdk.matrix.sync.handler.SyncJsonHandler;

/**
 * 同步请求处理
 */
public class SyncRequest {

    // 单例模式设计
    private static SyncRequest syncRequest = new SyncRequest();

    //
    public static SyncRequest getInstance() {
        return syncRequest;
    }

    private static final String TAG = "SyncRequest";

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

    private String mFilter_id;

    public void filter(Filter filter) {
        String body = Modules.getUtilsModule().getGsonUtils().toJson(filter);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("body", body);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Modules.getMatrixApiModule().getRoomParticipation().userIdByFilter(jsonObject, new MatrixCallBack() {

            @Override
            public void onSuccess(Object o) {
                Response response = (Response) o;
                Modules.getLogModule().e(TAG, " code : " + response.code);
                Modules.getLogModule().e(TAG, " body : " + response.body);

                try {
                    JSONObject jsonObject1 = new JSONObject(response.body);

                    // 过滤ID
                    String filter_id = jsonObject1.getString("filter_id");

                    mFilter_id = filter_id;

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("access_token", Modules.getDataModule().getMatrixData().getAccessToken());
                    jsonObject.put("filter", filter_id);
                    jsonObject.put("since", since());
                    jsonObject.put("full_state", false);
                    jsonObject.put("set_presence", "online");
                    jsonObject.put("timeout", 30000);
                    Modules.getMatrixApiModule().getRoomParticipation().sync(jsonObject, callBack);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

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
                // 需要一直同步
                //runSync(next_batch);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private void runSync(String next_batch) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("access_token", Modules.getDataModule().getMatrixData().getAccessToken());
            jsonObject.put("filter", mFilter_id);
            jsonObject.put("since", next_batch);
            jsonObject.put("full_state", true);
            jsonObject.put("set_presence", "online");
            jsonObject.put("timeout", 30000);
            Modules.getMatrixApiModule().getRoomParticipation().sync(jsonObject, callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

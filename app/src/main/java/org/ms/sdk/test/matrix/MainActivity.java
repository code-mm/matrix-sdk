package org.ms.sdk.test.matrix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ms.matrix.test.R;
import org.ms.module.base.view.BaseAppCompatActivity;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.net.Response;
import org.ms.sdk.matrix.MatrixCallBack;
import org.ms.sdk.matrix.client.MatrixClient;
import org.ms.sdk.matrix.inter.IMatrix;

import java.text.SimpleDateFormat;


public class MainActivity extends BaseActivity implements View.OnClickListener {


    private Button buttonSync;

    MatrixClient matrixClient;
    IMatrix matrix;


    MatrixCallBack callBack = new MatrixCallBack() {

        @Override
        public void onSuccess(Object o) {
            Response response = (Response) o;
            String body = response.body;

            Modules.getLogModule().json("tag", body);


            try {
                JSONObject jsonObject = new JSONObject(response.body);

                // 处理数据
                JSONObject device_one_time_keys_count = jsonObject.getJSONObject("device_one_time_keys_count");
                eventHandler_device_one_time_keys_count(device_one_time_keys_count);
                JSONObject groups = jsonObject.getJSONObject("groups");
                eventHandler_groups(groups);
                JSONObject rooms = jsonObject.getJSONObject("rooms");
                eventHandler_rooms(rooms);
                JSONObject presence = jsonObject.getJSONObject("presence");
                eventHandler_presence(presence);
                JSONObject device_lists = jsonObject.getJSONObject("device_lists");
                eventHandler_device_lists(device_lists);
                JSONObject to_device = jsonObject.getJSONObject("to_device");
                eventHandler_to_device(to_device);
                JSONObject account_data = jsonObject.getJSONObject("account_data");
                eventHandler_account_data(account_data);


                // 下一次同步
                String next_batch = jsonObject.getString("next_batch");
                matrix.sync(Modules.getDataModule().getAccessToken(), "1", next_batch, true, "offline", 3000, callBack);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private void eventHandler_device_lists(JSONObject device_lists) {
    }

    private void eventHandler_presence(JSONObject presence) {
    }

    private void eventHandler_rooms(JSONObject rooms) {
    }

    private void eventHandler_groups(JSONObject groups) {
    }

    private void eventHandler_device_one_time_keys_count(JSONObject device_one_time_keys_count) {
    }

    private void eventHandler_to_device(JSONObject to_device) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matrixClient = MatrixClient.Builder.builder().setHomeServer("https://matrix-client.matrix.org/").build();
        matrix = matrixClient.matrix();


        Modules.getControlSwitch().setRequestLog(true);

    }

    @Override
    protected void initView() {


        buttonSync = findViewById(R.id.buttonSync);

        buttonSync.setOnClickListener(this);


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSync:
                matrix.sync(Modules.getDataModule().getAccessToken(), "1", "s" + System.currentTimeMillis(), true, "offline", 3000, callBack);


                break;
        }
    }


    private void eventHandler_account_data(JSONObject account_data) {


        try {


            JSONArray events = account_data.getJSONArray("events");
            for (int i = 0; i < events.length(); i++) {
                JSONObject eventsT = events.getJSONObject(i);
                String type = eventsT.getString("type");

                if ("im.vector.setting.breadcrumbs".equals(type)) {
                    JSONObject content = eventsT.getJSONObject("content");
                    JSONArray recent_rooms = content.getJSONArray("recent_rooms");
                    for (int j = 0; j < recent_rooms.length(); j++) {
                        String string = recent_rooms.getString(j);
                        Modules.getLogModule().e("recent_rooms ", "" + string);
                    }
                }
                if ("m.direct".equals(type)) {

                }
                if ("m.push_rules".equals(type)) {

                }

                if ("im.vector.riot.breadcrumb_rooms".equals(type)) {

                }

                if ("m.accepted_terms".equals(type)) {

                }

                if ("m.widgets".equals(type)) {

                }

                if ("im.vector.web.settings".equals(type)) {

                }

                if ("m.ignored_user_list".equals(type)) {

                }

                if ("im.vector.setting.breadcrumbs".equals(type)) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private JSONObject account_data() {
        try {
            JSONObject account_data = new JSONObject();
            JSONArray not_senders = new JSONArray();
            JSONArray not_types = new JSONArray();
            JSONArray senders = new JSONArray();
            JSONArray types = new JSONArray();

            account_data.put("limit", 0);
            account_data.put("not_senders", not_senders);
            account_data.put("not_types", not_types);
            account_data.put("senders", senders);
            account_data.put("types", types);
            return account_data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    JSONArray event_fields() {
        JSONArray event_fields = new JSONArray();
        return event_fields;
    }

    JSONObject event_format() {
        JSONObject event_format = new JSONObject();
        return event_format;
    }


    JSONObject presence() {

        JSONObject presence = new JSONObject();
        return presence;
    }


    JSONObject room() {

        JSONObject room = new JSONObject();


        return room;
    }


    private void f() throws Exception {
        JSONObject rootObject = new JSONObject();

        rootObject.put("account_data", account_data());
        rootObject.put("event_fields", event_fields());
        rootObject.put("event_format", event_format());
        rootObject.put("presence", presence());
        rootObject.put("room", room());


    }


}

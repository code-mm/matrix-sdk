package org.ms.sdk.matrix.sync.handler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ms.module.supper.client.Modules;
import org.ms.sdk.matrix.module.filter.Filter;
import org.ms.sdk.matrix.request.SyncRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class account_data {


    Filter filter = new Filter();

    Filter.AccountDataBean accountDataBean = new Filter.AccountDataBean();


    private List<String> not_senders = new ArrayList<>();
    private List<String> not_types = new ArrayList<>();
    private List<String> senders = new ArrayList<>();
    private List<String> types = new ArrayList<>();


    public account_data(JSONObject account_data) {
        try {

            accountDataBean.setLimit(1);
            accountDataBean.setNot_senders(not_senders);
            accountDataBean.setNot_types(not_types);
            accountDataBean.setSenders(senders);
            accountDataBean.setTypes(types);


            JSONArray events = account_data.getJSONArray("events");
            for (int i = 0; i < events.length(); i++) {
                JSONObject eventsT = events.getJSONObject(i);
                String type = eventsT.getString("type");
                JSONObject content = eventsT.getJSONObject("content");

                // 添加到不同步过滤规则
                not_types.add(type);

                // 最近的房间
                if ("im.vector.setting.breadcrumbs".equals(type)) {

                    JSONArray recent_rooms = content.getJSONArray("recent_rooms");
                    for (int j = 0; j < recent_rooms.length(); j++) {
                        String string = recent_rooms.getString(j);
                        Modules.getLogModule().e("recent_rooms ", "" + string);
                        // 添加到最近列表
                        //Modules.getMatrixModule().addRecentRooms(string);

                    }
                }
                if ("m.direct".equals(type)) {


                    // key 为好友ID ,
                    Iterator<String> keys = content.keys();
                    // 变量好友ID
                    while (keys.hasNext()) {
                        // 取得key
                        String id = keys.next();
                        // 取出好友房间
                        JSONArray jsonArray = content.getJSONArray(id);
                        // 遍历房间ID
                        for (int t = 0; t < jsonArray.length(); t++) {
                            // 房间ID
                            String roomId = jsonArray.getString(t);
                            Map<String, String> map = new HashMap<>();
                            map.put(id, roomId);
                            // 将直接聊天(好友概念)的ID和所在房间添加到数据域
                            //Modules.getMatrixModule().addDirect(map);
                        }
                    }
                }
                if ("m.push_rules".equals(type)) {

                    JSONObject global = content.getJSONObject("global");

                    JSONArray underride = global.getJSONArray("underride");
                    JSONArray sender = global.getJSONArray("sender");
                    JSONArray room = global.getJSONArray("room");
                    JSONArray content1 = global.getJSONArray("content");
                    JSONArray override = global.getJSONArray("override");
                    JSONObject device = content.getJSONObject("device");

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
            }


            filter.setEvent_format("client");
            filter.setAccount_data(accountDataBean);
            String filterStr = Modules.getUtilsModule().getGsonUtils().toJson(filter).trim();
            Modules.getLogModule().e(TAG, "上一次过滤规则 : " + mFilter);
            Modules.getLogModule().e(TAG, "本次过滤规则 : " + filterStr);
            if (filterStr.equals(mFilter)) {
                Modules.getLogModule().e(TAG, "与上一次规则一样");
            } else {

                Modules.getLogModule().e(TAG, "与上一次规则不一样");
                SyncRequest.getInstance().filter(filter);
                mFilter = filterStr.trim();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String mFilter;
    private static final String TAG = "account_data";
}
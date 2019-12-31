package org.ms.sdk.matrix.sync.handler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ms.module.supper.client.Modules;
import org.ms.sdk.matrix.db.message.MatrixMessage;
import org.ms.sdk.matrix.db.message.MatrixMessageInjection;
import org.ms.sdk.matrix.db.room.MatrixRoom;
import org.ms.sdk.matrix.db.room.MatrixRoomInjection;
import org.ms.sdk.matrix.module.filter.Filter;
import org.ms.sdk.matrix.request.SyncRequest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


// room 数据处理
public class rooms {


    private static final String TAG = "rooms";


    private Filter filter = new Filter();

    private List<String> event_fields = new ArrayList<>();


    Filter.RoomBean roomBean = new Filter.RoomBean();
    Filter.RoomBean.AccountDataBeanX accountDataBeanX = new Filter.RoomBean.AccountDataBeanX();
    Filter.RoomBean.EphemeralBean ephemeralBean = new Filter.RoomBean.EphemeralBean();
    Filter.RoomBean.StateBean stateBean = new Filter.RoomBean.StateBean();
    Filter.RoomBean.TimelineBean timelineBean = new Filter.RoomBean.TimelineBean();


    private List<String> room_account_data_not_senders = new ArrayList<>();
    private List<String> room_account_data_not_types = new ArrayList<>();
    private List<String> room_account_data_senders = new ArrayList<>();
    private List<String> room_account_data_types = new ArrayList<>();
    private List<String> room_account_data_not_rooms = new ArrayList<>();

    private List<String> room_ephemeral_not_senders = new ArrayList<>();
    private List<String> room_ephemeral_not_types = new ArrayList<>();
    private List<String> room_ephemeral_senders = new ArrayList<>();
    private List<String> room_ephemeral_types = new ArrayList<>();
    private List<String> room_ephemeral_not_rooms = new ArrayList<>();
    private List<String> room_ephemeral_rooms = new ArrayList<>();

    private List<String> rooms = new ArrayList<>();
    private List<String> not_rooms = new ArrayList<>();


    private List<String> room_state_not_senders = new ArrayList<>();
    private List<String> room_state_not_types = new ArrayList<>();
    private List<String> room_state_senders = new ArrayList<>();
    private List<String> room_state_types = new ArrayList<>();
    private List<String> room_state_not_rooms = new ArrayList<>();
    private List<String> room_state_rooms = new ArrayList<>();


    private List<String> room_timeline_not_senders = new ArrayList<>();
    private List<String> room_timeline_not_types = new ArrayList<>();
    private List<String> room_timeline_senders = new ArrayList<>();
    private List<String> room_timeline_types = new ArrayList<>();
    private List<String> room_timeline_not_rooms = new ArrayList<>();
    private List<String> room_timeline_rooms = new ArrayList<>();


    public rooms(JSONObject rooms) {

        Modules.getLogModule().println("解析的数据 : " + rooms.toString());

        roomBean.setAccount_data(accountDataBeanX);
        roomBean.setEphemeral(ephemeralBean);
        roomBean.setNot_rooms(not_rooms);
        roomBean.setTimeline(timelineBean);
        roomBean.setState(stateBean);
        // 是否包含离开的
        roomBean.setInclude_leave(false);


        try {

            //1---------- 第一个字段
            JSONObject join = rooms.getJSONObject("join");
            Iterator<String> keys = join.keys();
            //1.2----------
            while (keys.hasNext()) {
                // 房间ID
                String roomId = keys.next();
                Modules.getLogModule().i(TAG, "roomId : " + roomId);

                not_rooms.add(roomId);

                // 将房间信息存入房间数据库

                MatrixRoom room = new MatrixRoom();
                room.setRoomId(roomId);
                room.setHomeServer(Modules.getDataModule().getMatrixData().getHomeServer());
                MatrixRoomInjection.provideDataDataSource().insert(room);


                JSONObject jsonObject = join.getJSONObject(roomId);
                //1.2.1----------
                JSONObject timeline = jsonObject.getJSONObject("timeline");
                //1.2.1.1----------
                JSONArray events = timeline.getJSONArray("events");
                //1.2.1.2----------
                String prev_batch = timeline.getString("prev_batch");
                //1.2.1.3----------
                boolean limited = timeline.getBoolean("limited");

                // 遍历事件 timeline 节点下的 events
                for (int i = 0; i < events.length(); i++) {

                    MatrixMessage matrixMessage = new MatrixMessage();
                    matrixMessage.setDate(System.currentTimeMillis());
                    matrixMessage.setRoomId(roomId);
                    matrixMessage.setHomeServer(Modules.getDataModule().getMatrixData().getHomeServer());

                    JSONObject jsonObject1 = events.getJSONObject(i);
                    String type = jsonObject1.getString("type");
                    String sender = jsonObject1.getString("sender");
                    // 获取消息
                    JSONObject content = jsonObject1.getJSONObject("content");

                    long origin_server_ts = jsonObject1.getLong("origin_server_ts");
                    JSONObject unsigned = jsonObject1.getJSONObject("unsigned");
                    int age = unsigned.getInt("age");
                    String event_id = jsonObject1.getString("event_id");
                    matrixMessage.setEventId(event_id);
                    event_fields.add(event_id);
                    // 时间
                    matrixMessage.setOriginServerTs(origin_server_ts);
                    // 发送者
                    matrixMessage.setSender(sender);
                    if ("m.room.message".equals(type)) {
                        // 消息内容
                        String body = content.getString("body");
                        // 消息类型
                        String msgtype = content.getString("msgtype");

                        if ("m.text".equals(msgtype)) {

                        }

                        matrixMessage.setMessageType("m.text");
                        matrixMessage.setMessage(body);
                        matrixMessage.setEventId(event_id);
                    }

                    if ("m.reaction".equals(type)) {

                    }


                    String s = Modules.getUtilsModule().getGsonUtils().toJson(matrixMessage);
                    // 输出消息体
                    Modules.getLogModule().json(TAG + " 消息 ", s);
                    if (matrixMessage.getMessage() != null && !matrixMessage.getMessage().equals("")) {

                        // 将消息插入数据库
                        MatrixMessageInjection.provideDataDataSource().insert(matrixMessage);
                    }

                }


                //1.2.2----------
                JSONObject state = jsonObject.getJSONObject("state");
                //1.2.2.1----------
                JSONArray events1 = state.getJSONArray("events");
                // 解析event


                for (int i = 0; i < events1.length(); i++) {
                    JSONObject jsonObject1 = events1.getJSONObject(i);
                    // 类型
                    String type = jsonObject1.getString("type");

                    // 发送者
                    String sender = jsonObject1.getString("sender");
                    //
                    String state_key = jsonObject1.getString("state_key");
                    // 服务器时间
                    long origin_server_ts = jsonObject1.getLong("origin_server_ts");
                    JSONObject unsigned = jsonObject1.getJSONObject("unsigned");
                    // 事件ID
                    String event_id = jsonObject1.getString("event_id");
                    // 内容
                    JSONObject content = jsonObject1.getJSONObject("content");


                    if ("m.room.power_levels".equals(type)) {
                        JSONObject users = content.getJSONObject("users");
                        Iterator<String> keys1 = users.keys();
                        while (keys1.hasNext()) {
                            int anInt = users.getInt(keys1.next());
                        }
                        String users_default = content.getString("users_default");
                        JSONObject events2 = content.getJSONObject("events");
                        int name = events2.getInt("m.room.name");
                        int power_levels = events2.getInt("m.room.power_levels");
                        int history_visibility = events2.getInt("m.room.history_visibility");
                        int canonical_alias = events2.getInt("m.room.canonical_alias");
                        int avatar = events2.getInt("m.room.avatar");
                        int events_default = content.getInt("events_default");
                        int state_default = content.getInt("state_default");
                        int ban = content.getInt("ban");
                        int kick = content.getInt("kick");
                        int redact = content.getInt("redact");
                        int invite = content.getInt("invite");
                    } else if ("m.room.name".equals(type)) {
                        // 房间名称
                        String roomName = content.getString("name");
                        long origin_server_ts1 = jsonObject1.getLong("origin_server_ts");

                    } else if ("m.room.member".equals(type)) {
                        // join
                        String membership = content.getString("membership");
                        // 显示名称
                        String displayname = content.getString("displayname");
                        // 头像地址
                        String avatar_url = content.getString("avatar_url");

                    } else if ("m.room.join_rules".equals(type)) {

                        String join_rule = content.getString("join_rule");


                    } else if ("m.room.history_visibility".equals(type)) {

                        String history_visibility = content.getString("history_visibility");


                    } else if ("m.room.guest_access".equals(type)) {

                        String guest_access = content.getString("guest_access");


                    } else if ("m.room.create".equals(type)) {
                        //
                        // 房间版本号
                        String room_version = content.getString("room_version");
                        // 房间创建者
                        String creator = content.getString("creator");
                    }
                }

                //1.2.3----------
                JSONObject account_data = jsonObject.getJSONObject("account_data");
                JSONArray events2 = account_data.getJSONArray("events");

                // 遍历 account_data 节点下的 event
                for (int i = 0; i < events2.length(); i++) {

                    JSONObject jsonObject1 = events2.getJSONObject(i);
                    String type = jsonObject1.getString("type");
                    JSONObject content = jsonObject1.getJSONObject("content");

                    if ("m.fully_read".equals(type)) {
                        // 事件ID
                        String event_id = content.getString("event_id");
                    }
                }


                //1.2.4----------
                JSONObject ephemeral = jsonObject.getJSONObject("ephemeral");

                //1.2.5----------
                JSONObject unread_notifications = jsonObject.getJSONObject("unread_notifications");

                if (unread_notifications.has("notification_count")) {
                    int notification_count = unread_notifications.getInt("notification_count");
                }

                if (unread_notifications.has("highlight_count")) {
                    int highlight_count = unread_notifications.getInt("highlight_count");
                }


                //1.2.6----------
                JSONObject summary = jsonObject.getJSONObject("summary");


            }

            //1---------- 第二个字段
            JSONObject invite = rooms.getJSONObject("invite");
            //1---------- 第三个字段
            JSONObject leave = rooms.getJSONObject("leave");


            filter.setEvent_format("client");
            filter.setEvent_fields(event_fields);
            filter.setRoom(roomBean);

            Modules.getLogModule().e(TAG, "过滤规则 : " + Modules.getUtilsModule().getGsonUtils().toJson(filter));

            SyncRequest.getInstance().filter(filter);

        } catch (Exception e) {
            Modules.getExceptionModule().printStackTrace(e);
        }
    }
}



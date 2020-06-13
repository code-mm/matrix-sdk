package org.ms.matrix.app.ui.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import org.ms.matrix.app.R;
import org.ms.matrix.app.db.event.Event;
import org.ms.matrix.app.db.MatrixDbInjection;
import org.ms.matrix.app.ui.fragment.message.MessageFragment;
import org.ms.matrix.app.ui.fragment.my.MyFragment;
import org.ms.matrix.app.ui.fragment.room.RoomFragment;
import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.matrix.sdk.model.MessageModel;
import org.ms.matrix.sdk.model.RoomJoinedUserInfo;
import org.ms.matrix.sdk.model.event.IEvent;
import org.ms.matrix.sdk.model.event.m_call_answer;
import org.ms.matrix.sdk.model.event.m_call_candidates;
import org.ms.matrix.sdk.model.event.m_call_invite;
import org.ms.matrix.sdk.model.event.m_room_message;
import org.ms.matrix.sdk.model.event.m_text;
import org.ms.matrix.sdk.model.request.SyncRequest;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.matrix.sdk.supper.inter.listener.MatrixListener;
import org.ms.matrix.sdk.supper.inter.room.IRoom;
import org.ms.module.base.view.BaseAppCompatActivity;
import org.ms.module.base.view.StatusBarUtil;
import org.ms.module.supper.client.Modules;
import org.ms.view.navigation.NavigationView;
import org.ms.view.navigation.NavigationViewAdapter;
import org.ms.view.navigation.NavigationViewItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseAppCompatActivity<MainActivityPresenter> implements IMainActivity, View.OnClickListener {

    private static final String TAG = "MainActivity";


    private NavigationView navigationView;

    private List<NavigationViewItem> navigationViewItems = new ArrayList<>();

    private NavigationViewAdapter navigationViewAdapter;

    private HashMap<String, Fragment> hashMapFragments = new HashMap<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        navigationView = findViewById(R.id.navigationView);
        navigationView.setOnItemClickListener((view, navigationChildItemView) -> {
            String name = navigationChildItemView.getItem().name;
            Fragment fragment = hashMapFragments.get(name);
            showFragment(fragment);
        });


    }

    @Override
    protected MainActivityPresenter initPresenter() {
        return new MainActivityPresenter(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        navigationViewItems.clear();
        navigationViewAdapter = new NavigationViewAdapter(navigationViewItems);
        navigationView.setAdapter(navigationViewAdapter);

        NavigationViewItem navigationViewMessage = new NavigationViewItem();
        navigationViewMessage.name = "消息";
        navigationViewMessage.drawable = getDrawable(R.drawable.tab_bar_message);
        navigationViewItems.add(navigationViewMessage);

        NavigationViewItem navigationViewRoom = new NavigationViewItem();
        navigationViewRoom.name = "房间";
        navigationViewRoom.drawable = getDrawable(R.drawable.image_room);
        navigationViewItems.add(navigationViewRoom);


        NavigationViewItem navigationViewMy = new NavigationViewItem();
        navigationViewMy.name = "我的";
        navigationViewMy.drawable = getDrawable(R.drawable.tab_bar_my);
        navigationViewItems.add(navigationViewMy);

        navigationViewAdapter.notificationDataChange();

        hashMapFragments.clear();


        hashMapFragments.put("消息", MessageFragment.newInstance());
        hashMapFragments.put("房间", RoomFragment.newInstance());
        hashMapFragments.put("我的", MyFragment.newInstance());

        for (String key : hashMapFragments.keySet()) {
            fragmentManager.beginTransaction().add(R.id.frameLayout, hashMapFragments.get(key)).commit();
        }
        showFragment(hashMapFragments.get("消息"));
        navigationView.setSelected(0);


        MatrixClient.getInstance().addListener(new MatrixListener() {
            @Override
            public void onEvent(IEvent event) {
                Log.e(TAG, "onEvent: " + Modules.getUtilsModule().getGsonUtils().toJson(event));

                if ("m.room.message".equals(event.getType())) {
                    m_room_message e_m_room_message = (m_room_message) event;
                    String msgtype = e_m_room_message.getContent().getMsgtype();

                    if ("m.text".equals(msgtype)) {
                        m_room_message<m_text> e_m_room_message_m_text = (m_room_message) event;

                        Event build = Event.builder()
                                ._type(e_m_room_message.getType())
                                ._event_id(e_m_room_message.getEvent_id())
                                ._room_id(e_m_room_message.getRoom_id())
                                ._origin_server_ts(e_m_room_message.getOrigin_server_ts())
                                ._content(e_m_room_message_m_text.getContent().toJson())
                                ._sender(e_m_room_message.getSender())
                                ._unsigned(e_m_room_message.getUnsigned().toJson())
                                ._timestamp(new Date().getTime())
                                .build();

                        MatrixDbInjection.providerEventDataSource().insert(build);

                    } else if ("m.emote".equals(msgtype)) {

                    } else if ("m.notice".equals(msgtype)) {

                    } else if ("m.image".equals(msgtype)) {

                    } else if ("m.file".equals(msgtype)) {

                    } else if ("m.audio".equals(msgtype)) {

                    } else if ("m.location".equals(msgtype)) {

                    } else if ("m.video".equals(msgtype)) {

                    }
                } else if ("m.call.invite".equals(event.getType())) {


                    m_call_invite e_m_call_invite = (m_call_invite) event;

                    Event build = Event.builder()
                            ._type(e_m_call_invite.getType())
                            ._event_id(e_m_call_invite.getEvent_id())
                            ._room_id(e_m_call_invite.getRoom_id())
                            ._origin_server_ts(e_m_call_invite.getOrigin_server_ts())
                            ._content(e_m_call_invite.getContent().toJson())
                            ._sender(e_m_call_invite.getSender())
                            ._unsigned(e_m_call_invite.getUnsigned().toJson())
                            ._timestamp(new Date().getTime())
                            .build();
                    MatrixDbInjection.providerEventDataSource().insert(build);


                } else if ("m.call.hangup".equals(event.getType())) {

                } else if ("m.call.candidates".equals(event.getType())) {

                    m_call_candidates e_call_candidates = (m_call_candidates) event;

                    Event build = Event.builder()
                            ._type(e_call_candidates.getType())
                            ._event_id(e_call_candidates.getEvent_id())
                            ._room_id(e_call_candidates.getRoom_id())
                            ._origin_server_ts(e_call_candidates.getOrigin_server_ts())
                            ._content(e_call_candidates.getContent().toJson())
                            ._sender(e_call_candidates.getSender())
                            ._unsigned(e_call_candidates.getUnsigned().toJson())
                            ._timestamp(new Date().getTime())
                            .build();
                    MatrixDbInjection.providerEventDataSource().insert(build);

                } else if ("m.call.answer".equals(event.getType())) {
                    m_call_answer e_m_call_answer = (m_call_answer) event;
                    Event build = Event.builder()
                            ._type(e_m_call_answer.getType())
                            ._event_id(e_m_call_answer.getEvent_id())
                            ._room_id(e_m_call_answer.getRoom_id())
                            ._origin_server_ts(e_m_call_answer.getOrigin_server_ts())
                            ._content(e_m_call_answer.getContent().toJson())
                            ._sender(e_m_call_answer.getSender())
                            ._unsigned(e_m_call_answer.getUnsigned().toJson())
                            ._timestamp(new Date().getTime())
                            .build();
                    MatrixDbInjection.providerEventDataSource().insert(build);

                } else if ("".equals(event.getType())) {

                }
            }
        });


        // 开始同步
        MatrixClient.getInstance().getRooms().synn(SyncRequest.builder().build());

        MatrixClient.getInstance().getUser().getRoomList(new MatrixCallBack<List<String>, Throwable>() {
            @Override
            public void onSuccess(List<String> strings) {

                Log.e(TAG, "onSuccess: " + strings.toString());

                for (String it : strings) {

                    Log.e(TAG, "onSuccess: " + it);

                    MatrixClient.getInstance().getRooms().getRoom(it, new MatrixCallBack<IRoom, Throwable>() {
                        @Override
                        public void onSuccess(IRoom iRoom) {


                            iRoom.getRoomAliases(null);


                            iRoom.getJoinedMembers(new MatrixCallBack<List<RoomJoinedUserInfo>, Throwable>() {
                                @Override
                                public void onSuccess(List<RoomJoinedUserInfo> roomJoinedUserInfos) {
                                    Log.e(TAG, "onSuccess: " + roomJoinedUserInfos.toString());


                                }

                                @Override
                                public void onFailure(Throwable throwable) {

                                }
                            });

                            Log.e(TAG, "onSuccess: " + iRoom.getRoomId());

                            // 发送消息
                            iRoom.send(MessageModel.builder().content(MessageModel.MessageContent.builder().msgtype(MessageModel.MessageContent.MessageType.M_TEXT).body("Heloo").build()).eventType(MessageModel.RoomEventType.M_ROOM_MESSAGE).build(), new MatrixCallBack() {
                                @Override
                                public void onSuccess(Object o) {

                                    Log.e(TAG, "onSuccess: " + o.getClass().getSimpleName());
                                    Log.e(TAG, "onSuccess: " + Modules.getUtilsModule().getGsonUtils().toJson(o));

                                }


                                @Override
                                public void onFailure(Object o) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Throwable throwable) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {


        }
    }


    private void showFragment(Fragment fragment) {

        for (String key : hashMapFragments.keySet()) {
            fragmentManager.beginTransaction().hide(hashMapFragments.get(key)).commit();
        }

        fragmentManager.beginTransaction().show(fragment).commit();
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
        StatusBarUtil.setLightMode(this);
    }
}

package org.ms.matrix.app.ui.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import org.ms.matrix.app.R;
import org.ms.matrix.app.db.messagelist.Event;
import org.ms.matrix.app.db.MatrixDbInjection;
import org.ms.matrix.app.ui.fragment.message.MessageFragment;
import org.ms.matrix.app.ui.fragment.my.MyFragment;
import org.ms.matrix.app.ui.fragment.room.RoomFragment;
import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.matrix.sdk.model.MessageModel;
import org.ms.matrix.sdk.model.RoomJoinedUserInfo;
import org.ms.matrix.sdk.model.SyncModel;
import org.ms.matrix.sdk.model.rooms.Content;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.matrix.sdk.supper.inter.listener.MatrixListener;
import org.ms.matrix.sdk.supper.inter.room.IRoom;
import org.ms.matrix.sdk.utils.SinceUtils;
import org.ms.module.base.view.BaseAppCompatActivity;
import org.ms.module.base.view.StatusBarUtil;
import org.ms.module.supper.client.Modules;
import org.ms.view.navigation.NavigationView;
import org.ms.view.navigation.NavigationViewAdapter;
import org.ms.view.navigation.NavigationViewItem;

import java.util.ArrayList;
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
            public void onEvent(org.ms.matrix.sdk.model.rooms.Event event) {
                Log.e(TAG, "onEvent: " + Modules.getUtilsModule().getGsonUtils().toJson(event));
                String type = event.getType();

                // 消息类型
                if ("m.room.message".equals(type)) {
                    Content content = event.getContent();

                    // 获取消息类型
                    String msgtype = content.getMsgtype();

                    // 文本消息
                    if ("m.text".equals(msgtype)) {

                        // 消息内容
                        String body = content.getBody();


                        Event messageList = Event.builder()
                                ._room_id(event.getRoomId())
                                ._event_id(event.getEvent_id())
                                ._message_type(event.getType())
                                ._sender(event.getSender())
                                ._message_content(event.getContent().getBody())
                                ._origin_server_ts(event.getOrigin_server_ts())
                                .build();


                        Log.e(TAG, "onEvent: 插入数据库");
                        MatrixDbInjection.providerEventDataSource().insert(messageList);


                    }
                }
            }
        });

        MatrixClient.getInstance().getRooms().synn(SyncModel.builder().since(SinceUtils.since()).build());


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

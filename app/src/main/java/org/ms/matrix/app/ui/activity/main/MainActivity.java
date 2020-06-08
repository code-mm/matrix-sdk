package org.ms.matrix.app.ui.activity.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import org.ms.matrix.app.R;
import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.matrix.sdk.model.MessageModel;
import org.ms.matrix.sdk.model.SyncModel;
import org.ms.matrix.sdk.model.rooms.Event;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.matrix.sdk.supper.inter.listener.MatrixListener;
import org.ms.matrix.sdk.supper.inter.room.IRoom;
import org.ms.matrix.sdk.utils.SinceUtils;
import org.ms.module.base.view.BaseAppCompatActivity;
import org.ms.module.supper.client.Modules;

import java.util.List;

public class MainActivity extends BaseAppCompatActivity<MainActivityPresenter> implements IMainActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

        recyclerView = findView(R.id.recyclerView);

    }

    @Override
    protected MainActivityPresenter initPresenter() {
        return new MainActivityPresenter(this);
    }


    private RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        MatrixClient.getInstance().addListener(new MatrixListener() {
            @Override
            public void onEvent(Event event) {


                Log.e(TAG, "onEvent: " + Modules.getUtilsModule().getGsonUtils().toJson(event));

            }

            @Override
            public void onRooms() {

            }

            @Override
            public void onPresence() {

            }

            @Override
            public void onAccountData() {

            }

            @Override
            public void onToDevice() {

            }

            @Override
            public void onDeviceLists() {

            }

            @Override
            public void onDeviceOneTimekeysCount() {

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
}

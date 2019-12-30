package org.ms.sdk.test.matrix;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import org.ms.matrix.test.R;
import org.ms.module.supper.client.Modules;
import org.ms.sdk.matrix.db.message.MatrixMessage;
import org.ms.sdk.matrix.db.message.MatrixMessageInjection;
import org.ms.sdk.matrix.db.room.MatrixRoom;
import org.ms.sdk.matrix.db.room.MatrixRoomInjection;
import org.ms.sdk.matrix.db.user.MatrixUser;
import org.ms.sdk.matrix.db.user.MatrixUserInjection;

import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";


    private Button buttonSync;
    private Button buttonMessage;
    private Button buttonRoomList;
    private Button buttonUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Modules.getControlSwitch().setRequestLog(true);
        Modules.getControlSwitch().setLogOut(true);
    }

    @Override
    protected void initView() {
        buttonSync = findViewById(R.id.buttonSync);
        buttonSync.setOnClickListener(this);

        buttonMessage = findViewById(R.id.buttonMessage);
        buttonMessage.setOnClickListener(this);

        buttonRoomList = findViewById(R.id.buttonRoomList);
        buttonRoomList.setOnClickListener(this);

        buttonUserInfo = findViewById(R.id.buttonUserInfo);
        buttonUserInfo.setOnClickListener(this);
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

                // 开始同步
                Modules.getMatrixModule().startSync();

                break;

            case R.id.buttonMessage:

                Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("查询消息");

                        List<MatrixMessage> matrixMessages = MatrixMessageInjection.provideDataDataSource().getMessages();
                        for (MatrixMessage it : matrixMessages) {
                            System.out.println(Modules.getUtilsModule().getGsonUtils().toJson(it));
                        }
                    }
                });
                break;

            case R.id.buttonRoomList:
                Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("查询房间列表");

                        List<MatrixRoom> rooms = MatrixRoomInjection.provideDataDataSource().getRooms();
                        for (MatrixRoom it : rooms) {
                            System.out.println(it.getRoomId());
                        }
                    }
                });
                break;

            case R.id.buttonUserInfo:
                System.out.println("查询用户信息");

                LiveData<MatrixUser> lastUser =
                        MatrixUserInjection.provideDataDataSource().getLastUser();
                lastUser.observeForever(new Observer<MatrixUser>() {
                    @Override
                    public void onChanged(@Nullable MatrixUser matrixUser) {
                        System.out.println(matrixUser.getUsername());
                    }
                });

                break;
        }
    }
}

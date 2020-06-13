package org.ms.matrix.app.ui.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;
import org.ms.matrix.app.R;
import org.ms.matrix.app.db.MatrixDbInjection;
import org.ms.matrix.app.db.event.Event;
import org.ms.matrix.app.ui.activity.videocall.VideoCallActivity;
import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.matrix.sdk.model.event.m_call_candidates;
import org.ms.matrix.sdk.model.event.m_call_invite;
import org.ms.matrix.sdk.model.event.m_text;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.matrix.sdk.supper.inter.room.IRoom;
import org.ms.module.base.view.BaseAppCompatActivity;
import org.ms.module.supper.client.Modules;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseAppCompatActivity<ChatActivityPresenter> implements IChatActivity {


    private static final String TAG = "ChatActivity";

    private EditText editText;
    private Button buttonSend;


    private RecyclerView recyclerViewMessage;

    private RecyclerViewMessageAdapter recyclerViewMessageAdapter;

    private ChatActivityViewModel chatActivityViewModel;

    private List<MessageBean> messageListList = new ArrayList<>();


    private RelativeLayout relativeLayoutRoot;

    private Button buttonVideoCall;


    @Override
    protected int getLayout() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView() {
        super.initView();

        editText = findViewById(R.id.editText);
        buttonSend = findViewById(R.id.buttonSend);
        recyclerViewMessage = findViewById(R.id.recyclerViewMessage);
        relativeLayoutRoot = findViewById(R.id.relativeLayoutRoot);
        buttonVideoCall = findViewById(R.id.buttonVideoCall);
    }


    private IRoom room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatActivityViewModel = new ViewModelProvider(this).get(ChatActivityViewModel.class);
        String roomid = getIntent().getStringExtra("roomid");
        Modules.getUtilsModule().getToastUtils().show(roomid + "");
        String content = getIntent().getStringExtra("content");


        m_call_invite.Invtie invtie = Modules.getUtilsModule().getGsonUtils().fromJson(content,
                m_call_invite.Invtie.class);
        String call_id = invtie.getCall_id();


        Modules.getUtilsModule().getThreadPoolUtils().runSubThread(new Runnable() {
            @Override
            public void run() {

                List<Event> events = MatrixDbInjection.providerEventDataSource().queryEventByType("m.call.candidates");

                for (Event event : events) {
                    String content1 = event.get_content();

                    try {
                        JSONObject jsonObject = new JSONObject(content1);
                        String call_id1 = jsonObject.getString("call_id");

                        if (call_id1.equals(call_id)) {

                            m_call_candidates.Candidates candidates = Modules.getUtilsModule().getGsonUtils().fromJson(content1, m_call_candidates.Candidates.class);


                            List<m_call_candidates.Candidate> candidates1 = candidates.getCandidates();


                            for(m_call_candidates.Candidate it : candidates1)
                            {


                            }

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });


        MatrixDbInjection.providerEventDataSource().liveDataEventByRoomId(roomid)
                .observe(this, new Observer<List<Event>>() {
                    @Override
                    public void onChanged(List<Event> events) {
                        Log.e(TAG, "onChanged: " + Modules.getUtilsModule().getGsonUtils().toJson(events));

                        if (events != null) {
                            messageListList.clear();
                            for (Event event : events) {

                                String sender = event.get_sender();

                                String type = event.get_type();


                                if ("m.room.message".equals(type)) {
                                    String content = event.get_content();

                                    try {
                                        JSONObject jsonObject = new JSONObject(content);
                                        String msgtype = jsonObject.getString("msgtype");
                                        String body = jsonObject.getString("body");

                                        messageListList.add(MessageBean.builder().msgtype(msgtype).content(body).sender(sender).build());

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                } else if ("m.call.invite".equals(type)) {
                                    String content = event.get_content();
                                    messageListList.add(MessageBean.builder().msgtype("m.call.invite").content(content).sender(sender).build());

                                }

                            }


                            if (recyclerViewMessageAdapter == null) {
                                recyclerViewMessageAdapter = new RecyclerViewMessageAdapter(ChatActivity.this, messageListList, roomid);
                                recyclerViewMessage.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                                recyclerViewMessage.setAdapter(recyclerViewMessageAdapter);


                            } else {
                                recyclerViewMessageAdapter.notifyDataSetChanged();
                                recyclerViewMessage.scrollToPosition(messageListList.size() - 1);
                            }
                        }
                    }
                });


        buttonVideoCall
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ChatActivity.this, VideoCallActivity.class);
                        intent.putExtra("roomid", roomid);
                        startActivity(intent);

                    }
                });


        MatrixClient.getInstance().getRooms().getRoom(roomid, new MatrixCallBack<IRoom, Throwable>() {
            @Override
            public void onSuccess(IRoom iRoom) {
                room = iRoom;
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (room != null) {
                    Modules.getUtilsModule().getToastUtils().show("start");
                    room.sendText(m_text.builder()
                                    .msgtype("m.text")
                                    .body(editText.getText().toString().trim())
                                    .build(),

                            new MatrixCallBack() {
                                @Override
                                public void onSuccess(Object o) {

                                }

                                @Override
                                public void onFailure(Object o) {

                                }
                            });
                } else {
                    Modules.getUtilsModule().getToastUtils().show("房间未null");
                }

            }
        });


    }
}

package org.ms.matrix.app.ui.activity.chat;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ms.matrix.app.App;
import org.ms.matrix.app.R;
import org.ms.matrix.app.ui.activity.videocall.AcceptVideoCallActivity;
import org.ms.module.supper.client.Modules;

import java.util.List;

public class RecyclerViewMessageAdapter extends RecyclerView.Adapter {


    private String roomId;

    private Context context;

    private List<MessageBean> messageBeanList;


    public RecyclerViewMessageAdapter(Context context, List<MessageBean> messageBeanList, String roomId) {
        this.context = context;
        this.messageBeanList = messageBeanList;
        this.roomId = roomId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch (viewType) {
            case 0:
                return new ViewHolderMe(View.inflate(context, R.layout.item_recycler_view_message_me_m_text, null));
            case 1:
                return new ViewHolderMeVideCall(View.inflate(context, R.layout.item_recycler_view_message_me_m_call_invite, null));
            case 2:
                return new ViewHolderOtherPeople(View.inflate(context, R.layout.item_recycler_view_message_otherpeople_m_text, null));
            case 3:
                return new ViewHolderOtherVideoCall(View.inflate(context, R.layout.item_recycler_view_message_otherpeople_m_call_invite, null));
        }


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);


        if (itemViewType == 0) {
            ViewHolderMe holder1 = (ViewHolderMe) holder;
            holder1.textViewContent.setText(messageBeanList.get(position).getContent());
        } else if (itemViewType == 1) {

        } else if (itemViewType == 2) {
            ViewHolderOtherPeople holder1 = (ViewHolderOtherPeople) holder;
            holder1.textViewContent.setText(messageBeanList.get(position).getContent());
        } else if (itemViewType == 3) {
            ViewHolderOtherVideoCall holder1 = (ViewHolderOtherVideoCall) holder;
            holder1.buttonAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Modules.getUtilsModule().getToastUtils().show("同意");


                    Intent intent = new Intent(context, AcceptVideoCallActivity.class);
                    intent.putExtra("roomid", roomId);
                    intent.putExtra("content", messageBeanList.get(position).getContent());
                    context.startActivity(intent);

                }
            });

            ((ViewHolderOtherVideoCall) holder).buttonRefuse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Modules.getUtilsModule().getToastUtils().show("拒绝");
                }
            });
        }


    }

    @Override
    public int getItemViewType(int position) {

        MessageBean messageBean = messageBeanList.get(position);

        if (messageBean.getSender().equals(App.userId)) {

            if (messageBean.getMsgtype().equals("m.text")) {

                return 0;

            } else if (messageBean.getMsgtype().equals("m.call.invite")) {

                return 1;

            }


        } else {
            if (messageBean.getMsgtype().equals("m.text")) {

                return 2;

            } else if (messageBean.getMsgtype().equals("m.call.invite")) {

                return 3;
            }
        }

        return 0;
    }

    @Override
    public int getItemCount() {
        return messageBeanList.size();
    }

    static class ViewHolderMe extends RecyclerView.ViewHolder {

        private TextView textViewContent;

        public ViewHolderMe(@NonNull View itemView) {
            super(itemView);
            textViewContent = itemView.findViewById(R.id.textViewContent);
        }
    }

    static class ViewHolderOtherPeople extends RecyclerView.ViewHolder {
        private TextView textViewContent;

        public ViewHolderOtherPeople(@NonNull View itemView) {
            super(itemView);
            textViewContent = itemView.findViewById(R.id.textViewContent);
        }
    }

    static class ViewHolderOtherVideoCall extends RecyclerView.ViewHolder {

        private Button buttonAccept;
        private Button buttonRefuse;

        public ViewHolderOtherVideoCall(@NonNull View itemView) {
            super(itemView);

            buttonAccept = itemView.findViewById(R.id.buttonAccept);
            buttonRefuse = itemView.findViewById(R.id.buttonRefuse);
        }
    }

    static class ViewHolderMeVideCall extends RecyclerView.ViewHolder {

        public ViewHolderMeVideCall(@NonNull View itemView) {
            super(itemView);
        }
    }


}
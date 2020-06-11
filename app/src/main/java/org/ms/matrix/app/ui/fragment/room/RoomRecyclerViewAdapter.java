package org.ms.matrix.app.ui.fragment.room;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ms.matrix.app.R;
import org.ms.matrix.app.db.matrixroom.MatrixRoom;

import java.util.List;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<MatrixRoom> matrixRoomList;

    public RoomRecyclerViewAdapter(Context context, List<MatrixRoom> matrixRoomList) {
        this.context = context;
        this.matrixRoomList = matrixRoomList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = View.inflate(context, R.layout.item_recycler_view_room_list_adapter, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewRoomName.setText(matrixRoomList.get(position).get_room_name());
    }

    @Override
    public int getItemCount() {
        return matrixRoomList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageViewAvatar;
        private TextView textViewRoomName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            textViewRoomName = itemView.findViewById(R.id.textViewRoomName);
        }
    }
}

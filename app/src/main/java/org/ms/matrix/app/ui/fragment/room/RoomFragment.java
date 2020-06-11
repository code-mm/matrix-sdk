package org.ms.matrix.app.ui.fragment.room;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.ms.matrix.app.R;
import org.ms.matrix.app.db.MatrixDbInjection;
import org.ms.matrix.app.db.matrixroom.MatrixRoom;
import org.ms.module.base.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends BaseFragment<RoomFragmentPresenter> implements IRoomFragment {


    public static RoomFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    
    private RecyclerView recyclerView;

    private RoomFragmentViewModel roomFragmentViewModel;

    private List<MatrixRoom> matrixRoomList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_room;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView = findView(R.id.recyclerView);
    }

    @Override
    protected RoomFragmentPresenter initPresenter() {
        return new RoomFragmentPresenter(this);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomFragmentViewModel = new ViewModelProvider(this).get(RoomFragmentViewModel.class);

        roomFragmentViewModel.getMatrixRoomMutableLiveData().observe(this, new Observer<List<MatrixRoom>>() {
            @Override
            public void onChanged(List<MatrixRoom> matrixRoom) {
                if (matrixRoom != null) {


                }
            }
        });


        MatrixDbInjection.providerMatrixRoomLocalDataSource().queryMatrixRooms().observe(this, new Observer<List<MatrixRoom>>() {
            @Override
            public void onChanged(List<MatrixRoom> matrixRooms) {
                roomFragmentViewModel.getMatrixRoomMutableLiveData().postValue(matrixRooms);


            }
        });


    }
}

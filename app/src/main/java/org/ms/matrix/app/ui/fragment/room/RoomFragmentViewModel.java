package org.ms.matrix.app.ui.fragment.room;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ms.matrix.app.db.matrixroom.MatrixRoom;

import java.util.List;

public class RoomFragmentViewModel extends ViewModel {

    private MutableLiveData<List<MatrixRoom>> matrixRoomMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<MatrixRoom>> getMatrixRoomMutableLiveData() {
        return matrixRoomMutableLiveData;
    }
}

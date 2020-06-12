package org.ms.matrix.app.ui.fragment.message;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ms.matrix.app.db.event.Event;

import java.util.List;

public class MessageFragmentViewModel extends ViewModel {



    private MutableLiveData<List<Event>> eventMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<List<Event>> getEventMutableLiveData() {
        return eventMutableLiveData;
    }
}

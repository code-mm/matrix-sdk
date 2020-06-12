package org.ms.matrix.app.ui.fragment.message;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.ms.matrix.app.R;
import org.ms.matrix.app.db.MatrixDbInjection;
import org.ms.matrix.app.db.event.Event;
import org.ms.module.base.view.BaseFragment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MessageFragment extends BaseFragment<MessageFragmentPresenter> implements IMessageFragment {

    private RecyclerView recyclerView;


    private MessageFragmentViewModel messageFragmentViewModel;


    private MessageRecyclerViewAdapter messageRecyclerViewAdapter;


    private List<Event> eventList = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.fragment_message;

    }

    @Override
    protected void initView() {
        super.initView();

        recyclerView = findView(R.id.recyclerView);

    }

    public static MessageFragment newInstance() {

        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public static List<Event> removeRepeatEvent(List<Event> aDrivers) {
        Set<Event> set = new TreeSet<Event>(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return -1;
                }
                if (o1.get_room_id() == null) {
                    return -1;
                }
                if (o2.get_room_id() == null) {
                    return -1;
                }
                return o1.get_room_id().compareTo(o2.get_room_id());
            }
        });
        set.addAll(aDrivers);
        return new ArrayList<Event>(set);
    }


    private static final String TAG = "MessageFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        messageFragmentViewModel = new ViewModelProvider(this).get(MessageFragmentViewModel.class);

        messageFragmentViewModel.getEventMutableLiveData().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {


                Log.e(TAG, "onChanged: messageFragmentViewModel " + events.size());

                if (events != null) {

                    eventList.clear();
                    eventList.addAll(removeRepeatEvent(events));


                    if (messageRecyclerViewAdapter == null) {
                        messageRecyclerViewAdapter = new MessageRecyclerViewAdapter(getActivity(), eventList);

                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(messageRecyclerViewAdapter);

                    } else {
                        messageRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        MatrixDbInjection.providerEventDataSource().liveDataMessage().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                Log.e(TAG, "onChanged: " + events.toString());
                messageFragmentViewModel.getEventMutableLiveData().postValue(events);
            }
        });
    }
}
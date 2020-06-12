package org.ms.matrix.app.ui.fragment.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import org.ms.matrix.app.R;
import org.ms.matrix.app.ui.activity.webrtc.WebRtcActivity;
import org.ms.module.base.view.BaseFragment;


public class MyFragment extends BaseFragment {

    private Button buttonWebRTC;

    @Override
    protected void initView() {
        super.initView();


        buttonWebRTC = (Button) findView(R.id.buttonWebRTC);
    }

    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        buttonWebRTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WebRtcActivity.class));
            }
        });
    }
}

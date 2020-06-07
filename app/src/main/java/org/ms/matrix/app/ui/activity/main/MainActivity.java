package org.ms.matrix.app.ui.activity.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;


import org.ms.matrix.app.R;
import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.matrix.sdk.supper.inter.callback.MatrixCallBack;
import org.ms.module.base.view.BaseAppCompatActivity;

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
    }

    @Override
    protected MainActivityPresenter initPresenter() {
        return new MainActivityPresenter(this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        MatrixClient.getInstance().getUser().getRoomList(new MatrixCallBack<List<String>, Throwable>() {
            @Override
            public void onSuccess(List<String> strings) {


                Log.e(TAG, "onSuccess: "+strings.toString() );

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });



    }
}

package org.ms.matrix.app.ui.activity.main;

import android.os.Bundle;

import androidx.annotation.Nullable;


import org.ms.matrix.app.R;
import org.ms.module.base.view.BaseAppCompatActivity;

public class MainActivity extends BaseAppCompatActivity<MainActivityPresenter> implements IMainActivity {
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




    }
}

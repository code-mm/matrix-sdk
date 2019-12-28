package org.ms.sdk.test.matrix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.ms.matrix.test.R;
import org.ms.module.base.view.BaseAppCompatActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this,LoginActivity.class));
        finish();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }
}

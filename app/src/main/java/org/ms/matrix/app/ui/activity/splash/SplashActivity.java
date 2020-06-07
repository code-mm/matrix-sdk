package org.ms.matrix.app.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.ms.matrix.app.R;
import org.ms.matrix.app.ui.activity.login.LoginActivity;
import org.ms.module.base.view.BaseAppCompatActivity;

public class SplashActivity extends BaseAppCompatActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}

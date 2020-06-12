package org.ms.matrix.app.ui.activity.splash;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import org.ms.matrix.app.R;
import org.ms.matrix.app.ui.activity.login.LoginActivity;
import org.ms.module.base.view.BaseAppCompatActivity;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.common.CallBack;

public class SplashActivity extends BaseAppCompatActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Modules.getPermissionModule().request(this, new CallBack() {
            @Override
            public void onSuccess(Object o) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Object o) {

            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);
    }
}

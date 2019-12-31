package org.ms.sdk.test.matrix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.ms.matrix.test.R;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.common.CallBack;
import org.ms.module.supper.inter.common.ICallBack;
import org.ms.module.supper.inter.net.Response;
import org.ms.sdk.matrix.MatrixCallBack;
import org.ms.sdk.matrix.db.user.MatrixUser;
import org.ms.sdk.matrix.db.user.MatrixUserInjection;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";


    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Modules.getMatrixModule().setHomeServer("https://www.mhw828.com/");

        Modules.getControlSwitch().setPrintStackTrace(true);
        Modules.getControlSwitch().setRequestLog(true);
        Modules.getControlSwitch().setLogOut(true)

        ;
        buttonLogin = findViewById(R.id.buttonLogin);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonLogin:

                final String username = editTextUsername.getText().toString();
                final String password = editTextPassword.getText().toString();
                showDialog();
                Modules.getMatrixModule().login(username, password, new MatrixCallBack() {
                    @Override
                    public void onSuccess(Object o) {
                        hideDialog();
                        Modules.getUtilsModule().getToastUtils().show("登录成功");
                        baseStart(MainActivity.class);
                        finish();
                    }
                });

                break;
        }
    }
}

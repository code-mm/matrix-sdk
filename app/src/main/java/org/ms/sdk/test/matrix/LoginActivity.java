package org.ms.sdk.test.matrix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.ms.matrix.test.R;
import org.ms.module.base.view.BaseAppCompatActivity;
import org.ms.module.dialog.ui.widget.progress.UIProgressDialog;
import org.ms.module.supper.client.Modules;
import org.ms.module.supper.inter.net.Response;
import org.ms.sdk.matrix.Matrix;
import org.ms.sdk.matrix.MatrixCallBack;
import org.ms.sdk.matrix.client.MatrixClient;
import org.ms.sdk.matrix.db.user.User;
import org.ms.sdk.matrix.inter.IMatrix;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";


    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;


    MatrixClient matrixClient;
    IMatrix matrix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        matrixClient = MatrixClient.Builder.builder().setHomeServer("https://matrix-client.matrix.org/").build();
        matrix = matrixClient.matrix();


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

                matrix.login(username, password, new MatrixCallBack() {

                    @Override
                    public void onSuccess(Object o) {
                        hideDialog();
                        Response response = (Response) o;
                        Modules.getLogModule().e(TAG, response.code + "");
                        Modules.getLogModule().e(TAG, response.body);

                        if (response.code == 200) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body);
                                String user_id = jsonObject.getString("user_id");
                                String access_token = jsonObject.getString("access_token");
                                String home_server = jsonObject.getString("home_server");
                                String device_id = jsonObject.getString("device_id");

                                Modules.getDataModule().setUserId(user_id);
                                Modules.getDataModule().setHomeServer(home_server);
                                Modules.getDataModule().setDeviceId(device_id);
                                Modules.getDataModule().setDeviceId(device_id);
                                Modules.getDataModule().setAccessToken(access_token);


                                User user = new User();
                                user.setUserId(user_id);
                                user.setUsername(username);
                                user.setPassword(password);
                                user.setBaseUrl("https://www.mhw828.com/");
                                user.setDeviceId(device_id);
                                user.setHomeServer(home_server);


                                Modules.getUtilsModule().getToastUtils().show("登录成功");


                                baseStart(MainActivity.class);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else if (response.code == 429) {


                        }


                    }

                    @Override
                    public void onFailure(Object o) {
                        Modules.getUtilsModule().getToastUtils().show("onFailure");
                    }

                    @Override
                    public void onException(Object o) {
                        Modules.getUtilsModule().getToastUtils().show("onException");
                    }
                });

                break;


        }

    }
}

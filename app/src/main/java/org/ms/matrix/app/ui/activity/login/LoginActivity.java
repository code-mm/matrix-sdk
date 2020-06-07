package org.ms.matrix.app.ui.activity.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.ms.matrix.app.R;
import org.ms.matrix.app.db.User;
import org.ms.matrix.app.ui.activity.main.MainActivity;
import org.ms.module.base.view.BaseAppCompatActivity;
import org.ms.module.base.view.StatusBarUtil;
import org.ms.module.supper.client.Modules;

@RequiresApi(api = Build.VERSION_CODES.N)
public class LoginActivity extends BaseAppCompatActivity<LoginActivityPresenter> implements ILoginActivity, View.OnClickListener {

    @Override
    protected LoginActivityPresenter initPresenter() {
        return new LoginActivityPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }


    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button buttonLogin;


    private ImageView imageViewUsername;
    private ImageView imageViewPassword;


    private LinearLayout relativeLayoutForgetPassword;
    private LinearLayout linearLayoutRegistered;


    private LoginActivityViewModel loginActivityViewModel;


    @Override
    protected boolean isFullScreen() {
        return false;
    }


    @Override
    protected void initView() {

        editTextUsername = findView(R.id.editTextUsername);
        editTextPassword = findView(R.id.editTextPassword);
        buttonLogin = findView(R.id.buttonLogin);
        imageViewUsername = findView(R.id.imageViewUsername);
        imageViewPassword = findView(R.id.imageViewPassword);
        linearLayoutRegistered = findView(R.id.linearLayoutRegistered);
        relativeLayoutForgetPassword = findView(R.id.relativeLayoutForgetPassword);

        buttonLogin.setOnClickListener(this);
        linearLayoutRegistered.setOnClickListener(this);
        relativeLayoutForgetPassword.setOnClickListener(this);


        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().length() > 0) {
                    imageViewUsername.setImageResource(R.drawable.image_user_1);
                } else {
                    imageViewUsername.setImageResource(R.drawable.image_user_0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    imageViewPassword.setImageResource(R.drawable.image_password_1);
                } else {
                    imageViewPassword.setImageResource(R.drawable.image_password_0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        queryLatestUser();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);


        loginActivityViewModel.getUserMutableLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {


                if (user != null) {
                    editTextUsername.setText(user._username);
                    editTextPassword.setText(user._password);

                    login(editTextUsername.getText().toString().trim(), editTextPassword.getText().toString().trim());
                }

            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            // 登录
            case R.id.buttonLogin:
                login(editTextUsername.getText().toString().trim(), editTextPassword.getText().toString().trim());
                break;
            // 注册
            case R.id.linearLayoutRegistered:
                Modules.getUtilsModule().getToastUtils().show("注册");
                break;

            case R.id.relativeLayoutForgetPassword:
                Modules.getUtilsModule().getToastUtils().show("忘记密码");
                break;
        }
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparent(this);
        StatusBarUtil.setLightMode(this);
    }

    @Override
    public void queryLatestUser() {
        presenter.queryLatestUser();
    }

    @Override
    public void queryLatestUserCallBack(User user) {
        loginActivityViewModel.getUserMutableLiveData().postValue(user);
    }

    @Override
    public void login(String username, String password) {
        showDialog();
        presenter.login(username, password);
    }

    @Override
    public void onLoginCallBack() {

        Modules.getUtilsModule().getToastUtils().show("登录成功");

        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}



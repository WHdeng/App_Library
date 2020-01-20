package com.android.discovery.viewtest.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.discovery.viewtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_username)
    EditText et_username;
    @BindView(R.id.login_password)
    EditText et_password;
    @BindView(R.id.login_login)
    Button bt_login;
    @BindView(R.id.layout_rememberPassword)
    RadioButton rb_password;
    @BindView(R.id.login_forgetPassword)
    TextView tv_forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_login, R.id.login_forgetPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                break;
            case R.id.login_forgetPassword:
                break;
        }
    }
}

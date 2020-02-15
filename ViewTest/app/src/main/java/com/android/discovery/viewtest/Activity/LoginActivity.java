package com.android.discovery.viewtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.discovery.viewtest.Bean.LoginBean;
import com.android.discovery.viewtest.Dialog.LoadProgressDialog;
import com.android.discovery.viewtest.Listener.RetrofitListener;
import com.android.discovery.viewtest.Model.UserModel;
import com.android.discovery.viewtest.R;
import com.android.discovery.viewtest.Unit.UserInfoSave;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements RetrofitListener {

    @BindView(R.id.login_username)
    EditText et_username;
    @BindView(R.id.login_password)
    EditText et_password;
    @BindView(R.id.login_login)
    Button bt_login;
    @BindView(R.id.login_forgetPassword)
    TextView tv_forgetPassword;
    @BindView(R.id.login_reg)
    TextView loginReg;

    private UserModel model;
    private LoadProgressDialog dialog;
    private Toast toast;
    private UserInfoSave userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
    }

    @OnClick({R.id.login_login, R.id.login_forgetPassword, R.id.login_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                login();
                break;
            case R.id.login_forgetPassword:
                Intent intent1 = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent1);
                break;
            case R.id.login_reg:
                Intent intent2 = new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void initData() {
        model = new UserModel();

        userInfo = UserInfoSave.getUserInfoSave(this);
    }

    public void login() {
        String name = et_username.getText().toString().trim();
        String passward = et_password.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(passward)) {
            model.UserLogin(name, passward, this);
        } else {
            if (TextUtils.isEmpty(name) && TextUtils.isEmpty(passward)) {
                showToast("用户名、密码为空");
            } else if (TextUtils.isEmpty(name)) {
                showToast("用户名为空");
            } else {
                showToast("密码为空");
            }
        }
    }

    private void showToast(String content) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        toast.show();

    }

    private void showDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        dialog = new LoadProgressDialog(this, "登录中");
        dialog.show();
    }

    @Override
    public void onStarted() {
        showDialog();
    }

    @Override
    public void onFailed(String errorString) {
        dialog.setMessage("登录失败");
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        Log.d("LoginActivity123", errorString);
    }

    @Override
    public void onSuccess(Object o, int flag) {
        if (flag == 1) {
            LoginBean bean = (LoginBean) o;
            if (bean.getMsg().equals("")){
                userInfo.setUserData(bean);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                showToast("登录成功");
            }else {
                if (bean.getMsg().equals("密码错误")){
                    showToast("密码错误");
                } else if (bean.getMsg().equals("用户不存在")){
                    showToast("用户不存在");
                }
            }


        }
    }

    @Override
    public void onFinish() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}

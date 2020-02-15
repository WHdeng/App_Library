package com.android.discovery.viewtest.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.discovery.viewtest.Bean.ReSetPasswordBean;
import com.android.discovery.viewtest.Dialog.LoadProgressDialog;
import com.android.discovery.viewtest.Listener.RetrofitListener;
import com.android.discovery.viewtest.Model.UserModel;
import com.android.discovery.viewtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetActivity extends AppCompatActivity implements RetrofitListener {

    @BindView(R.id.forget_back)
    ImageView forgetBack;
    @BindView(R.id.forget_username)
    EditText forgetUsername;
    @BindView(R.id.forget_userphone)
    EditText forgetUserphone;
    @BindView(R.id.forget_password)
    EditText forgetPassword;
    @BindView(R.id.forget_setPasswrd)
    Button forgetSetPasswrd;
    @BindView(R.id.forget_reSetPassword)
    Button forgetReSetPassword;

    private UserModel model;
    private LoadProgressDialog dialog;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        model = new UserModel();
        ButterKnife.bind(this);
    }

    //修改密码
    private void sendSetPasswordOrder(){
        boolean flag = false;
        String name = forgetUsername.getText().toString().trim();
        String phone = forgetUserphone.getText().toString().trim();
        String password = forgetPassword.getText().toString().trim();

        if (name.isEmpty()){
            showToast("用户名为空");
        } else if (phone.isEmpty()){
            showToast("手机号为空");
        } else if (password.isEmpty()){
            showToast("密码为空");
        } else {
            flag = true;
        }
        if (flag){
            model.UserReSetPassword(name,password,phone,this);
        }
    }

    //重置密码
    private void sendReSetPasswordOrder(){
        boolean flag = false;
        String name = forgetUsername.getText().toString().trim();
        String phone = forgetUserphone.getText().toString().trim();

        if (name.isEmpty()){
            showToast("用户名为空");
        } else if (phone.isEmpty()){
            showToast("手机号为空");
        } else {
            flag = true;
        }
        if (flag){
            model.UserReSetPassword(name,"123456",phone,this);
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
        dialog = new LoadProgressDialog(this, "提交中");
        dialog.show();
    }






    @OnClick({R.id.forget_back, R.id.forget_setPasswrd, R.id.forget_reSetPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_back:
                finish();
                break;
            case R.id.forget_setPasswrd:
                sendSetPasswordOrder();
                break;
            case R.id.forget_reSetPassword:
                sendReSetPasswordOrder();
                break;
        }
    }

    @Override
    public void onStarted() {
        showDialog();
    }

    @Override
    public void onFailed(String errorString) {
        dialog.setMessage("网络错误");
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public void onSuccess(Object o, int flag) {
        ReSetPasswordBean bean = (ReSetPasswordBean) o;
        if (bean.getMsg().equals("修改密码成功")){
            showToast("修改密码成功");
            finish();
        } else if (bean.getMsg().equals("修改密码失败")){
            showToast("修改密码失败");
        } else if (bean.getMsg().equals("手机号错误")){
            showToast("手机号错误");
        } else if (bean.getMsg().equals("无此用户名")){
            showToast("无此用户名");
        } else {
            showToast("其他错误");
        }
    }

    @Override
    public void onFinish() {
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }
}

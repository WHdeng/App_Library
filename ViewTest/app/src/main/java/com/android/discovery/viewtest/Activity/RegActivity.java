package com.android.discovery.viewtest.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.discovery.viewtest.Bean.RegBean;
import com.android.discovery.viewtest.Dialog.LoadProgressDialog;
import com.android.discovery.viewtest.Listener.RetrofitListener;
import com.android.discovery.viewtest.Model.UserModel;
import com.android.discovery.viewtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity implements RetrofitListener {

    @BindView(R.id.reg_back)
    ImageView img_back;
    @BindView(R.id.reg_username)
    EditText et_username;
    @BindView(R.id.reg_password)
    EditText et_password;
    @BindView(R.id.reg_confimPassword)
    EditText et_confimPassword;
    @BindView(R.id.reg)
    Button bt_reg;
    @BindView(R.id.reg_userphone)
    EditText et_userphone;
    @BindView(R.id.reg_userage)
    EditText et_userage;

    private Toast toast;
    private UserModel model;
    private LoadProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        model = new UserModel();
        ButterKnife.bind(this);
    }


    @OnClick({R.id.reg_back, R.id.reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reg_back:
                finish();
                break;
            case R.id.reg:
                sendOrder();
                break;
        }
    }

    private void sendOrder(){
        if (dealData()){
            String name = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            String age = et_userage.getText().toString().trim();
            String phone = et_userphone.getText().toString().trim();
            model.UserReg(name,password,phone,age,this);
        }
    }

    private boolean dealData(){
        String name = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String confimPassword = et_confimPassword.getText().toString().trim();
        String age = et_userage.getText().toString().trim();
        String phone = et_userphone.getText().toString().trim();

        if (name.isEmpty()){
            showToast("用户名为空");
            return false;
        }else if (password.isEmpty()){
            showToast("密码为空");
            return false;
        }else if (confimPassword.isEmpty()){
            showToast("确认密码为空");
            return false;
        }else if (age.isEmpty()){
            showToast("年龄为空");
            return false;
        }else if (phone.isEmpty()){
            showToast("手机号为空");
            return false;
        }else if (!password.equals(confimPassword)){
            showToast("两次密码不一致");
            return false;
        } else return true;

    }

    private void cleanData(){
        et_username.setText("");
        et_password.setText("");
        et_confimPassword.setText("");
        et_userphone.setText("");
        et_userage.setText("");

    }

    private void showToast(String msg){
        if (toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
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

    @Override
    public void onStarted() {
        showDialog();
    }

    @Override
    public void onFailed(String errorString) {
        dialog.setMessage("注册失败");
       if (dialog.isShowing()){
           dialog.dismiss();
       }
    }

    @Override
    public void onSuccess(Object o, int flag) {
        RegBean bean = (RegBean) o;
        if (bean.getMsg().equals("")){
            showToast("登陆成功");
            finish();
        }else {
            showToast(bean.getMsg());
        }

    }

    @Override
    public void onFinish() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        cleanData();
    }
}

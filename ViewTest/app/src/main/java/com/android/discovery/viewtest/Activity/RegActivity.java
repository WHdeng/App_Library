package com.android.discovery.viewtest.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.discovery.viewtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.reg_back, R.id.reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reg_back:
                break;
            case R.id.reg:
                break;
        }
    }
}

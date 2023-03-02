package com.example.videoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.videoapp.R;
import com.example.videoapp.api.Api;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.api.TtitCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;


public class RegisterActivity extends BaseActivity {
    private EditText etAccount, etPwd;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        etAccount = findViewById(R.id.et_account_register);
        etPwd = findViewById(R.id.et_pwd_register);
        btnRegister = findViewById(R.id.btn_register_register);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                register(account, pwd);
            }
        });

    }

    private void register(String account, String pwd) {
        if (TextUtils.isEmpty(account)) {
            showToast("请输入注册账号");
        }
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入注册密码");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("mobile", account);
        map.put("password", pwd);
        Api.config(ApiConfig.REGISTER, map).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(Call call, Response resp, String result) {
                Log.d("onSuccess", result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(result);

                    }
                });
            }

            @Override
            public void onFailure(Call call,Exception e) {

            }
        });
    }
}
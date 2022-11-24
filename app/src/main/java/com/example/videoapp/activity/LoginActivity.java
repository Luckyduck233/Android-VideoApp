package com.example.videoapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.videoapp.MyApplication;
import com.example.videoapp.R;
import com.example.videoapp.api.Api;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.api.TtitCallback;
import com.example.videoapp.entity.LoginResponse;
import com.example.videoapp.sp.MySharedPreferences;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private EditText etAccount, etPwd;
    private Button btnLogin, btnTest, btnTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initView() {
        etAccount = findViewById(R.id.et_account_login);
        etPwd = findViewById(R.id.et_pwd_login);
        btnLogin = findViewById(R.id.btn_login);
        btnTest = findViewById(R.id.btn_test);
        btnTest2 = findViewById(R.id.btn_test2);
    }

    private void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                login(account, pwd);
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreferences
                        .config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, MODE_PRIVATE)
                        .setParam(etAccount.getText().toString(),etPwd.getText().toString());
            }
        });
        btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object object = MySharedPreferences
                        .config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, MODE_PRIVATE)
                        .getParam(etAccount.getText().toString(), "123");
                Log.d(TAG, object.toString());
            }
        });
    }

    private void login(String account, String pwd) {
        if (TextUtils.isEmpty(account)) {
            showToast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入密码");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("mobile", account);
        map.put("password", pwd);
        Api.config("/app/login", map).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("onSuccess", result);
//                showToastAsync(result);
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(result, LoginResponse.class);
                if (loginResponse.getCode() == 0) {
                    String token = loginResponse.getToken();

                    SharedPreferences sp = getSharedPreferences("sp_ttit", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", token);
                    editor.commit();
                    showToastAsync("登录成功");
                } else {
                    String msg = loginResponse.getMsg();
                    showToastAsync(msg);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
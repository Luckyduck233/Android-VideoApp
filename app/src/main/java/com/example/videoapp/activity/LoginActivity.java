package com.example.videoapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private Button btnLogin, btnTest, btnTest2,btnLoginWithoutPassword;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        etAccount = findViewById(R.id.et_account_login);
        etPwd = findViewById(R.id.et_pwd_login);
        btnLogin = findViewById(R.id.btn_login);
        btnTest = findViewById(R.id.btn_test);
        btnTest2 = findViewById(R.id.btn_test2);
        btnLoginWithoutPassword = findViewById(R.id.btn_login_withoutPassword);
        cbRemember = findViewById(R.id.cb_remember);

        boolean isRemember = (boolean) MySharedPreferences
                .config(mContext, "isRemember", MODE_PRIVATE)
                .getParam("isRemember", false);
        if (isRemember) {
            btnLoginWithoutPassword.setVisibility(View.VISIBLE);
        } else {
            btnLoginWithoutPassword.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void initData() {
//        如果记住则显示免密登录
    }

    @Override
    protected void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                boolean isRemember = cbRemember.isChecked();
                if (isRemember) {
                    MySharedPreferences
                            .config(mContext, "isRemember", MODE_PRIVATE)
                            .setParam("isRemember", isRemember);
                } else {
                    MySharedPreferences
                            .config(mContext,"isRemember",MODE_PRIVATE)
                            .clearTargetData("isRemember");
                }
                login(account, pwd);
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = cbRemember.isChecked();
                Log.d(TAG, "cbBox is :"+checked);
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

        btnLoginWithoutPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login2();
            }
        });
    }

    private void login(String account, String pwd) {
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            showToast("请输入账号和密码");
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("mobile", account);
        map.put("password", pwd);
        Api.config(ApiConfig.LOGIN, map).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("onSuccess", result);
//                showToastAsync(result);
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(result, LoginResponse.class);
                if (loginResponse.getCode() == 0) {
                    String token = loginResponse.getToken();

                    MySharedPreferences.config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, MODE_PRIVATE)
                            .setParam("token", token);
                    showToastAsync("登录成功");
                    navigateToWithFlag(HomeActivity.class,Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                } else {
                    String msg = loginResponse.getMsg();
//                    showToastAsync(msg);
                    Log.d(TAG, msg);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void login2() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("mobile", "333666");
        params.put("password", "1");

        Api.config(ApiConfig.LOGIN,params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(String result) {
                LoginResponse response = new Gson().fromJson(result, LoginResponse.class);
                Integer code = response.getCode();
                if (code == 0) {
                    String token = response.getToken();
                    MySharedPreferences
                            .config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, MODE_PRIVATE)
                            .setParam("token", token);
                    showToastAsync("登录成功");
                    navigateToWithFlag(HomeActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                } else {
                    showToastAsync(response.getMsg());
                    showToastAsync("登录失败请输入账号密码登录");
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}
package com.example.videoapp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.videoapp.MyApplication;
import com.example.videoapp.R;
import com.example.videoapp.api.Api;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.api.TtitCallback;
import com.example.videoapp.easyMockEntity.Login.login.MockLoginResponse;
import com.example.videoapp.entity.LoginResponse;
import com.example.videoapp.sp.MySharedPreferences;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private EditText etAccount, etPwd;
    private Button btnLogin, btnTest, btnTest2, btnLoginWithoutPassword;
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
                            .config(mContext, "isRemember", MODE_PRIVATE)
                            .clearTargetData("isRemember");
                }
                login(account, pwd);
            }
        });
        btnTest.setOnClickListener(v->{
            Log.d(TAG, "initEvent: ");
            Window window = getWindow();
            if (window != null) {
                Log.d(TAG, "enter: ");

                Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.alertlog_loading);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.TOP);
                dialog.getWindow().setWindowAnimations(R.style.LoadingAnimation);
                dialog.show();
                try {
                    Thread.sleep(3000);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        map.put("username", account);
        map.put("pwd", pwd);
        Api.config(ApiConfig.EM_LOGIN, map).getRequestEm(mContext, new TtitCallback() {
            @Override
            public void onSuccess(String result) {
                Log.d("onSuccess", result);
//                showToastAsync(result);
                Gson gson = new Gson();
                MockLoginResponse response = gson.fromJson(result, MockLoginResponse.class);
                if (response.getCode() == 200) {
                    String token = response.getContent().getToken();
//                    Log.e("EM", token);
                    String msg = response.getContent().getMsg();
                    if (response.getContent().isIsAccountTrue()) {
                        MySharedPreferences
                                .config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, MODE_PRIVATE)
                                .setParam("token", token);
                        Log.d("EM", msg);
                        showToastAsync("登录成功");
                        navigateToWithFlag(HomeActivity.class,Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    } else {

//                    showToastAsync(msg);
                        Log.e(TAG, msg);
                    }

//                    navigateToWithFlag(HomeActivity.class,Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                }
                else {
                    showToastAsync("访问失败");
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

        Api.config(ApiConfig.LOGIN, params).postRequest(new TtitCallback() {
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
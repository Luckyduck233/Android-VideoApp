package com.example.videoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.videoapp.MyApplication;
import com.example.videoapp.R;
import com.example.videoapp.api.Api;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.api.TtitCallback;
import com.example.videoapp.easyMockEntity.Login.login.MockLoginResponse;
import com.example.videoapp.entity.LoginResponse;
import com.example.videoapp.sp.MySharedPreferences;
import com.example.videoapp.util.CustomPopupWindow;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private EditText etAccount, etPwd;
    private Button btnLogin, btnTest, btnTest2, btnLoginWithoutPassword;
    private CheckBox cbRemember;
    private Timer mTimer;
    private TimerTask mTask;

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
//        btnTest.setOnClickListener(v->{
//            Log.d(TAG, "initEvent: ");
//            Window window = getWindow();
//            if (window != null) {
//                Log.d(TAG, "enter: ");
//
//
//                Dialog dialog = new Dialog(LoginActivity.this);
//                dialog.setContentView(R.layout.alertlog_loading);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                dialog.getWindow().setGravity(Gravity.CENTER);
//                dialog.getWindow().setWindowAnimations(R.style.AnimBottom);
//
//
//
//                CustomTimerUtils customTimerUtils = new CustomTimerUtils(3000, 500,dialog);
//                customTimerUtils.start();
////                try {
////                    Thread.sleep(3000);
////                    dialog.dismiss();
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//            }
//
//
//        });

//        btnTest.setOnClickListener(v->{
//            SystemUtils systemUtils = new SystemUtils();
//            systemUtils.customDialog(LoginActivity.this,
//                    R.layout.alertlog_loading,
//                    R.anim.rotate_loading,
//                    R.style.AnimBottom,
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    Gravity.CENTER
//            );
//        });

//        btnTest.setOnClickListener(v->{
//            if (mTimer == null && mTask == null) {
//                mTimer = new Timer();
//                mTask = new TimerTask(){
//                    @Override
//                    public void run() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
//                    }
//                };
//                mTimer.schedule(mTask,0,1000);
//            }
//        });

//        btnTest.setOnClickListener(v -> {
//            Api.config(ApiConfig.FM_TEST_TIMEOUT, null, 3).getRequestFm(this, new TtitCallback() {
//                @Override
//                public void onSuccess(Call call, Response resp, String result) {
//                    Log.e(TAG,   result);
//                }
//
//                @Override
//                public void onFailure(Call call,Exception e) {
//                    Log.e(TAG, "Time out" + e);
//                    showToastAsync("超时连接");
//                }
//            });
//        });

//        btnTest.setOnClickListener(v->{
////            View view = getLayoutInflater().inflate(R.layout.alertlog_loading, null);
////            PopupWindow popupWindow = new CustomPopupWindow(this,view).setPopupWindow();
////            popupWindow.showAtLocation(view,Gravity.CENTER,0,0);
//
//            CustomPopupWindow customPopupWindow = new CustomPopupWindow(this, R.layout.alertlog_loading);
//            customPopupWindow.setAnimationStyle(R.style.AnimBottom);
//            customPopupWindow.setTargetControlAnimation(R.anim.rotate_loading, R.id.iv_rotateCircle);
//            customPopupWindow.show();
//        });

        btnTest.setOnClickListener(v -> {
//            CustomPopupWindow.Builder builder = CustomPopupWindow.with(mContext)
//                    .setContentView(R.layout.alertlog_loading)
//                    .animationStyle(R.style.AnimBottom);
//            PopupWindow popupWindow = builder.build();
//            popupWindow.getContentView().
            PopupWindow popupWindow = CustomPopupWindow.with(mContext).setTargetControlAnimation(R.id.iv_rotateCircle, R.anim.rotate_loading).build();
            Api.config(ApiConfig.FM_TEST_TIMEOUT, null, 5)
                    .getRequestFm(mContext, new TtitCallback() {
                        @Override
                        public void onSuccess(Call call, Response resp, String result) {
                            if (resp.isSuccessful()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        popupWindow.dismiss();
                                    }
                                });
                            }

                            Log.e(TAG, result);

                        }

                        @Override
                        public void onFailure(Call call, Exception e) {

                        }
                    });
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
            public void onSuccess(Call call, Response resp, String result) {
                Log.d("onSuccess", result);
//                showToastAsync(result);
                Gson gson = new Gson();
                MockLoginResponse loginResponse = gson.fromJson(result, MockLoginResponse.class);
                if (loginResponse.getCode() == 200) {
                    String token = loginResponse.getContent().getToken();
//                    Log.e("EM", token);
                    String msg = loginResponse.getContent().getMsg();
                    if (loginResponse.getContent().isIsAccountTrue()) {
                        MySharedPreferences
                                .config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, MODE_PRIVATE)
                                .setParam("token", token);
                        Log.d("EM", msg);
                        showToastAsync("登录成功");
                        navigateToWithFlag(HomeActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    } else {

//                    showToastAsync(msg);
                        Log.e(TAG, msg);
                    }

//                    navigateToWithFlag(HomeActivity.class,Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                } else {
                    showToastAsync("访问失败");
                }
            }

            @Override
            public void onFailure(Call call, Exception e) {

            }
        });
    }

    private void login2() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("mobile", "333666");
        params.put("password", "1");

        Api.config(ApiConfig.LOGIN, params).postRequest(new TtitCallback() {
            @Override
            public void onSuccess(Call call, Response resp, String result) {
                LoginResponse loginResponse = new Gson().fromJson(result, LoginResponse.class);
                Integer code = loginResponse.getCode();
                if (code == 0) {
                    String token = loginResponse.getToken();
                    MySharedPreferences
                            .config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, MODE_PRIVATE)
                            .setParam("token", token);
                    showToastAsync("登录成功");
                    navigateToWithFlag(HomeActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                } else {
                    showToastAsync(loginResponse.getMsg());
                    showToastAsync("登录失败请输入账号密码登录");
                }
            }

            @Override
            public void onFailure(Call call, Exception e) {

            }
        });

    }
}

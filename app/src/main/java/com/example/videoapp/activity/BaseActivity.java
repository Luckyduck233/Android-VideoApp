package com.example.videoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

abstract public class BaseActivity extends AppCompatActivity {
    public Context mContext;
    private String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initData();
        initEvent();
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToastAsync(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(msg);
            }
        });
    }

    public void navigateTo(Class cls) {
        Log.d(TAG, "navigateTo: ");
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }
}

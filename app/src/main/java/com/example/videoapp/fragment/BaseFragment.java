package com.example.videoapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.videoapp.MyApplication;
import com.example.videoapp.api.ApiConfig;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import xyz.doikki.videoplayer.player.VideoViewManager;

public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    private Unbinder unbinder;
    public static final Context mContext = MyApplication.getContext();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        if (mRootView == null) {
            mRootView = inflater.inflate(initLayout(), container, false);
            initView();
            initListener();
        }
        unbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }


    protected abstract void initView();

    protected abstract void initData();

    protected abstract int initLayout();

    protected abstract void initListener();

    protected void showToast(String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected String getStringFromSp(String key) {
        SharedPreferences sp = getContext().getSharedPreferences(ApiConfig.SP_TOKEN_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public void navigateToWithFlag(Class cls, int Flags) {
        Intent intent = new Intent(mContext, cls);
        intent.setFlags(Flags);
        startActivity(intent);
    }

    public void navigateTo(Class cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 子类可通过此方法直接拿到VideoViewManager
     */
    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }
}

package com.example.videoapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.videoapp.MainActivity;
import com.example.videoapp.MyApplication;
import com.example.videoapp.R;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.databinding.FragmentMyBinding;
import com.example.videoapp.sp.MySharedPreferences;

public class MyFragment extends BaseFragment{
    private Button mBtn_Logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getBinding().logOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TAG", "江家豪牛逼");
//            }
//        });

    }

    @Override
    protected void initView() {
        mBtn_Logout = mRootView.findViewById(R.id.log_out);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initListener() {
        mBtn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreferences
                        .config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, getContext().MODE_PRIVATE)
                        .clearTargetData("token");
                navigateToWithFlag(MainActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
        });
    }

    public MyFragment() {

    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    //    protected int initLayout() {
//        return R.layout.fragment_my;
//    }

//    @Override
//    public void initListener(){
//        binding.skinChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TAG", "江家豪牛逼");
//            }
//        });
//    }
}
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
import android.widget.Toast;

import com.example.videoapp.MainActivity;
import com.example.videoapp.MyApplication;
import com.example.videoapp.R;
import com.example.videoapp.activity.TestActivity;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.databinding.FragmentMyBinding;
import com.example.videoapp.sp.MySharedPreferences;
import com.example.videoapp.util.DialogUtil;
import com.example.videoapp.util.LoadingDialog;
import com.example.videoapp.util.OnConfirmClickListener;

public class MyFragment extends BaseFragment {
    private Button mBtn_Logout;
    private View mBtn_collect;
    private boolean switch1 =false;
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
        mBtn_collect = mRootView.findViewById(R.id.my_collect);

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
        mBtn_Logout.setOnClickListener(v ->
                DialogUtil.showConfirmDialog(getActivity(), "是否退出当前账号", () -> {
                    navigateToWithFlag(MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }));

//        mBtn_collect.setOnClickListener(view -> {
//            LoadingDialog dialog = LoadingDialog.Builder(getActivity(), "正在加载");
//            dialog.show();
//            new Thread(){
//                @Override
//                public void run() {
//                    super.run();
//                    try {
//                        sleep(1000);
//                        dialog.dismiss();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
////            if (switch1) {
////                dialog.show();
////                switch1 = false;
////            } else {
////                dialog.dismiss();
////                switch1 = true;
////            }
//        });
        mBtn_collect.setOnClickListener(v->{
            navigateTo(TestActivity.class);
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
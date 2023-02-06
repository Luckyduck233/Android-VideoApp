package com.example.videoapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videoapp.R;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectFragment extends BaseFragment {
    private SmartRefreshLayout smartRefreshLayoutCollect;


    public CollectFragment() {

    }

    public static CollectFragment newInstance() {
        CollectFragment fragment = new CollectFragment();
        return fragment;
    }


    @Override
    protected void initView() {
        smartRefreshLayoutCollect = mRootView.findViewById(R.id.smartRefreshLayout_collect);
    }

    @Override
    protected void initData() {
        smartRefreshLayoutCollect = mRootView.findViewById(R.id.smartRefreshLayout_collect);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initListener() {

    }
}
package com.example.videoapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videoapp.MyApplication;
import com.example.videoapp.R;
import com.example.videoapp.adapter.HomeAdapter;
import com.example.videoapp.api.Api;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.api.TtitCallback;
import com.example.videoapp.entity.Category.CategoryEntity;
import com.example.videoapp.entity.Category.VideoCategoryResponse;
import com.example.videoapp.sp.MySharedPreferences;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends BaseFragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles ;
//private final String[] mTitles = {"热门", "1", "2","3","4","5","6","7"};
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private HomeAdapter mAdapter;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initView() {
        viewPager = mRootView.findViewById(R.id.fixedViewPager_home);
        slidingTabLayout = mRootView.findViewById(R.id.slidingTabLayout_home);
    }

    @Override
    protected void initData() {
        getVideoCategoryList();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        viewPager.setOffscreenPageLimit(mFragments.size());
        mAdapter = new HomeAdapter(requireActivity().getSupportFragmentManager(), mTitles, mFragments);
        viewPager.setAdapter(mAdapter);
        int count = viewPager.getAdapter().getCount();
        String s = mTitles.toString();
        Log.e("loneyjjh", s);
        slidingTabLayout.setViewPager(viewPager, mTitles);
//        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initListener() {

    }

    private void getVideoCategoryList() {
        String token = (String) MySharedPreferences.config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, getActivity().MODE_PRIVATE)
                .getParam("token", "");
        if (!TextUtils.isEmpty(token)) {
            HashMap<String, Object> params = new HashMap<>();
            Api.config(ApiConfig.VIDEO_CATEGORY_LIST,params)
                    .getRequest(getContext() ,new TtitCallback() {
                        @Override
                        public void onSuccess(String result) {
                            VideoCategoryResponse response = new Gson().fromJson(result, VideoCategoryResponse.class);
                            if (response != null && response.getCode() == 0) {
                                List<CategoryEntity> pageList = response.getPage().getList();
                                if (pageList != null && pageList.size() > 0) {
                                    mTitles = new String[pageList.size()];
                                    for (int i = 0; i < pageList.size(); i++) {
                                        mTitles[i] = pageList.get(i).getCategoryName();
                                        mFragments.add(VideoFragment.newInstance(pageList.get(i).getCategoryId()));
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {

                        }
                    });
        }
    }
}
//
//package com.example.videoapp.fragment;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.viewpager.widget.ViewPager;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.videoapp.MyApplication;
//import com.example.videoapp.R;
//import com.example.videoapp.adapter.HomeAdapter;
//import com.flyco.tablayout.SlidingTabLayout;
//
//import java.util.ArrayList;
//import java.util.Objects;
//
//
//public class HomeFragment extends Fragment {
//    private ArrayList<Fragment> mFragments = new ArrayList<>();
//    private final String[] mTitles = {"热门", "1", "2","3","4","5","6","7"};
//    private ViewPager viewPager;
//    private SlidingTabLayout slidingTabLayout;
//    private HomeAdapter mAdapter;
//
//    public HomeFragment() {
//
//    }
//
//    public static HomeFragment newInstance() {
//        HomeFragment fragment = new HomeFragment();
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        viewPager=  view.findViewById(R.id.fixedViewPager_home);
//        slidingTabLayout = view.findViewById(R.id.slidingTabLayout_home);
//        return view;
//    }
//
//    //    这个方法在onCreateView执行后执行
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        for (String title : mTitles) {
//            mFragments.add(VideoFragment.newInstance(1));
//        }
//        viewPager.setOffscreenPageLimit(mFragments.size());
//        mAdapter = new HomeAdapter(requireActivity().getSupportFragmentManager(), mTitles, mFragments);
//        viewPager.setAdapter(mAdapter);
//        slidingTabLayout.setViewPager(viewPager,mTitles);
//    }
//}
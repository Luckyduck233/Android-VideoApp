package com.example.videoapp.fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.videoapp.R;
import com.example.videoapp.adapter.HomeAdapter;
import com.example.videoapp.api.Api;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.api.TtitCallback;
import com.example.videoapp.easyMockEntity.Login.video.Data;
import com.example.videoapp.easyMockEntity.Login.video.VideoListResponseTest;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeFragment extends BaseFragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;
    private String[] mTabTitle;
    //private final String[] mTitles = {"热门", "1", "2","3","4","5","6","7"};
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private HomeAdapter mAdapter;
    private volatile int on = 0;
    private VideoListResponseTest response;
    private static int mFragmentSize;
    private List<String> mTabList;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initFragment(response);
                    break;
                case 2:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            mHandler.postDelayed(this, 300);
                            viewPager.setOffscreenPageLimit(mFragments.size());
//                            Log.e("99898", mFragments.size()+"" );
//                            Log.e("99898", mTabList.size()+"" );
//                            Log.e("99898", mTabTitle.toString());
                            mAdapter = new HomeAdapter(requireActivity().getSupportFragmentManager(), mTabTitle, mFragments);
                            viewPager.setAdapter(mAdapter);
                            slidingTabLayout.setViewPager(viewPager,mTabTitle);
                            mAdapter.notifyDataSetChanged();
                            Log.e("TAG", mTabTitle[1] );
//                            slidingTabLayout.setViewPager(viewPager,mTabTitle,getActivity(),mFragments);
                        }
                    });
                    break;
            }
        }
    };

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }




    @Override
    protected void initView() {
        viewPager = mRootView.findViewById(R.id.fixedViewPager_home);
        slidingTabLayout = mRootView.findViewById(R.id.slidingTabLayout_home);
//        getVideoListTest();
//        try {
//            Thread.currentThread().sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    protected void initData() {
        getVideoListTest();
        try {
            Thread.currentThread().sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] mTitles1 = {"热门", "1", "2", "3", "4", "5", "6", "7"};

//        String s = mTitles.toString();
//        Log.e("loneyjjh", s);
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    slidingTabLayout.setViewPager(viewPager, mTitles);
//                } catch (Exception e) {
//                    Log.e("TAG", e.getMessage() );
//                }
//            }
//        });

//        slidingTabLayout.setViewPager(viewPager);
    }

    private void getVideoListTest() {
        HashMap<String, Object> map = new HashMap<>();
//        map.put("page", 1);
        Api.config(ApiConfig.EM_TEST, map)
                .getRequestEm(mContext, new TtitCallback() {
                    @Override
                    public void onSuccess(String result) {
                        response = new Gson().fromJson(result, VideoListResponseTest.class);
                        String s = response.toString();
                        Log.e("101010", s );
                        if (response != null) {
                            mHandler.sendEmptyMessage(1);
                        }
                    }
                    @Override
                    public void onFailure(Exception e) {

                    }
                });
    }

    private void initFragment(@NonNull VideoListResponseTest response) {
        List<Data> list = response.getData();
        mTabList = response.getTopTab();
//        int fragmentSize = list.size();
        Log.e("EM1", list.size() + "");
        mFragmentSize = response.getTopTab().size();
        mTabTitle = new String[mFragmentSize];
        Log.e("jjhdhj", mTabTitle.length+"" );

//        Log.e("popop", String.valueOf(mTabList));
        for (int i = 0; i < mFragmentSize; i++) {
            mTabTitle[i] = mTabList.get(i);
            mFragments.add(VideoFragment.newInstance(i));
        }
        Log.e("jjhdhj", mTabTitle.length+"" );
        Log.e("jjhdhj", mTabTitle[1] );
        on = 1;

        mHandler.sendEmptyMessage(2);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initListener() {

    }

//    private void getVideoCategoryList() {
//        String token = (String) MySharedPreferences.config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, getActivity().MODE_PRIVATE)
//                .getParam("token", "");
//        if (!TextUtils.isEmpty(token)) {
//            HashMap<String, Object> params = new HashMap<>();
//            Api.config(ApiConfig.VIDEO_CATEGORY_LIST,params)
//                    .getRequestEm(getContext() ,new TtitCallback() {
//                        @Override
//                        public void onSuccess(String result) {
//                            VideoCategoryResponse response = new Gson().fromJson(result, VideoCategoryResponse.class);
//                            if (response != null && response.getCode() == 0) {
//                                List<CategoryEntity> pageList = response.getPage().getList();
//                                if (pageList != null && pageList.size() > 0) {
//                                    mTitles = new String[pageList.size()];
//                                    for (int i = 0; i < pageList.size(); i++) {
//                                        mTitles[i] = pageList.get(i).getCategoryName();
//                                        mFragments.add(VideoFragment.newInstance(pageList.get(i).getCategoryId()));
//                                    }
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Exception e) {
//
//                        }
//                    });
//        }
//    }
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
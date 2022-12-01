package com.example.videoapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videoapp.MyApplication;
import com.example.videoapp.R;
import com.example.videoapp.adapter.HomeAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"热门", "1", "2"};
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager=  view.findViewById(R.id.fixedViewPager_home);
        slidingTabLayout = view.findViewById(R.id.slidingTabLayout_home);
        return view;
    }

    //    这个方法在onCreateView执行后执行
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (String title : mTitles) {
            mFragments.add(VideoFragment.newInstance(title));
        }
        viewPager.setOffscreenPageLimit(mFragments.size());
        mAdapter = new HomeAdapter(requireActivity().getSupportFragmentManager(), mTitles, mFragments);
        viewPager.setAdapter(mAdapter);

        Log.d("jjh", viewPager.getAdapter().getCount()+"vp count");
        Log.d("jjh", mAdapter.getCount()+"ad count");
        Log.d("jjh", mTitles.length+"mt length");
        slidingTabLayout.setViewPager(viewPager,mTitles);
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
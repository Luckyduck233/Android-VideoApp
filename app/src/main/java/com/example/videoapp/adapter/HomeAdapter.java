package com.example.videoapp.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class HomeAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    public HomeAdapter(@NonNull FragmentManager fm, String[] titles, ArrayList<Fragment> fragments) {
        super(fm);
        this.mTitles = titles;
        this.mFragmentList = fragments;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
//        try {
//            Thread.currentThread().sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (mTitles != null && mFragmentList != null) {
            return mTitles.length;
        } else {
            return 0;
        }
    }

}

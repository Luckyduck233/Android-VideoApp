package com.example.videoapp.util;

import android.content.Context;

import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Collections;

public class MySlidingTabLayout extends SlidingTabLayout {
    public MySlidingTabLayout(Context context) {
        super(context);
    }

    @Override
    public void setViewPager(ViewPager vp) {
        super.setViewPager(vp);
    }

    @Override
    public void setViewPager(ViewPager vp, String[] titles) {
//        this.mViewPager = vp;
//        mTitles = new ArrayList<>();
//        Collections.addAll(mTitles, titles);
//
//        this.mViewPager.removeOnPageChangeListener(this);
//        this.mViewPager.addOnPageChangeListener(this);
//        notifyDataSetChanged();
    }
}

package com.example.videoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.videoapp.R;
import com.example.videoapp.adapter.MyPagerAdapter;
import com.example.videoapp.entity.TabEntity;
import com.example.videoapp.fragment.CollectFragment;
import com.example.videoapp.fragment.HomeFragment;
import com.example.videoapp.fragment.MyFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;

    private String[] mTitles = {"首页", "收藏", "我的"};
    private int[] mIconUnselectIds = {R.drawable.tab_home_unselect, R.drawable.collect_unselect, R.drawable.my_unselect};
    private int[] mIconSelectIds = {R.drawable.tab_home_select, R.drawable.collect_select, R.drawable.my_select};

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(CollectFragment.newInstance());
        mFragments.add(MyFragment.newInstance());

//        Fragment预加载 有多少个Fragment就加载多少个
        viewPager.setOffscreenPageLimit(mFragments.size());
    }

    @Override
    protected void initData() {
        //        把标题、tab图标的值传入实体类
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        commonTabLayout.setTabData(mTabEntities);

        //        设置viewpager适配器
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitles, mFragments));

    }

    @Override
    protected void initEvent() {
        //        设置Tab点击事件
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                    viewPager.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
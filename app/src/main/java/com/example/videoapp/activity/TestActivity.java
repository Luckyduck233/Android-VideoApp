package com.example.videoapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.videoapp.R;

public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";
    private ImageView ivLoading,ivLoading2;
    private Button btnStart;
    private Animation mAnimation;
    private boolean switch1 = false;
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        ivLoading = findViewById(R.id.iv_loading);
        ivLoading2 = findViewById(R.id.iv_loading2);
        btnStart = findViewById(R.id.btn_startRotate);
    }

    @Override
    protected void initData() {
        mAnimation = AnimationUtils.loadAnimation(TestActivity.this, R.anim.rotate_loading);
        mAnimation.setDuration(1500);
        mAnimation.setRepeatCount(Animation.INFINITE);

        Path path = new Path();
        path.moveTo(0f, 0f);
        path.lineTo(1f, 1f);
        PathInterpolator pathInterpolator = new PathInterpolator(path);

        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(ivLoading2, View.ROTATION, 0f, 360f);
        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(ivLoading2, View.TRANSLATION_Y, 0f, 500f);

        rotateAnimator.setDuration(800);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        translationYAnimator.setDuration(2000);
        translationYAnimator.setInterpolator(pathInterpolator);

        animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotateAnimator, translationYAnimator);

    }

    @Override
    protected void initEvent() {
        ivLoading.setOnClickListener(v -> {
//            if (mAnimation != null) {
//                Log.d(TAG, "startAnimation: ");
//                ivLoading.startAnimation(mAnimation);
//            }
        });
        btnStart.setOnClickListener(v->{
            Log.d(TAG, "click: ");
            if (!switch1 && mAnimation!=null) {
                Log.d(TAG, "startAnimation: ");
                ivLoading.startAnimation(mAnimation);
                animatorSet.start();
                switch1 = true;
            } else {
                Log.d(TAG, "clearAnimation: ");
                ivLoading.clearAnimation();
                switch1 = false;
            }
        });
    }
}
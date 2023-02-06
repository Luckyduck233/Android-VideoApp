package com.example.videoapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;

import com.example.videoapp.MyApplication;
import com.example.videoapp.databinding.FragmentNewsBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import xyz.doikki.videoplayer.player.VideoViewManager;

public abstract class BaseFG<T extends ViewBinding> extends Fragment {
    protected View mRootView;
    private Unbinder unbinder;

    protected T viewBinding;

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
////        if (viewBinding == null) {
////            viewBinding = FragmentNewsBinding
////        }
////        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
////        Class cls = (Class) type.getActualTypeArguments()[0];
////
////            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
////            viewBinding = (T) inflate.invoke(null, inflater, container, false);
////
////        return viewBinding.getRoot();
////        Type type = this.getClass().getGenericSuperclass();
////        if (type instanceof ParameterizedType) {
////
////                Class<T> clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
////                Method method = clazz.getMethod("inflate", LayoutInflater.class, ViewGroup.class, Boolean.class);
////                viewBinding = (T) method.invoke(null, container, false);
////
////        }
////        return viewBinding.getRoot();
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int initLayout();

    protected abstract void initListener();

    public void showToast(String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        viewBinding = null;
    }

    /**
     * 子类可通过此方法直接拿到VideoViewManager
     */
    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }
}


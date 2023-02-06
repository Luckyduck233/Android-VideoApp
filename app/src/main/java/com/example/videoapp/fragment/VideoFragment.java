package com.example.videoapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.videoapp.MyApplication;
import com.example.videoapp.R;
import com.example.videoapp.activity.LoginActivity;
import com.example.videoapp.adapter.VideoAdapter;
import com.example.videoapp.api.Api;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.api.TtitCallback;
import com.example.videoapp.entity2.ListDTO;
import com.example.videoapp.entity2.MyDTO;
import com.example.videoapp.entity2.RandomHeadResoponse;
import com.example.videoapp.entity2.VideoListResponse;
import com.example.videoapp.listener.OnItemChildClickListener;
import com.example.videoapp.sp.MySharedPreferences;
import com.example.videoapp.util.Tag;
import com.example.videoapp.util.Utils;
import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import xyz.doikki.videocontroller.StandardVideoController;
import xyz.doikki.videocontroller.component.CompleteView;
import xyz.doikki.videocontroller.component.ErrorView;
import xyz.doikki.videocontroller.component.GestureView;
import xyz.doikki.videocontroller.component.TitleView;
import xyz.doikki.videocontroller.component.VodControlView;
import xyz.doikki.videoplayer.player.VideoView;


public class VideoFragment extends BaseFragment implements OnItemChildClickListener {
    private static final String TAG = "VideoFragment";
    private int categoryId;
    private RecyclerView mRecyclerView;
    private RefreshLayout refreshLayout;
    private int pageNum = 1;
    private VideoAdapter videoAdapter;
    private List<ListDTO> mDatas = new ArrayList<>();
    private int count = 0;
    public List<RandomHeadResoponse> list = new ArrayList<>();
    ArrayList<Object> imgUrlList = new ArrayList<>();
    private final Context mContext = MyApplication.getContext();
    private boolean isFullScreen = false;

//    private Handler mHandler1 = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(@NonNull Message msg) {
//            Looper.prepare();
//            switch (msg.what) {
//                case 0:
//                    mRecyclerView.setAdapter(videoAdapter);
//                    videoAdapter.notifyDataSetChanged();
//                    break;
//            }
//            Looper.loop();
//            return false;
//        }
//    });

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
//            Looper.prepare();
            switch (msg.what) {
                case 0:
                    mRecyclerView.setAdapter(videoAdapter);
                    videoAdapter.notifyDataSetChanged();
                    Log.e(TAG, "eeeeeee");
                    break;
            }
        }
    };




    protected VideoView mVideoView;
    protected StandardVideoController mController;
    protected ErrorView mErrorView;
    protected CompleteView mCompleteView;
    protected TitleView mTitleView;

    /**
     * 当前播放的位置
     */
    protected int mCurPos = -1;
    /**
     * 上次播放的位置，用于页面切回来之后恢复播放
     */
    protected int mLastPos = mCurPos;
    private LinearLayoutManager mLinearLayoutManager;

    public VideoFragment() {

    }

    public static VideoFragment newInstance(int categoryName) {
        VideoFragment fragment = new VideoFragment();
        fragment.categoryId = categoryName;
        Log.e(TAG, categoryName+"");
        return fragment;
    }
    @Override
    protected void initView() {
        initVideoView();

        mRecyclerView = mRootView.findViewById(R.id.recyclerView_video);
        refreshLayout = mRootView.findViewById(R.id.smartRefreshLayout);

    }

    @Override
    protected void initData() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        videoAdapter = new VideoAdapter(getContext());
        videoAdapter.setOnItemChildClickListener(this::onItemChildClick);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//        设置recyclerview间距
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
//        传入一个drawable对象来自定义我们想要的效果
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider_video));
        mRecyclerView.addItemDecoration(decoration);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getVideoListTest(true);
            }
        }).setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getVideoListTest(false);
            }
        });
        getVideoListTest(true);
    }

    @Override
    protected void initListener() {
        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                CardView playerContainer = view.findViewById(R.id.player_container);
                View v = playerContainer.getChildAt(0);
                if (v != null && v == mVideoView && !mVideoView.isFullScreen()) {
                    releaseVideoView();
                    Log.e(TAG, "onChildViewDetachedFromWindow: ");
                }
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_video;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (mVideoView.isFullScreen()) {
                        mVideoView.stopFullScreen();
                        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                        Log.e(TAG, "keyCode is : "+keyCode );
                        return true;
                    } else {

                        Log.e(TAG, "keyCode is : "+keyCode );
                        requireActivity().finish();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    protected void initVideoView() {
        mVideoView = new VideoView(getActivity());
        mVideoView.setOnStateChangeListener(new VideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                //监听VideoViewManager释放，重置状态
                if (playState == VideoView.STATE_IDLE) {
                    Utils.removeViewFormParent(mVideoView);
                    mLastPos = mCurPos;
                    mCurPos = -1;
                }
            }
        });
        mController = new StandardVideoController(getActivity());
        mErrorView = new ErrorView(getActivity());
        mController.addControlComponent(mErrorView);
        mCompleteView = new CompleteView(getActivity());
        mController.addControlComponent(mCompleteView);
        mTitleView = new TitleView(getActivity());
        mController.addControlComponent(mTitleView);
        mController.addControlComponent(new VodControlView(getActivity()));
        mController.addControlComponent(new GestureView(getActivity()));
        mController.setEnableOrientation(true);
        mVideoView.setVideoController(mController);
    }


    private void getVideoListTest(boolean isRefresh) {

        Object spResult = MySharedPreferences.config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, getContext().MODE_PRIVATE).getParam("token", "");
        String token = spResult.toString();
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", pageNum);
        params.put("limit", ApiConfig.PAGE_SIZE);
        Log.e("jjh000", pageNum + "");
        Api.config(ApiConfig.VIDEO_LIST, params)
                .getRequest(getContext(),new TtitCallback() {
                    @Override
                    public void onSuccess(String result) {
                        if (isRefresh) {
                            refreshLayout.finishRefresh(true);
                        } else {
                            refreshLayout.finishLoadMore(true);
                        }
                        ArrayList<MyDTO> myDTOS = new ArrayList<>();
                        VideoListResponse response = new Gson().fromJson(result, VideoListResponse.class);

                        if (response != null && response.getCode() == 0) {
                            List<ListDTO> list = response.getPage().getList();
                            if (list != null && list.size() > 0) {
                                if (isRefresh) {
                                    mDatas = list;
                                } else {
                                    mDatas.addAll(list);
                                }
                                Log.d(TAG, mDatas.size() + ":");
                                videoAdapter.setmDatas(mDatas);

                                //需要在主线程渲染ui
                                mHandler.sendEmptyMessage(0);
//                                getImgUrl(mDatas.size());
                            } else {
                                if (isRefresh) {
                                    showToast("暂时无数据");
                                } else {
                                    showToast("没有更多数据了");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        if (isRefresh) {
                            Toast.makeText(getContext(), "暂时无数据", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//        videoAdapter.setmDatas();
    }



    private void getImgUrl(int counts) {
        Log.d("jiangjia", counts+"");

        HashMap<String, Object> params2 = new HashMap<>();
        params2.put("method", "zsy");
        params2.put("lx", "a1");
        params2.put("format", "json");
        new Thread("getImgUrl") {
            @Override
            public void run() {
                super.run();
                while (true) {
//                        sleep(500);
                        if (count < counts) {
                            count++;
                            Api.config2(ApiConfig.RANDOM_HEAD, params2).getRequest(getContext(),new TtitCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    RandomHeadResoponse resoponse = new Gson().fromJson(result, RandomHeadResoponse.class);
                                    if (resoponse != null && resoponse.getCode().equals("200")) {
                                        imgUrlList.add(resoponse.getImgurl());
//                                        Log.e(TAG, imgUrlList.toString() );
                                        Log.d("jiahao", count+"");
                                        if (count == counts) {
                                            Log.d(TAG, "onSuccess: ");
                                            videoAdapter.setHeadimgUrl2(imgUrlList);
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Exception e) {

                                }
                            });
//                        Log.d("getImgUrl", count + "");
                        } else {
                            Log.d("getImgUrl", "stop :");
                            int size = imgUrlList.size();
                            Log.d(TAG, "stop : "+size);
                            break;
                        }


                }
            }
        }.start();
//        int size = imgUrlList.size();
//        Log.d(TAG, "列表的 size "+size);
    }

    private void addList(RandomHeadResoponse resoponse) {
        list.add(resoponse);
        Log.d("addList", list.size() + "");
    }

    private void setList(List<Object> param) {

    }

    private void getVideoList(boolean isRefresh) {
        Log.d("1204jjh", "setmDatas11");
        Object spResult = MySharedPreferences.config(MyApplication.getContext(), ApiConfig.SP_TOKEN_NAME, MyApplication.getContext().MODE_PRIVATE).getParam("token", "");
        String token = spResult.toString();
        Log.d(TAG, token);
            Log.d("1204jjh", "setmDatas12");
            HashMap<String, Object> params = new HashMap<>();
            params.put("page", pageNum);
            params.put("limit", ApiConfig.PAGE_SIZE);
            Api.config(ApiConfig.VIDEO_LIST, params).getRequest(getContext(),new TtitCallback() {
                @Override
                public void onSuccess(String result) {
                    Log.d("1204jjh", "setmDatas2");
                    if (isRefresh) {
                        refreshLayout.finishRefresh(true);
                    } else {
                        refreshLayout.finishLoadMore(true);
                    }
                    VideoListResponse response = new Gson().fromJson(result, VideoListResponse.class);
                    if (response != null && response.getCode() == 0) {
                        List<ListDTO> list = response.getPage().getList();
                        if (list != null && list.size() > 0) {
                            if (isRefresh) {
                                mDatas = list;
                            } else {
                                mDatas.addAll(list);
                            }
                            videoAdapter.setmDatas(mDatas);
                            Log.d("1204jjh", "setmDatas3");
                            videoAdapter.notifyDataSetChanged();
                        }
//                        如果返回为空
                        else {
                            if (isRefresh) {
                                Toast.makeText(getContext(), "暂时加载无数据", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                            }
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mRecyclerView.setAdapter(videoAdapter);
                            }
                        });

//                        我的头像url实体类列表
//                         List<MyDTO> myDatas = new ArrayList<>();

//                        for (int i = 0; i < response.getPage().getTotalCount(); i++) {
//                            MyDTO myDTO = new MyDTO();
//                            myDTO.setHeadUrl("https://api.btstu.cn/sjtx/api.php");
//                            myDatas.add(myDTO);
//                        }
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    if (isRefresh) {
                        refreshLayout.finishRefresh(true);
                    } else {
                        refreshLayout.finishLoadMore(true);
                    }
                }
            });
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
//        Log.e("gjlnb", "0");
    }

    /**
     * 由于onPause必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onPause的逻辑
     */
    protected void pause() {
        releaseVideoView();
//        Log.e("gjlnb", "1");
    }

    @Override
    public void onResume() {
        super.onResume();
        resume();
//        Log.e("gjlnb", "2");
    }

    /**
     * 由于onResume必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onResume的逻辑
     */
    protected void resume() {
        if (mLastPos == -1)
            return;
//        if (MainActivity.mCurrentIndex != 1)
//            return;
        //恢复上次播放的位置
        startPlay(mLastPos);
//        Log.e("gjlnb", "3");
    }

    /**
     * PrepareView被点击
     */
    @Override
    public void onItemChildClick(int position) {
        Log.e(TAG, "onItemChildClick: 666" );
        startPlay(position);
    }

    /**
     * 开始播放
     * @param position 列表位置
     */
    protected void startPlay(int position) {
        if (mCurPos == position) return;
        if (mCurPos != -1) {
            releaseVideoView();
        }
        ListDTO videoBean = mDatas.get(position);
        //边播边存
//        String proxyUrl = ProxyVideoCacheManager.getProxy(getActivity()).getProxyUrl(videoBean.getUrl());
//        mVideoView.setUrl(proxyUrl);
        Log.e("byebye", videoBean.getPlayurl());
        mVideoView.setUrl(videoBean.getPlayurl());
        mTitleView.setTitle(videoBean.getVtitle());
        View itemView = mLinearLayoutManager.findViewByPosition(position);
        if (itemView == null) return;
        VideoAdapter.ViewHolder viewHolder = (VideoAdapter.ViewHolder) itemView.getTag();
        //把列表中预置的PrepareView添加到控制器中，注意isDissociate此处只能为true, 请点进去看isDissociate的解释
        mController.addControlComponent(viewHolder.mPrepareView, true);
        Utils.removeViewFormParent(mVideoView);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        //播放之前将VideoView添加到VideoViewManager以便在别的页面也能操作它
        getVideoViewManager().add(mVideoView, Tag.LIST);
        mVideoView.start();
        mCurPos = position;
    }

    private void releaseVideoView() {
        mVideoView.release();
        if (mVideoView.isFullScreen()) {
            mVideoView.stopFullScreen();
        }
        if(getActivity().getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mCurPos = -1;
    }


//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            showToast("真的要退出应用吗");
//            return true;
//        } else {
//            return false;
//        }
//    }

    /*
     * 设置RecyclerView间距 RecyclerView装饰类
     * */
    public class LinearSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spacing;

        public LinearSpacingItemDecoration(Context context) {

        }


        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
//        outRect.top = spacing;//item上边的间距
//        outRect.left = spacing;//左边间距
//        outRect.right = spacing;//右边间距
//            设置底部间距
            outRect.bottom = spacing;
        }
    }

}
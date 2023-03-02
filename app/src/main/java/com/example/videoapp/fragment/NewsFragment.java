package com.example.videoapp.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.videoapp.R;
import com.example.videoapp.adapter.NewsAdapter;
import com.example.videoapp.api.Api;
import com.example.videoapp.api.ApiConfig;
import com.example.videoapp.api.TtitCallback;
import com.example.videoapp.databinding.FragmentNewsBinding;
import com.example.videoapp.easyMockEntity.Login.news.Data;
import com.example.videoapp.easyMockEntity.Login.news.NewsListMockResponse;
import com.example.videoapp.entity.News.NewsEntity;
import com.example.videoapp.entity.News.NewsListResponse;
import com.example.videoapp.sp.MySharedPreferences;
import com.google.gson.Gson;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends BaseFragment {

    private SearchView mSearchView;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private List<NewsEntity> datas = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private int pageNum = 1;
    public static String TAG = "20220110";

//    private Handler mHandler = new Handler(Looper.getMainLooper()) {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 0:
//                    newsAdapter.setDatas(datas);
//                    newsAdapter.notifyDataSetChanged();
//                    break;
//            }
//        }
//    };

    private final Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.e(TAG, "handleMessage: "+1 );
                    NewsListMockResponse response = (NewsListMockResponse) msg.obj;
                    List<Data> dataList = response.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            newsAdapter = new NewsAdapter(getContext());
                            recyclerView.setAdapter(newsAdapter);
                            newsAdapter.setDatas(dataList);
                            newsAdapter.notifyDataSetChanged();
                        }
                    });
                    break;
            }
        }
    };


    public NewsFragment() {

    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected void initView() {
        smartRefreshLayout = mRootView.findViewById(R.id.smartRefreshLayout_news);
        recyclerView = mRootView.findViewById(R.id.recyclerView_news);
        mSearchView = mRootView.findViewById(R.id.news_searchView);
    }

    @Override
    protected void initData() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
//        设置recyclerview间距
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        //        传入一个drawable对象来自定义我们想要的效果
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider_news));
        recyclerView.addItemDecoration(decoration);
        getNewsList(true);
    }

//    private void getNewsList(boolean isRefresh) {
//        HashMap<String, Object> map = new HashMap<>();
//
//        Api.config(ApiConfig.NEWS_LIST, map)
//                .getRequestEm(getContext(),new TtitCallback() {
//                    @Override
//                    public void onSuccess(Call call, Response resp, String result) {
//                        if (isRefresh) {
//                            smartRefreshLayout.finishRefresh(true);
//                        } else {
//                            smartRefreshLayout.finishLoadMore(true);
//                        }
//                        NewsListResponse response = new Gson().fromJson(result, NewsListResponse.class);
//                        if (response.getCode() != null && response.getCode() == 0) {
//                            List<NewsEntity> list = response.getPage().getList();
//                            if (list != null && list.size() > 0) {
//                                if (isRefresh) {
//                                    Log.e(TAG, "refresh");
//                                    datas = list;
//                                } else {
//                                    Log.e(TAG, "loading");
//                                    datas.addAll(list);
//                                }
//                                mHandler.sendEmptyMessage(0);
//                            } else {
//                                if (isRefresh) {
//                                    showToast("暂时无数据");
//                                } else {
//                                    showToast(" 没有更多数据了");
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call call,Exception e) {
//                        if (isRefresh) {
//                            smartRefreshLayout.finishRefresh(true);
//                        } else {
//                            smartRefreshLayout.finishLoadMore(true);
//                        }
//                    }
//                });
//    }
    private void getNewsList(boolean isRefresh){
        HashMap<String, Object> map = new HashMap<>();
        Api.config(ApiConfig.EM_NEWS_LIST,map).getRequestEm(mContext, new TtitCallback() {
            @Override
            public void onSuccess(Call call, Response resp, String result) {
                NewsListMockResponse response = new Gson().fromJson(result, NewsListMockResponse.class);
                if (response.getCode()!=null && response.getCode() == 200) {
                    Log.e(TAG, "onSuccess: response news");
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = response;
                    mHandler.handleMessage(message);
                }
            }

            @Override
            public void onFailure(Call call,Exception e) {

            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.e(TAG, "refresh");
                pageNum = 1;
                smartRefreshLayout.finishRefresh(true);
                getNewsList(true);
            }
        });smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e(TAG, "onLoadMore");
                smartRefreshLayout.finishLoadMore(true);
                pageNum++;
                getNewsList(false);
            }
        });
//        getNewsList(true);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, newText );
                newsAdapter.getFilter().filter(newText);
//                newsAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

}
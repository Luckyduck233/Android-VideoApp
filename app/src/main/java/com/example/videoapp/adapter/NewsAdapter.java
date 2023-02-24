//package com.example.videoapp.adapter;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.videoapp.R;
//import com.example.videoapp.entity.News.NewsEntity;
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
//    private Context mContext;
//    private List<NewsEntity> mDatas;
//
//    public NewsAdapter(Context mContext) {
//        Log.e("000999", "NewsAdapter: ");
//        this.mContext = mContext;
//    }
//
//    public void setDatas(List<NewsEntity> datas) {
//        this.mDatas = datas;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
////        这里返回的是datas传入的type值
//        int type = mDatas.get(position).getType();
//        return type;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        Log.e("999888", viewType + "");
//        if (viewType == 1) {
//            Log.e("10109", "start   "+viewType );
//            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_one, parent, false);
//            return new ViewHolder(view);
//        } else if (viewType == 2) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_three, parent, false);
//            return new ViewHolder2(view);
//        } else {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_big, parent, false);
//            return new ViewHolder3(view);
//        }
//
////        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_one, parent, false);
////        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Integer type = mDatas.get(position).getType();
//        NewsEntity newsEntity = mDatas.get(position);
//        Log.e("10109", type+"" );
//        if (type == 1) {
//            ViewHolder vh = (ViewHolder) holder;
//            vh.mTitle.setText(newsEntity.getNewsTitle());
//            vh.mTitle.setText(newsEntity.getNewsTitle());
//            vh.mAuthor.setText(newsEntity.getAuthorName());
//            vh.mComment.setText(String.valueOf(newsEntity.getCommentCount()));
//            vh.mTime.setText(newsEntity.getReleaseDate());
//
//            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(0).getThumbUrl()).into(vh.mCover);
//        } else if (type == 2) {
//            ViewHolder2 vh = (ViewHolder2) holder;
//
//            vh.mTitle.setText(newsEntity.getNewsTitle());
//            vh.mTitle.setText(newsEntity.getNewsTitle());
//            vh.mAuthor.setText(newsEntity.getAuthorName());
//            vh.mComment.setText(String.valueOf(newsEntity.getCommentCount()));
//            vh.mTime.setText(newsEntity.getReleaseDate());
//
//            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(0).getThumbUrl()).into(vh.mCover);
//            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(1).getThumbUrl()).into(vh.mCover1);
//            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(2).getThumbUrl()).into(vh.mCover2);
//        } else {
//            ViewHolder3 vh = (ViewHolder3) holder;
//
//            vh.mTitle.setText(newsEntity.getNewsTitle());
//            vh.mTitle.setText(newsEntity.getNewsTitle());
//            vh.mAuthor.setText(newsEntity.getAuthorName());
//            vh.mComment.setText(String.valueOf(newsEntity.getCommentCount()));
//            vh.mTime.setText(newsEntity.getReleaseDate());
//
//            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(0).getThumbUrl()).into(vh.mCover);
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mDatas != null && mDatas.size() >= 0) {
//            return mDatas.size();
////            return 14;
//        } else {
//            return 0;
//        }
//    }
//
//    @Override
//    public Filter getFilter() {
//        return null;
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView mTitle, mAuthor, mTime, mComment;
//        private ImageView mCover;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            initView(itemView);
//        }
//
//        private void initView(View view) {
//            mTitle = view.findViewById(R.id.news_title);
//            mAuthor = view.findViewById(R.id.news_author);
//            mTime = view.findViewById(R.id.news_time);
//            mComment = view.findViewById(R.id.news_comment);
//            mCover = view.findViewById(R.id.news_cover);
//        }
//    }
//
//
//    public class ViewHolder2 extends RecyclerView.ViewHolder {
//        private TextView mTitle, mAuthor, mTime, mComment;
//        private ImageView mCover,mCover1,mCover2;
//
//        public ViewHolder2(@NonNull View itemView) {
//            super(itemView);
//            initView(itemView);
//        }
//
//        private void initView(View view) {
//            mTitle = view.findViewById(R.id.news_title);
//            mAuthor = view.findViewById(R.id.news_author);
//            mTime = view.findViewById(R.id.news_time);
//            mComment = view.findViewById(R.id.news_comment);
//            mCover = view.findViewById(R.id.news_cover);
//            mCover1 = view.findViewById(R.id.news_cover1);
//            mCover2 = view.findViewById(R.id.news_cover2);
//        }
//    }
//
//
//    public class ViewHolder3 extends RecyclerView.ViewHolder {
//        private TextView mTitle, mAuthor, mTime, mComment;
//        private ImageView mCover;
//
//        public ViewHolder3(@NonNull View itemView) {
//            super(itemView);
//            initView(itemView);
//        }
//
//        private void initView(View view) {
//            mTitle = view.findViewById(R.id.news_title);
//            mAuthor = view.findViewById(R.id.news_author);
//            mTime = view.findViewById(R.id.news_time);
//            mComment = view.findViewById(R.id.news_comment);
//            mCover = view.findViewById(R.id.news_cover);
//        }
//    }
//
//}
package com.example.videoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoapp.R;
import com.example.videoapp.activity.NewsActivity;
import com.example.videoapp.easyMockEntity.Login.news.Data;
import com.example.videoapp.entity.News.NewsEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private Context mContext;
    private List<Data> mOriginalList;
    private List<Data> mNewsDatas;
    private NewsFilter mFilter;
    public static String TAG = "NewsAdapter";

    public NewsAdapter(Context mContext) {
        Log.e("000999", "NewsAdapter: ");
        this.mContext = mContext;
    }

    public void setDatas(@NonNull List<Data> datas) {
        String ntitle = datas.get(0).getNtitle();
        Log.e(TAG, "setDatas: "+ntitle );
        this.mNewsDatas = datas;
        Log.e(TAG, "mNewsDatas: "+mNewsDatas.get(0).getNtitle() );
        this.mOriginalList = datas;
    }

    @Override
    public int getItemViewType(int position) {
//        这里返回的是datas传入的type值
        int type = mNewsDatas.get(position).getType();
        Log.e(TAG, type+"09323521" );
        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.e("999888", viewType + "");
        if (viewType == 1) {
            Log.e("10109", "start   " + viewType);
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_one, parent, false);
            return new ViewHolder(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_big, parent, false);
            return new ViewHolder2(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_three, parent, false);
            return new ViewHolder3(view);
        }

//        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_one, parent, false);
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Integer type = mNewsDatas.get(position).getType();
        Data newsData = mNewsDatas.get(position);
        Log.e("10109", type + "");
        if (type == 1) {
            Log.d(TAG, "09323521: ");
            ViewHolder vh = (ViewHolder) holder;
            vh.mTitle.setText(newsData.getNtitle());
            vh.mAuthor.setText(newsData.getAuthor());
            vh.mComment.setText(String.valueOf(newsData.getCommentNum()));
            vh.mTime.setText(newsData.getCreateTime());

            Picasso.with(mContext).load(newsData.getCoverUrlList().get(0).getCoverUrl().get(0)).into(vh.mCover);
        } else if (type == 2) {
            ViewHolder2 vh = (ViewHolder2) holder;
            vh.mTitle.setText(newsData.getNtitle());
            vh.mAuthor.setText(newsData.getAuthor());
            vh.mComment.setText(String.valueOf(newsData.getCommentNum()));
            vh.mTime.setText(newsData.getCreateTime());

            Picasso.with(mContext).load(newsData.getCoverUrlList().get(0).getCoverUrl().get(0)).into(vh.mCover);
        } else {
            ViewHolder3 vh = (ViewHolder3) holder;
            vh.mTitle.setText(newsData.getNtitle());
            vh.mAuthor.setText(newsData.getAuthor());
            vh.mComment.setText(String.valueOf(newsData.getCommentNum()));
            vh.mTime.setText(newsData.getCreateTime());

            Picasso.with(mContext).load(newsData.getCoverUrlList().get(0).getCoverUrl().get(0)).into(vh.mCover);
            Picasso.with(mContext).load(newsData.getCoverUrlList().get(0).getCoverUrl().get(1)).into(vh.mCover1);
            Picasso.with(mContext).load(newsData.getCoverUrlList().get(0).getCoverUrl().get(2)).into(vh.mCover2);
        }

    }

    @Override
    public int getItemCount() {
        if (mNewsDatas != null) {
            return mNewsDatas.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mAuthor, mTime, mComment;
        private ImageView mCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, NewsActivity.class);
//                    mContext.startActivity(intent);
//                }
//            });
        }

        private void initView(View view) {
            mTitle = view.findViewById(R.id.news_title);
            mAuthor = view.findViewById(R.id.news_author);
            mTime = view.findViewById(R.id.news_time);
            mComment = view.findViewById(R.id.news_comment);
            mCover = view.findViewById(R.id.news_cover);
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView mTitle, mAuthor, mTime, mComment;
        private ImageView mCover;


        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            mTitle = view.findViewById(R.id.news_title);
            mAuthor = view.findViewById(R.id.news_author);
            mTime = view.findViewById(R.id.news_time);
            mComment = view.findViewById(R.id.news_comment);
            mCover = view.findViewById(R.id.news_cover);
        }
    }

    public static class ViewHolder3 extends RecyclerView.ViewHolder {
        private TextView mTitle, mAuthor, mTime, mComment;

        private ImageView mCover, mCover1, mCover2;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }
        private void initView(View view) {
            mTitle = view.findViewById(R.id.news_title);
            mAuthor = view.findViewById(R.id.news_author);
            mTime = view.findViewById(R.id.news_time);
            mComment = view.findViewById(R.id.news_comment);
            mCover = view.findViewById(R.id.news_cover);
            mCover1 = view.findViewById(R.id.news_cover1);
            mCover2 = view.findViewById(R.id.news_cover2);
        }
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new NewsFilter();
        }
        return mFilter;
    }

    class NewsFilter extends Filter {
        //        在perFormFiltering 这个方法中定义过滤规则
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.e(TAG, "performFiltering: ");
            FilterResults results = new FilterResults();
            List<Data> resultList;
            //将符合条件的数据添加在集合中
            if (!TextUtils.isEmpty(constraint)) {
                resultList = new ArrayList<>();
                List<NewsEntity> authorList = new ArrayList<>();
                for (Data news : mOriginalList) {
                    if (news.getNtitle().contains(constraint)) {
                        resultList.add(news);
                    }
                }


//                for (NewsEntity news : mOriginalList) {
//                    if (news.getAuthorName().contains(constraint)) {
//                        for (NewsEntity authorName : resultList) {
//                            if (news.getNewsId()==authorName.getNewsId())
//                        }
//                    }
//                }
//                for (NewsEntity a : mOriginalList) {
//                    if (a.getAuthorName().contains(constraint)) {
//                        Log.e(TAG, "resultList.size"+resultList.size()+"");
//                        if (resultList.isEmpty()) {
//                            resultList.add(a);
//                        } else {
//                            for (NewsEntity b : resultList) {
//                                Log.e(TAG, a.getAuthorName() );
//                                Log.e(TAG, resultList.size()+b.getNewsTitle()+"   "+b.getAuthorName());
//
//                                if (a.getNewsId() == b.getNewsId()) {
//
//                                }
//                            }
//                        }
////                        for (NewsEntity b : resultList) {
////                            if (!Objects.equals(a.getNewsId(), b.getNewsId())) {
////                                resultList.add(a);
////                            }
////                            break;
////                        }
//                    }
//
//                }
            } else {
                //否则当搜索词为空时我们显示所有数据（查询前的所有数据）
                resultList = mOriginalList;
            }


//            将得到的集合数保存在 FilterResult的count变量中
            results.count = resultList.size();
//            将得到的集合保存在 FilterResult的values变量中
            results.values = resultList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.e(TAG, "publishResults: ");
            if (results.count > 0) {
                mNewsDatas = (List<Data>) results.values;
                notifyDataSetChanged();
            } else {
                mNewsDatas = null;
                notifyDataSetChanged();
            }
        }
    }

}


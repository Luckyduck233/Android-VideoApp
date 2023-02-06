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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoapp.R;
import com.example.videoapp.entity.News.NewsEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private Context mContext;
    private List<NewsEntity> mOriginalList;
    private List<NewsEntity> mNewsDatas;
    private NewsFilter mFilter;
    public static String TAG = "NewsAdapter";

    public NewsAdapter(Context mContext) {
        Log.e("000999", "NewsAdapter: ");
        this.mContext = mContext;
    }

    public void setDatas(List<NewsEntity> datas) {
        this.mNewsDatas = datas;
        this.mOriginalList = datas;
    }

    @Override
    public int getItemViewType(int position) {
//        这里返回的是datas传入的type值
        int type = mNewsDatas.get(position).getType();
        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.e("999888", viewType + "");
        if (viewType == 1) {
            Log.e("10109", "start   "+viewType );
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_one, parent, false);
            return new ViewHolder(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_three, parent, false);
            return new ViewHolder2(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_big, parent, false);
            return new ViewHolder3(view);
        }

//        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_one, parent, false);
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Integer type = mNewsDatas.get(position).getType();
        NewsEntity newsEntity = mNewsDatas.get(position);
        Log.e("10109", type+"" );
        if (type == 1) {
            ViewHolder vh = (ViewHolder) holder;
            vh.mTitle.setText(newsEntity.getNewsTitle());
            vh.mTitle.setText(newsEntity.getNewsTitle());
            vh.mAuthor.setText(newsEntity.getAuthorName());
            vh.mComment.setText(String.valueOf(newsEntity.getCommentCount()));
            vh.mTime.setText(newsEntity.getReleaseDate());

            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(0).getThumbUrl()).into(vh.mCover);
        } else if (type == 2) {
            ViewHolder2 vh = (ViewHolder2) holder;

            vh.mTitle.setText(newsEntity.getNewsTitle());
            vh.mTitle.setText(newsEntity.getNewsTitle());
            vh.mAuthor.setText(newsEntity.getAuthorName());
            vh.mComment.setText(String.valueOf(newsEntity.getCommentCount()));
            vh.mTime.setText(newsEntity.getReleaseDate());

            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(0).getThumbUrl()).into(vh.mCover);
            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(1).getThumbUrl()).into(vh.mCover1);
            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(2).getThumbUrl()).into(vh.mCover2);
        } else {
            ViewHolder3 vh = (ViewHolder3) holder;

            vh.mTitle.setText(newsEntity.getNewsTitle());
            vh.mTitle.setText(newsEntity.getNewsTitle());
            vh.mAuthor.setText(newsEntity.getAuthorName());
            vh.mComment.setText(String.valueOf(newsEntity.getCommentCount()));
            vh.mTime.setText(newsEntity.getReleaseDate());

            Picasso.with(mContext).load(newsEntity.getThumbEntities().get(0).getThumbUrl()).into(vh.mCover);
        }

    }

    @Override
    public int getItemCount() {
        if (mNewsDatas != null && mNewsDatas.size() >= 0) {
            return mNewsDatas.size();
//            return 14;
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mAuthor, mTime, mComment;
        private ImageView mCover;

        public ViewHolder(@NonNull View itemView) {
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


    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView mTitle, mAuthor, mTime, mComment;
        private ImageView mCover,mCover1,mCover2;

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
            mCover1 = view.findViewById(R.id.news_cover1);
            mCover2 = view.findViewById(R.id.news_cover2);
        }
    }


    public class ViewHolder3 extends RecyclerView.ViewHolder {
        private TextView mTitle, mAuthor, mTime, mComment;
        private ImageView mCover;

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
            List<NewsEntity> resultList;
            if (TextUtils.isEmpty(constraint)) {
//                当搜索词为空时我们显示所有数据（查询前的所有数据）
                resultList = mOriginalList;
            } else {//否则将符合条件的数据添加在集合中
                resultList = new ArrayList<>();
                for (NewsEntity news : mOriginalList) {
                    if (news.getNewsTitle().contains(constraint)) {
                        resultList.add(news);
                    }
                }
            }
//            将得到的集合数保存在 FilterResult的count变量中
            results.count = resultList.size();
//            将得到的集合保存在 FilterResult的values变量中
            results.values = resultList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Log.e(TAG, "publishResults: " );
            mNewsDatas = (List<NewsEntity>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            }
        }
    }

}


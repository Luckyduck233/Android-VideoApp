package com.example.videoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoapp.R;
import com.example.videoapp.entity.VideoEntity;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<VideoEntity> mDatas;

    public VideoAdapter(Context context, List<VideoEntity> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VideoEntity videoEntity = mDatas.get(position);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {

            return mDatas.size();
        } else {
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvCollect, tvLike,tvAuthor,tvComments;



        public ViewHolder(@NonNull View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            tvLike = view.findViewById(R.id.video_like);
            tvCollect = view.findViewById(R.id.video_collect);
            tvTitle = view.findViewById(R.id.video_title);
            tvAuthor = view.findViewById(R.id.video_author);
            tvComments = view.findViewById(R.id.video_comments);
        }
    }

}

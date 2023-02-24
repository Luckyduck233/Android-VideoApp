package com.example.videoapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videoapp.R;
import com.example.videoapp.easyMockEntity.Login.video.Data;
import com.example.videoapp.entity2.ListDTO;
import com.example.videoapp.entity2.MyDTO;
import com.example.videoapp.listener.OnItemChildClickListener;
import com.example.videoapp.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import xyz.doikki.videocontroller.component.PrepareView;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ListDTO> mDatas;
    private List<MyDTO> mDatas2;
    private List<Data> mTestData;
    private String mHeadImgUrl;
    List<Object> imgUrlList = new ArrayList<>();
    List<Object> imgUrlList2 = new ArrayList<>();

    private static String TAG = "VideoAdapter";
    int count = 0;

    public OnItemChildClickListener mOnItemChildClickListener;

    public OnItemClickListener mOnItemClickListener;

    public void setmDatas(List<Data> datas) {
//        this.mDatas = datas;
//        Log.e(TAG, mDatas.toString());
        this.mTestData = datas;
        Log.e(TAG, mTestData.toString());
    }

    public void setmDatas2(List<MyDTO> mDatas2) {
        this.mDatas2 = mDatas2;
    }

    public void setHeadimgUrl(String headimgUrl) {
        this.mHeadImgUrl = headimgUrl;
        imgUrlList.add(headimgUrl);
    }

    public void setHeadimgUrl2(List<Object> headimgUrl2) {
        this.imgUrlList2 = headimgUrl2;
        Log.d(TAG, "setHeadimgUrl2: ");
//        Log.e("jiahao",imgUrlList2.toString() );
    }

    public VideoAdapter(Context context) {
        this.mContext = context;
    }

    public VideoAdapter(Context context, List<ListDTO> datas, List<MyDTO> datas2) {
//        Log.e(TAG, "VideoAdapter: ");
        this.mContext = context;
        this.mDatas = datas;
        this.mDatas2 = datas2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.e(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Log.e(TAG, "onBindViewHolder: ");
        ViewHolder vh = (ViewHolder) holder;

        Data videoEntity = mTestData.get(position);
//        Log.d(TAG, position+"");
//        Log.e(TAG, mDatas.toString());
        vh.tvTitle.setText(String.valueOf(mTestData.get(position).getVtitle()));
        vh.tvAuthor.setText(String.valueOf(mTestData.get(position).getAuthor()));
        vh.tvCollect.setText(String.valueOf(mTestData.get(position).getCommentNum()));
        vh.tvComments.setText(String.valueOf(mTestData.get(position).getCommentNum()));
        vh.tvLike.setText(String.valueOf(mTestData.get(position).getLikeNum()));

        Picasso.with(mContext).load(videoEntity.getHeadurl()).into(vh.imgHeader);
        Picasso.with(mContext).load(videoEntity.getCoverurl()).into(vh.mThumb);

//        Log.e("position is", position+"" );
//        Log.e("imgUrlList2.size is", imgUrlList2.size()+"" );
//        String s = imgUrlList2.get(position).toString();
//        Log.d("jiahao", s );
//        Log.e("jjh000999", imgUrlList2.get(position).toString() );
//        Log.e("jjh00099900", position + "");
//        Log.e("jjh00099900", imgUrlList2.size() + "");
//        Log.e("jjh000999", imgUrlList2.toString());
//        String imgUrl = imgUrlList2.get(position).toString();
//        Log.e("jjh000999", imgUrl);

//        Picasso.with(mContext).load(imgUrl).into(vh.imgHeader);

//        Log.d("jjh000666", (String) imgUrlList2.get(position));
//        ListDTO data = mDatas.get(position);
//        MyDTO myDTO = mDatas2.get(position);
//        vh.tvComments.setText(String.valueOf(data.getCommentNum()));
//        vh.tvCollect.setText(String.valueOf(data.getCollectNum()));
//        vh.tvAuthor.setText(String.valueOf(data.getAuthor()));
//        vh.tvTitle.setText(String.valueOf(data.getVtitle()));
//        vh.tvLike.setText(String.valueOf(data.getLikeNum()));
//
//        Picasso.with(mContext).load(data.getHeadurl()).into(vh.imgHeader);
//        Picasso.with(mContext).load(data.getCoverurl()).into(vh.imgCover);
//        Picasso.with(mContext).load(myDTO.getHeadUrl()).into(vh.imgCover);
//        Picasso.with(mContext).load(myDTO.getHeadUrl()).into(vh.imgHeader);

        vh.mPosition = position;
    }

    @Override
    public int getItemCount() {
//        Log.e(TAG, "getItemCount: "+mDatas.size());
        if (mTestData != null) {
//            Log.e("jjh000999", mDatas.size()+"" );
            return mTestData.size();
//            return 5;
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle, tvCollect, tvLike, tvAuthor, tvComments;
        private LinearLayout btnLike;
        private ImageView imgHeader, imgCover;
        /*缩略图*/
        public ImageView mThumb;
        public PrepareView mPrepareView;
        public FrameLayout mPlayerContainer;
        public int mPosition;

        public ViewHolder(@NonNull View view) {
            super(view);
//            Log.e(TAG, "ViewHolder: ");
            initView(view);
//            initListener();
        }

        private void initView(View view) {
            tvLike = view.findViewById(R.id.video_likeNum);
            tvCollect = view.findViewById(R.id.video_collectNum);
            tvTitle = view.findViewById(R.id.video_title);
            tvAuthor = view.findViewById(R.id.video_author);
            tvComments = view.findViewById(R.id.video_commentsNum);
            imgHeader = view.findViewById(R.id.img_header);
            btnLike = view.findViewById(R.id.video_like_btn);
//            imgCover = view.findViewById(R.id.img_cover);
            mPlayerContainer = view.findViewById(R.id.player_container);
            mPrepareView = view.findViewById(R.id.prepare_view);
            mThumb = mPrepareView.findViewById(xyz.doikki.videocontroller.R.id.thumb);

            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                view.setOnClickListener(this);
            }
            //通过tag将ViewHolder和itemView绑定
            view.setTag(this);

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("gjlnb", mPosition+"");
                }
            });
        }

        private void initListener() {
            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }
            //通过tag将ViewHolder和itemView绑定
            itemView.setTag(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.player_container) {
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildClick(mPosition);
                }
            } else {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mPosition);
                }
            }
        }
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
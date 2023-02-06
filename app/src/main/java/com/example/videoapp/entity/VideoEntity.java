package com.example.videoapp.entity;

import java.io.Serializable;

public class VideoEntity implements Serializable {
    private int vid;

    private String vtitle;

    private String author;

    private String coverurl;

    private String headurl;

    private int commentNum;

    private int likeNum;

    private int collectNum;

    private String playurl;

    private String createTime;

    private String updateTime;

    private int categoryId;

    private String categoryName;

    private VideoSocialEntity videoSocialEntity;

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getVid() {
        return this.vid;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getVtitle() {
        return this.vtitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getCoverurl() {
        return this.coverurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getHeadurl() {
        return this.headurl;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getCommentNum() {
        return this.commentNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getLikeNum() {
        return this.likeNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public int getCollectNum() {
        return this.collectNum;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public String getPlayurl() {
        return this.playurl;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return this.categoryName;
    }


    public VideoSocialEntity getVideoSocialEntity() {
        return videoSocialEntity;
    }

    public void setVideoSocialEntity(VideoSocialEntity videoSocialEntity) {
        this.videoSocialEntity = videoSocialEntity;
    }

    public static class VideoSocialEntity {

        private int commentnum;
        private int likenum;
        private int collectnum;
        private boolean flagLike;
        private boolean flagCollect;

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public int getLikenum() {
            return likenum;
        }

        public void setLikenum(int likenum) {
            this.likenum = likenum;
        }

        public int getCollectnum() {
            return collectnum;
        }

        public void setCollectnum(int collectnum) {
            this.collectnum = collectnum;
        }

        public boolean isFlagLike() {
            return flagLike;
        }

        public void setFlagLike(boolean flagLike) {
            this.flagLike = flagLike;
        }

        public boolean isFlagCollect() {
            return flagCollect;
        }

        public void setFlagCollect(boolean flagCollect) {
            this.flagCollect = flagCollect;
        }
    }
}

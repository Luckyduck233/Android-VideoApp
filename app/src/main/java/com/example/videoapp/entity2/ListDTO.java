package com.example.videoapp.entity2;

public class ListDTO {



    private Integer vid;
    private String vtitle;
    private String author;
    private String coverurl;
    private String headurl;
    private Integer commentNum;
   private Integer likeNum;
    private Integer collectNum;
    private String playurl;
    private String createTime;
    private String updateTime;
    private Integer categoryId;
    private String categoryName;
    private Object videoSocialEntity;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Object getVideoSocialEntity() {
        return videoSocialEntity;
    }

    public void setVideoSocialEntity(Object videoSocialEntity) {
        this.videoSocialEntity = videoSocialEntity;
    }

    @Override
    public String toString() {
        return "ListDTO{" +
                "vid=" + vid +
                ", vtitle='" + vtitle + '\'' +
                ", author='" + author + '\'' +
                ", coverurl='" + coverurl + '\'' +
                ", headurl='" + headurl + '\'' +
                ", commentNum=" + commentNum +
                ", likeNum=" + likeNum +
                ", collectNum=" + collectNum +
                ", playurl='" + playurl + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", videoSocialEntity=" + videoSocialEntity +
                '}';
    }
}

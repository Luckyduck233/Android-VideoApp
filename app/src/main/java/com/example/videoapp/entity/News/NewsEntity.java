package com.example.videoapp.entity.News;

import java.io.Serializable;
import java.util.List;

public class NewsEntity implements Serializable {
    private Integer type;
    private Integer newsId;
    private String newsTitle;
    private String authorName;
    private String headerUrl;
    private Integer commentCount;
    private String releaseDate;
//    private Integer type;
    private Object imgList;
    private List<ThumbEntitiesBean> thumbEntities;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

//    public Integer getType() {
//        return type;
//    }

//    public void setType(Integer type) {
//        this.type = type;
//    }

    public Object getImgList() {
        return imgList;
    }

    public void setImgList(Object imgList) {
        this.imgList = imgList;
    }

    public List<ThumbEntitiesBean> getThumbEntities() {
        return thumbEntities;
    }

    public void setThumbEntities(List<ThumbEntitiesBean> thumbEntities) {
        this.thumbEntities = thumbEntities;
    }
}

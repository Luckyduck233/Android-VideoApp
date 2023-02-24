package com.example.videoapp.easyMockEntity.Login.news;

import java.util.List;

public class Data {
    private Integer nid;
    private String ntitle;
    private String author;
    private String headUrl;
    private String createTime;
    private int type;
    private int commentNum;

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    private List<CoverUrlList> coverUrlList;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;

    }

    public List<CoverUrlList> getCoverUrlList() {
        return coverUrlList;
    }

    public void setCoverUrlList(List<CoverUrlList> coverUrlList) {
        this.coverUrlList = coverUrlList;
    }
}

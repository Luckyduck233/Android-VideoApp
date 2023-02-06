package com.example.videoapp.entity.News;

import java.util.List;

public class PageBean {
    private Integer totalCount;
    private Integer pageSize;
    private Integer totalPage;
    private Integer currPage;
    private List<NewsEntity> list;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public List<NewsEntity> getList() {
        return list;
    }

    public void setList(List<NewsEntity> list) {
        this.list = list;
    }
}

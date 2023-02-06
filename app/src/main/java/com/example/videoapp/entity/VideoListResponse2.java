package com.example.videoapp.entity;

import java.io.Serializable;
import java.util.List;

public class VideoListResponse2 implements Serializable {
    private String msg;

    private int code;

    private PageBean pageBean;

    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setPageBean(PageBean pageBean){
        this.pageBean = pageBean;
    }
    public PageBean getPageBean(){
        return this.pageBean;
    }

    public static class PageBean {
        private int totalCount;

        private int pageSize;

        private int totalPage;

        private int currPage;

        private List<VideoEntity> list;

        public void setTotalCount(int totalCount){
            this.totalCount = totalCount;
        }
        public int getTotalCount(){
            return this.totalCount;
        }
        public void setPageSize(int pageSize){
            this.pageSize = pageSize;
        }
        public int getPageSize(){
            return this.pageSize;
        }
        public void setTotalPage(int totalPage){
            this.totalPage = totalPage;
        }
        public int getTotalPage(){
            return this.totalPage;
        }
        public void setCurrPage(int currPage){
            this.currPage = currPage;
        }
        public int getCurrPage(){
            return this.currPage;
        }

        public List<VideoEntity> getList() {
            return list;
        }

        public void setList(List<VideoEntity> list) {
            this.list = list;
        }
    }
}

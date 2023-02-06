package com.example.videoapp.entity2;

import java.io.Serializable;

public class VideoListResponse implements Serializable {

    private String msg;
    private Integer code;
    private PageDTO page;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public PageDTO getPage() {
        return page;
    }

    public void setPage(PageDTO page) {
        this.page = page;
    }
}

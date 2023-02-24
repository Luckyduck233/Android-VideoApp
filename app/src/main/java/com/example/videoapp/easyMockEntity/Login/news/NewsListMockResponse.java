package com.example.videoapp.easyMockEntity.Login.news;

import java.util.List;

public class NewsListMockResponse {
    private Integer code;
    private List<Data> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}

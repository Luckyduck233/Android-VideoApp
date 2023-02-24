package com.example.videoapp.easyMockEntity.Login.video;

import java.util.List;
public class VideoListResponseTest {
    @Override
    public String toString() {
        return "VideoListResponseTest{" +
                "code=" + code +
                ", topTabSize=" + topTabSize +
                ", data=" + data +
                ", topTab=" + topTab +
                '}';
    }

    private Integer code;
    private Integer topTabSize;


    private List<Data> data;
    private List<String> topTab;

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

    public List<String> getTopTab() {
        return topTab;
    }

    public void setTopTab(List<String> topTab) {
        this.topTab = topTab;
    }
}

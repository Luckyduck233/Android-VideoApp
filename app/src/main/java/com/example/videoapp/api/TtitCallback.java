package com.example.videoapp.api;

import okhttp3.Call;
import okhttp3.Response;

public interface TtitCallback {
    void onSuccess(Call call, Response response, String result);

    void onFailure(Call call,Exception e);
}

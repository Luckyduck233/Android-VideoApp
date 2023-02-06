package com.example.videoapp.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.videoapp.activity.LoginActivity;
import com.example.videoapp.util.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
    private static final String TAG = "ApiApiApi";
    private static String requestUrl;
    private static HashMap<String, Object> mParams;
    private static OkHttpClient client;
    public static Api api = new Api();

    public Api() {

    }

    public static Api config(String url, HashMap<String, Object> params) {
        Log.d(TAG, "config: ");
        client = new OkHttpClient.Builder()
                .build();
        requestUrl = ApiConfig.BASE_URL2 + url;
        mParams = params;
        return api;
    }

    public static Api config2(String url, HashMap<String, Object> params) {
        Log.d(TAG, "config2: ");
        client = new OkHttpClient.Builder().build();
        requestUrl = url;
        mParams = params;
        return api;
    }

    public void getRequest(Context context, TtitCallback callback) {
        SharedPreferences sp = context.getSharedPreferences(ApiConfig.SP_TOKEN_NAME, Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        String url = getAppendUrl(requestUrl, mParams);
        Log.e(TAG, url);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("token", token)
                .get()
                .build();
        String token1 = request.header("token");
        Log.e(TAG, "token："+token1);

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
//                Log.e(TAG,"result :"+ result );
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String code = String.valueOf(jsonObject.get("code"));
                    if (code.equals("401")) {
                        context.startActivity(new Intent(context, LoginActivity.class));
                    } else {
                        callback.onSuccess(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void postRequest(TtitCallback callback) {
        JSONObject jsonObject = new JSONObject(mParams);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);
        Log.e(TAG, requestUrl );
//        第三步创建Request
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(requestBodyJson)
                .addHeader("contentType", "application/json;charset=UTF-8")
                .build();

//        第四步创建call回调对象
        Call call = client.newCall(request);

//        第五步发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                callback.onSuccess(result);
            }
        });
    }

    private String getAppendUrl(String url, Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            StringBuffer buffer = new StringBuffer();
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (TextUtils.isEmpty(buffer.toString())) {
                    buffer.append("?");
                } else {
                    buffer.append("&");
                }
                buffer.append(entry.getKey()).append("=").append(entry.getValue());
            }
            url += buffer.toString();
        }

        return url;
    }

}
//    //        第一步创建OkHttpClient
//    OkHttpClient client = new OkHttpClient.Builder().build();
//
//    HashMap map = new HashMap();
//        map.put("mobile", account);
//        map.put("password", pwd);
//    JSONObject jsonObject = new JSONObject(map);
//    String jsonStr = jsonObject.toString();
//    RequestBody requestBodyJson = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);
//
//    //        第三步创建Request
//    Request request = new Request.Builder()
//            .url(AppConfig.BASE_URL2 + "/app/login")
//            .addHeader("contentType", "application/json;charset=UTF-8")
//            .post(requestBodyJson)
//            .build();
//
//    //        第四步创建Call回调对象
//    Call call = client.newCall(request);
////        第五步发起请求
//        call.enqueue(new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//            Log.e("onFailure",e.getMessage() );
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//            Log.d(TAG, "okhttp请求成功");
//            String result = response.body().string();
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    showToast(result);
//                }
//            });
//
//        }
//    });

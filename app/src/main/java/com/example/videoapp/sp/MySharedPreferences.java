package com.example.videoapp.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.videoapp.api.ApiConfig;

public class MySharedPreferences {

    private static MySharedPreferences mySharedPreferences = new MySharedPreferences();
    private static String TAG = "MySharedPreferences";
    private static SharedPreferences sharedPreferences;
    private static Context mContext;


    public static MySharedPreferences config(Context context, String spName, int mode) {
        Log.d(TAG, "getInstance: ");
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(spName, mode);
        return mySharedPreferences;
    }

    private MySharedPreferences() {
        Log.d(TAG, "MySharedPreferences: ");
    }

    /*
    SharedPreferences保存数据方法
    * content 上下文
    * key 键
    * object 值
    * */
    public void setParam(String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }
        editor.commit();
    }


    /*
    获取数据方法
    * context 上下文
    * key 键
    * object 值
    */
    public Object getParam(String key, Object object) {
        String type = object.getClass().getSimpleName();
        if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) object);
        } else if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) object);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) object);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) object);
        }
        String s = toString();
        Log.d(TAG, "getParam: "+s);
        return null;
    }

    /*
     * 删除指定数据
     * */
    public void clearTargetData(String key) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(key).commit();
    }

    /*
    * 删除所有数据
    * */
    public void clearAllData() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear().commit();
    }
}
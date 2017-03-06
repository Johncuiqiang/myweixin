package com.yzh.weixin.utils;

import android.util.Log;

/**
 * Copyright(c) by Yingzhi Tech and WHRTTV
 * User: fred
 * Date: 13-11-29 18:07
 */
public final class LogUtil {
    private static final String TAG = "bjeeaoa";
    public static void i(String msg){
        Log.i(TAG, msg);
    }
    public static void e(String msg){
        Log.e(TAG, msg);
    }
    public static void e(String msg, Throwable e){
        Log.e(TAG, msg, e);
    }
}

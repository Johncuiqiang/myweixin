package com.yzh.weixin.utils;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;

public final class ContextUtil extends Application {

    private static ContextUtil instance;

    public static ContextUtil getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SDKInitializer.initialize(getApplicationContext());
      /*  CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());*/
    }
}



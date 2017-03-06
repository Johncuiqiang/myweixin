package com.yzh.weixin.application;

import android.app.Activity;
import android.app.Application;


import java.util.LinkedList;

/**
 * Created by cuiqiang on 2016/4/27.
 */
public class SysApplication extends Application {
    private LinkedList<Activity> mList = new LinkedList();
    private static SysApplication instance;

    private SysApplication() {
    }
    public synchronized static SysApplication getInstance() {
        if (null == instance) {
            instance = new SysApplication();
        }
        return instance;
    }
    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }


}


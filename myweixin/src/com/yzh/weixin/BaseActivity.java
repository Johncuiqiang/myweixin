package com.yzh.weixin;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.utils.DisplayUtil;
;

/**
 * Created by cuiqiang on 2016/6/7.
 */
public class BaseActivity extends FragmentActivity {

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //lltoolbar.setPadding(0, statusBarHeight, 0, 0);
            *//*ViewGroup.LayoutParams p = view.getLayoutParams();
            p.height = DisplayUtil.px2dip(this, 270);
            view.setLayoutParams(p);*//*
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }*/
}
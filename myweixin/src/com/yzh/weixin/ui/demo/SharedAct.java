package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.iflytek.cloud.*;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.common.MyToast;

/**
 * Created by cuiqiang on 2016/5/23.
 */
public class SharedAct extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_voice);
        initView();
    }
    public void  initView(){
       /* b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(listen);
        b2.setOnClickListener(speak)*/;
    }
    private View.OnClickListener listen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    private View.OnClickListener speak = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SharedAct");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SharedAct");
        MobclickAgent.onPause(this);
    }

}

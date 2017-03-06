package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;


/**
 * Created by cuiqiang on 2016/5/23.
 */
public class SlindingAct extends Activity {


    private SlidingDrawer mDrawer;
    private ImageButton imbg;
    private Boolean flag=false;
    private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_slinding);
        initView();
        initData();
    }

    public void initView() {
        imbg=(ImageButton)findViewById(R.id.handle);
        mDrawer=(SlidingDrawer)findViewById(R.id.slidingdrawer);
        tv=(TextView)findViewById(R.id.tv);
    }
    public void initData(){
        mDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener()
        {
            @Override
            public void onDrawerOpened() {
                flag=true;
                imbg.setImageResource(R.drawable.arrow_small_black);
            }

        });

        mDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener(){

            @Override
            public void onDrawerClosed() {
                flag=false;
                imbg.setImageResource(R.drawable.arrow_gray);
            }

        });

        mDrawer.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener(){

            @Override
            public void onScrollEnded() {
                tv.setText("结束拖动");
            }

            @Override
            public void onScrollStarted() {
                tv.setText("开始拖动");
            }
        });
    }


}

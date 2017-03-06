package com.yzh.weixin.ui.demo;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import com.yzh.weixin.R;
import com.yzh.weixin.autolayout.AutoLayoutActivity;
import com.yzh.weixin.autolayout.utils.AutoUtils;


/**
 * Created by cuiqiang on 2016/5/23.
 */
public class AutoAct extends AutoLayoutActivity {

    private Button four;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auto);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            /*ViewGroup.LayoutParams p=lltoolbar.getLayoutParams();
            p.height = DisplayUtil.dip2px(this,400);
            lltoolbar.setLayoutParams(p);*/
        }
        initView();
        initData();
    }

    public void initView() {
        four = (Button) findViewById(R.id.four);
        //autolayout动态设置代码无效
        ViewGroup.LayoutParams lp=four.getLayoutParams();
        lp.width= AutoUtils.getPercentWidthSize(100);
        four.setLayoutParams(lp);
    }
    public void initData(){

    }


}

package com.yzh.weixin.ui.demo;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.yzh.weixin.R;
import com.yzh.weixin.adapter.AutoAdapter;
import com.yzh.weixin.autolayout.AutoLayoutActivity;
import com.yzh.weixin.autolayout.utils.AutoUtils;
import com.yzh.weixin.personal.PersonMainAdapter;
import com.yzh.weixin.utils.DisplayUtil;
import com.yzh.weixin.utils.ToastUtils;

import java.lang.reflect.Field;


/**
 * Created by cuiqiang on 2016/5/23.
 */
public class AutoListAct extends AutoLayoutActivity {
    private AutoListAct mActivity;
    private ListView lvMain;;
    private int width;
    private int height;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_listview);
        initView();
        initData();
    }

    public void initView() {
        display();
        lvMain=(ListView)findViewById(R.id.lv_main);
        //条目设置
        AutoAdapter autoAdapter = new AutoAdapter(this,height);
        lvMain.setAdapter(autoAdapter);
         setListViewHeightBasedOnChildren(lvMain);
    }
    public void initData(){
    }
    /**
     * 计算屏幕分辨率
     */
    public void display(){
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int screenHeight = dm.heightPixels;


        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        width  = screenWidth;
        height = screenHeight - sbar ;
    }
    /**
     * listview
     * 高度自适应,非条目自适应
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {return;}
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //要把item布局改成linearLayout，否则报空指针
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}

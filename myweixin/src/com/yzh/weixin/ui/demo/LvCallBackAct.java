package com.yzh.weixin.ui.demo;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import com.yzh.weixin.R;
import com.yzh.weixin.adapter.CallBackAdapter;
import com.yzh.weixin.utils.ToastUtils;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by cuiqiang on 2016/5/23.
 */
public class LvCallBackAct extends Activity implements AdapterView.OnItemClickListener {

    // 模拟listview中加载的数据
    private static final String[] CONTENTS = {"北京", "上海", "广州", "深圳", "苏州",
            "南京", "武汉", "长沙", "杭州"};
    private ArrayList<String> contentList ;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lv_callback);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
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
        mListView = (ListView) findViewById(R.id.listview);
        contentList = new ArrayList<String>();
        for (int i = 0; i < CONTENTS.length; i++) {
            contentList.add(CONTENTS[i]);
        }
        //
        mListView.setAdapter(new CallBackAdapter(this, contentList,mListener,yListener));
        mListView.setOnItemClickListener(this);
    }

    public void initData() {

    }

    /**
     * 响应ListView中item的点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
              ToastUtils.show(LvCallBackAct.this,"你点击的是item");
    }
     /**
       * 实现类，响应按钮点击事件
       */
       private CallBackAdapter.MyClickListener mListener = new CallBackAdapter.MyClickListener() {
            @Override
            public void myOnClick(int position, View v) {
                ToastUtils.show(LvCallBackAct.this,"你点击了第" + position + "个button");
            }
       };
    /**
     * 实现类，响应按钮点击事件
     */
    private CallBackAdapter.YmClickListener yListener = new CallBackAdapter.YmClickListener() {
        @Override
        public void myOnClick(View v) {
            ToastUtils.show(LvCallBackAct.this,"你点击了textview");
        }
    };
}

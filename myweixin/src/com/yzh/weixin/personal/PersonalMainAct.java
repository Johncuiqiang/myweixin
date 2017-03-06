package com.yzh.weixin.personal;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.autolayout.AutoLayoutActivity;


/**
 * Created by cuiqiang on 2016/4/22.
 */
public class PersonalMainAct extends AutoLayoutActivity {


    ListView lvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_main);
        initView();
        initData();
    }

    public void initData(){

    }
    public void initView(){
        lvMain=(ListView)findViewById(R.id.lv_main);
        //条目设置
        PersonMainAdapter personMainAdapter = new PersonMainAdapter(this);
        lvMain.setAdapter(personMainAdapter);
        setListViewHeightBasedOnChildren(lvMain);
    }

    /**
     * listview
     * 高度自适应
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

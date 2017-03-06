package com.yzh.weixin.ui.fragment;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.adapter.TimeAdapter;
import com.yzh.weixin.agent.WxAnimationAgent;
import com.yzh.weixin.bean.Share;
import com.yzh.weixin.bean.Time;
import com.yzh.weixin.common.view.MaterialMenuDrawable;
import com.yzh.weixin.utils.DateUtil;

import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/5/10.
 */
public class WeixinFrag extends Fragment {

    private LinearLayout top;
    private ListView lvTime;
    private ArrayList<Time> mList;
    private View popView;
    private PopupWindow popupWindow;
    private View parent;
    private EditText etContent;
    private TextView right;
    private TextView wrong;
    private TimeAdapter timeAdapter;
    private ImageView ivSmall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weixin, null);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        //top = (LinearLayout) v.findViewById(R.id.ll_top);
        lvTime = (ListView) v.findViewById(R.id.lv_time);
        //ivSmall = (ImageView) v.findViewById(R.id.iv_small);
        //ivSmall.setOnClickListener(changeStyle);
        parent = v.findViewById(R.id.main);
        mList =new ArrayList<Time>();
        initLis();
        initData();
        intiDataPopwindow();
        lvTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                appear();
            }
        });

        lvTime.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean scrollFlag=false;//标记是否滑动
            boolean isFirst=true;//标记第一次进入，因为第一次进来lastVisibleItemPosition默认为0，
            // 此时如果listview的第一个显示的条目不是第一个（下表为0），则往下滑也会出现firstVisibleItem>lastVisibleItemPosition的情况
            //所以第一次进入时不做操作，第二次进来已经给lastVisibleItemPosition赋值，就可以判断了
            int lastVisibleItemPosition;//标记上次的显示位置
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState==1||scrollState==2){
                    //其中1 表示滑动，等同于 AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL
                    // 2 表示惯性滑动  等同于 AbsListView.OnScrollListener.SCROLL_STATE_FLING
                    scrollFlag=true;
                }else{
                    scrollFlag=false;
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(!isFirst){
                    if(firstVisibleItem>lastVisibleItemPosition){
                        //执行向上滑动时要做的逻辑
                    }else{

                    }
                    lastVisibleItemPosition=firstVisibleItem;//记录当前条目
                    isFirst=false;
                }

            }
        });
        return v;
    }
    public void initLis() {
        String date = DateUtil.getCurrentTimeWx();
        Time item = new Time();
        item.setText("测试数据之二哈");
        item.setTime(date);
        item.setImg(R.drawable.mosou);
        mList.add(item);
        Time item1 = new Time();
        item1.setText("测试数据之金毛");
        item1.setTime("16.06.13");
        item1.setImg(R.drawable.mosou1);
        mList.add(item1);
        Time item2 = new Time();
        item2.setText("测试数据之霜狼");
        item2.setTime("16.06.14");
        item2.setImg(R.drawable.dog);
        mList.add(item2);
    }
    public void initData() {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);

        View headview=View.inflate(getActivity(), R.layout.item_time_second, null);
        //将viewpager添加到list中作为头
        lvTime.addHeaderView(headview);
        timeAdapter = new TimeAdapter(getActivity(),mList);
        lvTime.setAdapter(timeAdapter);
        lvTime.setDividerHeight(0);
    }

    private void appear() {
        //为popWindow添加动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_animation);
        // 点击弹出泡泡窗口
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }
    private void intiDataPopwindow() {
        popView = getActivity().getLayoutInflater().inflate(R.layout.write_sth, null);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        etContent = (EditText) popView.findViewById(R.id.et_content);
        right = (TextView) popView.findViewById(R.id.right);
        wrong = (TextView) popView.findViewById(R.id.wrong);
        right.setOnClickListener(sure);
        wrong.setOnClickListener(cancel);
    }

    private View.OnClickListener sure =new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            String content=etContent.getText().toString().toLowerCase().trim();
            String date = DateUtil.getCurrentTimeWx();
            Time item = new Time();
            if(content!=null) {
                item.setText(content);
            }else{
                item.setText("写点什么吧");
            }
            item.setTime(date);
            item.setImg(R.drawable.mosou1);
            mList.add(item);
            timeAdapter.notifyDataSetChanged();
            popupWindow.dismiss();
        }
    };
    private View.OnClickListener cancel =new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            popupWindow.dismiss();
        }
    };

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("WeinxinFrag"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("WenXinFrag");
    }
}

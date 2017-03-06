package com.yzh.weixin.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.adapter.NewMsgAdpter;
import com.yzh.weixin.ui.demo.VideoAct;

import com.yzh.weixin.widght.EyeView;
import com.yzh.weixin.widght.PullDownListView;

/**
 * Created by cuiqiang on 2016/5/10.
 */
public class SettingFrag extends Fragment {

    private Activity ctx;
    private View layout;
    private ListView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.fragment_setting, null);
            initView();
            initPullDownView();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent != null) {
                parent.removeView(layout);
            }
        }
        return layout;
    }

    private void initView() {
        // TODO 实现本页面的布局
    }

    private void initPullDownView() {
        final PullDownListView pullDownListView = (PullDownListView) layout
                .findViewById(R.id.pullDownListView);
        final EyeView eyeView = (EyeView) layout.findViewById(R.id.eyeView);

        pullDownListView.getListView().setAdapter(
                new NewMsgAdpter(getActivity()));

        pullDownListView
                .setOnPullHeightChangeListener(new PullDownListView.OnPullHeightChangeListener() {

                    @Override
                    public void onTopHeightChange(int headerHeight,
                                                  int pullHeight) {
                        // TODO Auto-generated method stub
                        float progress = (float) pullHeight
                                / (float) headerHeight;

                        if (progress < 0.5) {
                            progress = 0.0f;
                        } else {
                            progress = (progress - 0.5f) / 0.5f;
                        }

                        if (progress > 1.0f) {
                            progress = 1.0f;
                        }

                        if (!pullDownListView.isRefreshing()) {
                            eyeView.setProgress(progress);
                        }
                    }

                    @Override
                    public void onBottomHeightChange(int footerHeight,
                                                     int pullHeight) {
                        // TODO Auto-generated method stub
                        float progress = (float) pullHeight
                                / (float) footerHeight;

                        if (progress < 0.5) {
                            progress = 0.0f;
                        } else {
                            progress = (progress - 0.5f) / 0.5f;
                        }

                        if (progress > 1.0f) {
                            progress = 1.0f;
                        }

                        if (!pullDownListView.isRefreshing()) {

                        }

                    }

                    @Override
                    public void onRefreshing(final boolean isTop) {
                        // TODO Auto-generated method stub
                        if (isTop) {
                            eyeView.startAnimate();
                        } else {
                            // progressView.startAnimate();
                        }
                        Intent intent = new Intent(ctx,
                                VideoAct.class);
                        ctx.startActivity(intent);
                        ctx.overridePendingTransition(R.anim.push_up_in,
                                R.anim.push_up_out);
                        pullDownListView.pullUp();
                    }

                });

        pullDownListView.getListView().setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {


                    }
                });

    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SettingFrag"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SettingFrag");
    }

}

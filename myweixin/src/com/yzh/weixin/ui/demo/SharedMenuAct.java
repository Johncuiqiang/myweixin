package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.bean.Share;
import com.yzh.weixin.adapter.ShareMenuAdapter;
import com.yzh.weixin.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/5/17.
 */
public class SharedMenuAct extends Activity  {
    private SharedMenuAct mcontext=this;
    private Context context=this;
    private Button btShare;
    private Button btShareNew;
    private Button btHide;
    private GridView gvShare;
    private FrameLayout flUpMenu;
    private View parent;
    private View popView;
    private PopupWindow popupWindow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_share_menu);
        btShare = (Button) findViewById(R.id.bt_share);
        btShareNew = (Button) findViewById(R.id.bt_share_new);
        btHide = (Button) findViewById(R.id.bt_hide);
        gvShare = (GridView) findViewById(R.id.gv_share);
        flUpMenu = (FrameLayout) findViewById(R.id.fl_up_menu);
        parent = findViewById(R.id.main);
        intiData();
        intiDataPopwindow();
        btShare.setOnClickListener(shareMenu);
        btHide.setOnClickListener(shareMenuHide);
        btShareNew.setOnClickListener(shareMenuNew);

    }

    private void intiData() {
        ArrayList<Share> list = new ArrayList<Share>();
        Share item = new Share();
        item.setName("QQ");
        item.setIcon(R.drawable.book_qq);
        list.add(item);
        Share item1 = new Share();
        item1.setName("朋友圈");
        item1.setIcon(R.drawable.book_weixin);
        list.add(item1);
        ShareMenuAdapter shareMenuAdapter = new ShareMenuAdapter(context,list);
        gvShare.setAdapter(shareMenuAdapter);
        shareMenuAdapter.notifyDataSetChanged();

    }
    private View.OnClickListener shareMenu=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
                 show();
        }
    };
    private View.OnClickListener shareMenuHide=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            hide();
        }
    };
    private View.OnClickListener shareMenuNew=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            appear();
        }
    };
    private void show() {
        btShare.setClickable(false);
        flUpMenu.setVisibility(View.VISIBLE);
        flUpMenu.setAnimation(AnimationUtils.loadAnimation(this, R.anim.up_menu_in));
        /*viewCover.setVisibility(View.VISIBLE);
        viewCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.cover_in));*/

    }

    private void hide() {
        btShare.setClickable(true);
        flUpMenu.setVisibility(View.GONE);
        flUpMenu.setAnimation(AnimationUtils.loadAnimation(this, R.anim.up_menu_out));
        /*viewCover.setVisibility(View.GONE);
        viewCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.cover_out));*/
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        hide();
    }
    private void appear() {
        //为popWindow添加动画效果
        popupWindow.setAnimationStyle(R.style.popWindow_animation);
        // 点击弹出泡泡窗口
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }
    private void intiDataPopwindow() {
        popView = getLayoutInflater().inflate(R.layout.share_popwindow, null);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        ArrayList<Share> list = new ArrayList<Share>();
        Share item = new Share();
        item.setName("QQ");
        item.setIcon(R.drawable.book_qq);
        list.add(item);
        Share item1 = new Share();
        item1.setName("朋友圈");
        item1.setIcon(R.drawable.book_weixin);
        list.add(item1);
        ShareMenuAdapter shareMenuAdapter = new ShareMenuAdapter(context,list);
        GridView gvShare1 = (GridView)popView.findViewById(R.id.gv_share1);
        gvShare1.setAdapter(shareMenuAdapter);
        gvShare1.setOnItemClickListener(shareNew);
    }
   private AdapterView.OnItemClickListener shareNew = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    ToastUtils.show(context,"QQ分享成功了");
                    popupWindow.dismiss();
                    break;
                case 1:
                    ToastUtils.show(context,"朋友圈分享成功了");
                    popupWindow.dismiss();
                    break;
            }
        }
    };
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SharedMenuAct");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SharedMenuAct");
        MobclickAgent.onPause(this);
    }

}

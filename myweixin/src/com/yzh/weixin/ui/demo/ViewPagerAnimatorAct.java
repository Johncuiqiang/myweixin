package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ImageView;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.springindicator.SpringIndicator;
import com.yzh.weixin.springindicator.viewpager.ScrollerViewPager;

import java.util.ArrayList;


/**
 * Created by cuiqiang on 2016/5/23.
 */
public class ViewPagerAnimatorAct extends Activity {
    ScrollerViewPager viewPager;
    private static final int[] imgs = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3,R.drawable.bg4};
    private ArrayList<ImageView> views;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_viewpager_animator);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        initPagerItem();
        initView();
    }

    public void initView() {
        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        MyPagerAdapter mPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(mPagerAdapter);
        viewPager.fixScrollSpeed();
        // just set viewPager
        springIndicator.setViewPager(viewPager);
    }
    private void initPagerItem() {
        views = new ArrayList<>();
        for (int id : imgs) {
            ImageView view = (ImageView) View.inflate(this, R.layout.img_viewpager, null);
            view.setImageResource(id);
            views.add(view);
        }
    }
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = views.get(position);
            ViewParent vp =imageView.getParent();
            if (vp!=null){
                ViewGroup parent = (ViewGroup)vp;
                parent.removeView(imageView);
            }
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ViewPagerAnim");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ViewPagerAnim");
        MobclickAgent.onPause(this);
    }
}

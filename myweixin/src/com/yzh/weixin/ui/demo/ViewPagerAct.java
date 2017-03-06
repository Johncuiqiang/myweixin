package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.map.Text;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/5/17.
 */
public class ViewPagerAct extends Activity {

    private static final int[] imgs = {R.drawable.view_pager1, R.drawable.view_pager2, R.drawable.view_pager3};
    private ArrayList<ImageView> views;
    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private Handler mHandler;
    private CirclePageIndicator mIndicator;
    private TextView mTitle;
    private ArrayList<String> mTopList;
    private RelativeLayout mRl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_viewpager);

        initPagerItem();
        initView();

       /* mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (mViewPager != null) {
                    int position = mViewPager.getCurrentItem() + 1;
                    mViewPager.setCurrentItem(position);
                }
                if (mHandler != null) {
                    mHandler.sendEmptyMessageDelayed(0, 5000);
                }
            }
        };
        mHandler.sendEmptyMessageDelayed(0, 5000);*/

    }

    private void initView() {
        mTopList=new ArrayList<String>();
        mTopList.add(0,"腾讯");
        mTopList.add(1,"猎豹");
        mTopList.add(2,"邮箱");
        mRl = (RelativeLayout) findViewById(R.id.rl);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mPagerAdapter = new MyPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);

       // mRl.removeAllViews();
       // int position = 500 - 500 % views.size();

        mIndicator.setViewPager(mViewPager);
        mIndicator.setSnap(true);
        mIndicator.onPageSelected(0);
        //滑动页面,切换标题
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                String title = mTopList.get(position);
                mTitle.setText(title);
            }

            @Override
            public void onPageScrolled(int position,
                                       float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置默认标题
        mTitle.setText(mTopList.get(0));

/*        int position = 500 - 500 % views.size();
        mViewPager.setCurrentItem(position);*/
        //头条新闻自动轮播
        if (mHandler == null) {
            mHandler = new Handler() {
                public void handleMessage(android.os.Message msg) {
                    int currentItem = mViewPager.getCurrentItem();

                    if (currentItem < mTopList.size() - 1) {
                        currentItem++;
                    } else {
                        currentItem = 0;
                    }

                    mViewPager.setCurrentItem(currentItem);

                    mHandler.sendEmptyMessageDelayed(0, 3000);
                }

                ;
            };

            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
        //头条新闻触摸监听
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("按下");
                        //停止轮播
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        //当按下头条新闻之后,没有及时抬起,而是进行触摸移动,导致抬起事件被父控件拦截, 无法响应抬起事件, 此时需要重新启动自动轮播
                        System.out.println("事件取消");
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("抬起");
                        mHandler.sendEmptyMessageDelayed(0, 3000);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

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
            return mTopList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= views.size();
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

    /**
     * 设置小圆点背景
     */
 /*   private void setImageBackground(int selectItems) {
        for (int i = 0; i < imageViews.length; i++) {
            if (i == selectItems) {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }*/
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ViewPager");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ViewPager");
        MobclickAgent.onPause(this);
    }
}

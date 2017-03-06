package com.yzh.weixin.ui.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.common.view.MaterialMenuDrawable;
import com.yzh.weixin.yalantis.com.sidemenu.animation.FlipAnimation;
import com.yzh.weixin.yalantis.com.sidemenu.interfaces.Resourceble;
import com.yzh.weixin.yalantis.com.sidemenu.interfaces.ScreenShotable;
import com.yzh.weixin.yalantis.com.sidemenu.model.SlideMenuItem;
import com.yzh.weixin.yalantis.com.sidemenu.util.ViewAnimator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiqiang on 2016/5/10.
 */
public class SideBarAct extends FragmentActivity implements ViewAnimator.ViewAnimatorListener{

    private final int ANIMATION_DURATION = 200;
    private DrawerLayout drawerLayout;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private LinearLayout linearLayout;
    private LinearLayout lltoolbar;
    private ImageView tool;
    private ScrollView scroll;
    private ImageView icn1;
    private ImageView icn2;
    private ImageView icn3;
    private ImageView icn4;
    private ImageView icn5;
    private ImageView icn6;
    private ImageView icn7;
    private SideBarAct msidebar;
    private MaterialMenuDrawable materialMenu;
    private int flag_X = 1;//菜单按钮X状态
    private int flag_BURGER = 2;//菜单按钮正常状态
    private int flag = flag_BURGER;//默认状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sidebar);
        initView();
        initData();
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               closeDrawer();
            }
        });
        tool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectflag();
                //侧边栏打开，推荐用start，但必须与xml文件中保持一致
                drawerLayout.openDrawer(Gravity.START);
            }
        });
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            //一共三个状态，DrawerLayout调用
            @Override
            public void onDrawerStateChanged(int newState) {

            }
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }
            //在侧边栏打开后，执行代码
            @Override
            public void onDrawerOpened(View arg0) {
                 openDrawer();
            }

            @Override
            public void onDrawerClosed(View arg0) {
                icn1.setVisibility(View.GONE);
                icn2.setVisibility(View.GONE);
                icn3.setVisibility(View.GONE);
                icn4.setVisibility(View.GONE);
                icn5.setVisibility(View.GONE);
                icn6.setVisibility(View.GONE);
                icn7.setVisibility(View.GONE);
            }
        });
    }
    public void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        scroll = (ScrollView) findViewById(R.id.scrollView);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        lltoolbar = (LinearLayout) findViewById(R.id.toolbar);
        tool = (ImageView) findViewById(R.id.iv_tool);
        icn1 = (ImageView) findViewById(R.id.icn1);
        icn2 = (ImageView) findViewById(R.id.icn2);
        icn3 = (ImageView) findViewById(R.id.icn3);
        icn4 = (ImageView) findViewById(R.id.icn4);
        icn5 = (ImageView) findViewById(R.id.icn5);
        icn6 = (ImageView) findViewById(R.id.icn6);
        icn7 = (ImageView) findViewById(R.id.icn7);
    }
    public void initData() {
        contentFragment = ContentFragment.newInstance(R.drawable.content_music);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();
        materialMenu = new MaterialMenuDrawable(this, Color.BLUE, MaterialMenuDrawable.Stroke.EXTRA_THIN);
        tool.setImageDrawable(materialMenu);
    }
    public void openDrawer() {
        animateFirstView(icn1);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateView(icn2);
            }
        }, 100);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateView(icn3);
            }
        }, 200);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateView(icn4);
            }
        }, 300);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateView(icn5);
            }
        }, 400);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateView(icn6);
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateView(icn7);
            }
        }, 600);
    }
    public void closeDrawer() {
        animateHideView(icn1);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateHideView(icn2);
            }
        }, 100);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateHideView(icn3);
            }
        }, 200);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateHideView(icn4);
            }
        }, 300);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateHideView(icn5);
            }
        }, 400);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateHideView(icn6);
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                animateHideView(icn7);
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                drawerLayout.closeDrawers();
            }
        }, 800);
    }
    private void animateFirstView(View view) {
        view.setVisibility(View.VISIBLE);
        FlipAnimation rotation =
                new FlipAnimation(45, 0, 0.0f, view.getHeight() / 2.0f);
        rotation.setDuration(80);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(rotation);
    }

    private void animateView(View view) {
        view.setVisibility(View.VISIBLE);
        FlipAnimation rotation =
                new FlipAnimation(90, 0, 0.0f, view.getHeight() / 2.0f);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(rotation);
    }

    private void animateHideView(View view) {
        FlipAnimation rotation =
                new FlipAnimation(0, 90, 0.0f, view.getHeight() / 2.0f);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(rotation);
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        return null;
    }

    @Override
    public void disableHomeButton() {

    }

    @Override
    public void enableHomeButton() {

    }

    @Override
    public void addViewToContainer(View view) {

    }

    public void selectflag() {
        if (flag == flag_BURGER) {
            //由3条线变左方向键
            materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
            flag = flag_X;
        } else if (flag == flag_X) {
            //由左方向键变对号
            materialMenu.animateIconState(MaterialMenuDrawable.IconState.CHECK);
            flag = flag_BURGER;
        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SideBarAct");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SideBarAct");
        MobclickAgent.onPause(this);
    }

}

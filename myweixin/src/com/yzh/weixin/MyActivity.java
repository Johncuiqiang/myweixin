package com.yzh.weixin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.baidu.mapapi.SDKInitializer;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.common.view.MaterialMenuDrawable;
import com.yzh.weixin.adapter.MyFragPagerAdapter;
import com.yzh.weixin.ui.fragment.DemoFrag;
import com.yzh.weixin.ui.fragment.FindFrag;
import com.yzh.weixin.ui.fragment.SettingFrag;
import com.yzh.weixin.ui.fragment.WeixinFrag;
import com.yzh.weixin.utils.DisplayUtil;
import com.yzh.weixin.utils.ToastUtils;

import java.util.ArrayList;

public class MyActivity extends BaseActivity {
    private TextView tv;
    private MaterialMenuDrawable materialMenu;
    private int flag_X = 1;//菜单按钮X状态
    private int flag_BURGER = 2;//菜单按钮正常状态
    private int flag = flag_BURGER;//默认状态
    private ImageView  toolbar;
    private RadioGroup mRadioGroup;
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private MyFragPagerAdapter fragmentadapter;
    private RadioButton rbMain;
    private RadioButton rbFind;
    private RadioButton rbSetting;
    private RadioButton rbDemo;
    private LinearLayout lltoolbar;
    private Context context=this;
    private TextView tvToolbar;
    private FindFrag findFrag;
    private int statusBarHeight;
    private MyActivity myactivity = this;
    private RelativeLayout main;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);
        initMap();
        setContentView(R.layout.main);
        initView();
        initData();
    }

    private void  initView(){
        main = (RelativeLayout) findViewById(R.id.main);
        toolbar = (ImageView) findViewById(R.id.toolbar_icon);
        tvToolbar = (TextView) findViewById(R.id.toolbar_tv);
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        toolbar.setImageDrawable(materialMenu);
        toolbar.setOnClickListener(selectflag);
        lltoolbar = (LinearLayout) findViewById(R.id.ll_toolbar);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_rg);
        rbMain = (RadioButton) findViewById(R.id.rb_main);
        rbFind = (RadioButton) findViewById(R.id.rb_find);
        rbSetting = (RadioButton) findViewById(R.id.rb_setting);
        rbDemo = (RadioButton) findViewById(R.id.rb_demo);
        mPager = (ViewPager)findViewById(R.id.vPager);
        lltoolbar.setOnClickListener(changestyle);
        mRadioGroup.setOnCheckedChangeListener(listener);
        InitViewPager();
    }
    private void  initData(){
        statusBarHeight = getStatusBarHeight(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    /**
     * 用于获取状态栏的高度。 使用Resource对象获取（推荐这种方式）
     * 目前实现结果并太好，需要改进
     * @return 返回状态栏高度的像素值。
     */
    public  int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private View.OnClickListener selectflag = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            selectflag();
        }
    };
    private void  initMap(){
        SDKInitializer.initialize(getApplicationContext());
    }
    private View.OnClickListener changestyle = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    public void selectflag() {
        if (flag == flag_BURGER) {
            //由3条线变
            materialMenu.animateIconState(MaterialMenuDrawable.IconState.X);
            lltoolbar.setBackgroundColor(Color.parseColor("#464950"));
            main.setBackgroundColor(Color.parseColor("#464950"));
            flag = flag_X;
            System.out.println("11111111" + 15.2 % 5);
        } else if (flag == flag_X) {
            //由X变3条线
            materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER);
            lltoolbar.setBackgroundColor(Color.parseColor("#48BDF0"));
            main.setBackgroundColor(Color.parseColor("#48BDF0"));
            flag = flag_BURGER;
        }
    }
    protected RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.rb_main:
                    mPager.setCurrentItem(0, false);
                    break;
                case R.id.rb_find:
                    mPager.setCurrentItem(1, false);
                    break;
                case R.id.rb_setting:
                    mPager.setCurrentItem(2, false);
                    break;
                case R.id.rb_demo:
                    mPager.setCurrentItem(3, false);
                    break;
            }
        }
    };
    private void InitViewPager() {
        fragmentsList = new ArrayList<Fragment>();
        WeixinFrag weixinFrag=new WeixinFrag();
        SettingFrag settingFrag=new SettingFrag();
        findFrag = new FindFrag();
        DemoFrag demoFrag=new DemoFrag();
        fragmentsList.add(weixinFrag);
        fragmentsList.add(findFrag);
        fragmentsList.add(settingFrag);
        fragmentsList.add(demoFrag);
        fragmentadapter = new MyFragPagerAdapter(getSupportFragmentManager(), fragmentsList);
        mPager.setAdapter(fragmentadapter);
        mPager.setOnPageChangeListener(viewpagerLis);
        mPager.setCurrentItem(0);
        lltoolbar.setVisibility(View.GONE);
    }

    /* private void InitViewPager() {
         FragmentTransaction transaction = getSupportFragmentManager()
                 .beginTransaction();
         transaction.add(R.id.vPager, weixinFrag);
         transaction.commit();
     }*/
    public ViewPager.OnPageChangeListener viewpagerLis = new ViewPager.OnPageChangeListener() {


        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            switch (i) {
                case 0:
                    MobclickAgent.onEvent(context,"weixin");
                    lltoolbar.setVisibility(View.GONE);
                    rbMain.setChecked(true);
                    break;
                case 1:
                    MobclickAgent.onEvent(context,"find");
                    rbFind.setChecked(true);
                    lltoolbar.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    MobclickAgent.onEvent(context,"setting");
                    rbSetting.setChecked(true);
                    lltoolbar.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    MobclickAgent.onEvent(context,"demo");
                    rbDemo.setChecked(true);
                    lltoolbar.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(findFrag!=null){
            findFrag.resultAgent(requestCode,resultCode,data);
        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}

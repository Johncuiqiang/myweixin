package com.yzh.weixin.photo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.weixin.R;

/**
 * Created by Kind on 16/3/11.
 */
public class BaseExActivity extends FragmentActivity {

    private int startInAnimationResources = 0;
    private int startOutAnimationResources = 0;
    private int finishInAnimationResources = 0;
    private int finishOutAnimationResources = 0;
    private InputMethodManager inputMethodManager;
    private boolean isInAnimated = false;

    public static int DEVICE_WIDTH;
    public static int DEVICE_HEIGHT;

    private boolean resume = false;

    private boolean isCanBack = true;
    /**
     * 事件控制器，用于控制本实例中的所有事件的添加和销毁管理<br>
     * 使用时才创建。
     *
     * @author JohnsonLi
     * @qq 505214658
     * @date 2015-04-11
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //avoid IllegalStateException: Can not perform this action after onSaveInstanceState
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置系统的背景颜色
        // getWindow().setBackgroundDrawableResource(R.color.common_bg);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        getScreenSize();

        if (isCanBack) {
            initSlideBack();
        }
    }

    /**
     * 顶层触摸控制
     */
    private void initSlideBack() {
    }

    /**
     * 右滑返回：添加忽略view，内部维护一个list，可在一个页面添加多个忽略view
     */
    public void addIgnoredView(View v) {

    }

    /**
     * 设置当前页面是否支持滑动退出，需要写在继承该类的子类onCreate中super.onCreate();的前面
     *
     * @param isCanBack
     */
    public void isCanBack(boolean isCanBack) {
        this.isCanBack = isCanBack;
    }

    /**
     * 设置打开界面和关闭界面的动画效果
     *
     * @param startInAnimationResources
     * @param startOutAnimationResources
     * @param finishInAnimationResources
     * @param finishOutAnimationResources
     */
    public void setInOutAnimation(int startInAnimationResources,
                                  int startOutAnimationResources,
                                  int finishInAnimationResources,
                                  int finishOutAnimationResources) {
        this.startInAnimationResources = startInAnimationResources;
        this.startOutAnimationResources = startOutAnimationResources;
        this.finishInAnimationResources = finishInAnimationResources;
        this.finishOutAnimationResources = finishOutAnimationResources;
    }


    @Override
    protected void onPause() {
        super.onPause();
        resume = false;

    }

    /**
     * 设置返回组件
     *
     * @param view
     */
    public void setBackView(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    /**
     * 设置返回按钮
     *
     * @param resourcesId
     */
    public void setBackView(int resourcesId) {
        View view = this.findViewById(resourcesId);
        view.setVisibility(View.VISIBLE);
        setBackView(view);
    }

    /**
     * 设置返回按钮
     *
     * @param resourcesId
     */
    public void setBackView(int resourcesId, String title) {
        View view = this.findViewById(resourcesId);
        view.setVisibility(View.VISIBLE);
        setBackView(view);

        setTitle(title);
    }

    /**
     * 设置返回按钮
     *
     * @param resourcesId 资源ID
     * @param title       标题
     * @param listener    资源点击事件
     */
    public void setBackView(int resourcesId, String title, View.OnClickListener listener) {
        View view = this.findViewById(resourcesId);
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(listener);

        setTitle(title);
    }

    /**
     * 设置标题
     *
     * @param txt
     */
    public void setTitle(String txt) {
        TextView textView = (TextView) this.findViewById(R.id.title);
        textView.setVisibility(View.VISIBLE);
        textView.setText(txt);
    }

    /**
     * 设置标题右侧文字
     *
     * @param txt
     */
    public void setTitleRight(String txt, View.OnClickListener listener) {
    }

    /**
     * 设置标题右侧文字
     *
     * @param txt
     */
    public void setTitleRight(String txt, int color, View.OnClickListener listener) {
    }

    private TextView titleLeftText, titleRightText;
    private ImageView titleRightImg;

    /**
     * @return 获取左侧textview，需先set，再get，确保其不为空
     */
    public TextView getTitleLeftText() {
        return titleLeftText;
    }

    /**
     * @return 获取右侧textview，需先set，再get，确保其不为空
     */
    public TextView getTitleRightText() {
        return titleRightText;
    }

    /**
     * @return 获取右侧imageView，需先set，再get，确保其不为空
     */
    public ImageView getTitleRightImg() {
        return titleRightImg;
    }


    /**
     * 设置标题右侧图片
     */
    public void setTitleRightImg(int resId) {
    }

    /**
     * 设置标题右侧图片
     */
    public void setTitleRightImg(int resId, View.OnClickListener listener) {
    }

    /**
     * 设置标题左侧文字，默认字体颜色为紫色
     *
     * @param txt
     */
    public void setTitleLeft(String txt, View.OnClickListener listener) {
    }

    /**
     * 设置标题左侧文字
     *
     * @param txt
     */
    public void setTitleLeft(String txt, int color, View.OnClickListener listener) {
    }

    /**
     * 设置标题左侧图片
     */
    public void setTitleLeftImg(int resId, View.OnClickListener listener) {
    }

    /**
     * 返回
     */
    public void back() {
        //关闭键盘并finish当前页面
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();//在onKeyUp方法中拦截太早了，原系统关于软键盘的处理步骤还没走
    }

    /**
     * 获取参数对象
     */
    public Bundle getParamBundle() {
        Bundle bundle = this.getIntent().getExtras();
        return bundle;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取屏幕宽高
     */
    public void getScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        DEVICE_WIDTH = dm.widthPixels;
        DEVICE_HEIGHT = dm.heightPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        onHideSoftInput(event);
        return super.onTouchEvent(event);
    }

    /**
     * 点击空白处,关闭输入法软键盘
     */
    public void onHideSoftInput(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null
                    && getCurrentFocus().getWindowToken() != null) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public boolean isResume() {
        return resume;
    }

    public void setResume(boolean resume) {
        this.resume = resume;
    }

    public void setOnClick(int id, View.OnClickListener listener) {
        findViewById(id).setOnClickListener(listener);
    }

    private View getRootView() {
        return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
    }

    @Override
    public void finish() {
        super.finish();
        if (finishInAnimationResources != 0) {
            overridePendingTransition(finishInAnimationResources, finishOutAnimationResources);
        }

        //关闭键盘
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
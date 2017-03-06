package com.yzh.weixin.ui.demo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.yzh.weixin.R;
import com.yzh.weixin.gaode.ToastUtil;
import com.yzh.weixin.utils.DisplayUtil;
import com.yzh.weixin.utils.ToastUtils;

/**
 * Created by cuiqiang on 2016/6/13.
 */
public class CeshiAnimAct extends Activity {

    private Boolean flag =true;
    private ImageView img;
    private TextView text;
    private Button ceshi;
    private float width;
    private float height;
    private int metri;
    private AnimatorSet set;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ceshi_anim);
        initView();
        display();
        display2();
    }

    public void initView() {
        img = (ImageView) findViewById(R.id.img);
        text = (TextView) findViewById(R.id.tv);
        ceshi = (Button) findViewById(R.id.bt_ceshi);
        ceshi.setOnClickListener(click);
    }
    View.OnClickListener click =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(flag){
                startClick();
                flag=false;
            }else{
                endClick();
                flag=true;
            }
        }
    };

    /**
     * 计算屏幕分辨率
     */
    public void display(){
        DisplayMetrics  dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int screenHeight = dm.heightPixels;
        width=screenWidth;
        height=screenHeight;
        ToastUtils.show(CeshiAnimAct.this,""+screenWidth);
        ToastUtils.show(CeshiAnimAct.this,""+screenHeight);
    }

    /**
     * 计算屏幕宽高
     */
    public void display2(){
        WindowManager wm = this.getWindowManager();
        int widthmetric = wm.getDefaultDisplay().getWidth();
        int heightmetric = wm.getDefaultDisplay().getHeight();

        int width = DisplayUtil.px2dip(CeshiAnimAct.this,widthmetric);
        int height = DisplayUtil.px2dip(CeshiAnimAct.this,heightmetric);
        /*WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();*/
    }

    /**
     * 上拉动画
     */
    public void startClick() {
        leftsmallAnim(img,height,width);
        uprightAnim(text,height,width);
    }
    /**
     * 返回动画
     */
    public void endClick() {
         bigrightAnim(img,height,width);
         downleftAnim(text,height,width);
    }
    public void leftsmallAnim(View view, float height, float width) {
        set=new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",0,-(float) (0.15*width)),
                ObjectAnimator.ofFloat(view,"translationY",0,-(float) (0.15*height))
                /*ObjectAnimator.ofFloat(view,"scaleX",1.0f,0.55f),
                ObjectAnimator.ofFloat(view,"scaleY",1.0f,0.55f)*/
        );
        set.setDuration(300).start();
    }
    public void bigrightAnim(View view,float height,float width) {
        set=new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",-(float)(0.15*width),0),
                ObjectAnimator.ofFloat(view,"translationY",-(float)(0.15*height),0)
               /* ObjectAnimator.ofFloat(view,"scaleX",0.55f,1.0f),
                ObjectAnimator.ofFloat(view,"scaleY",0.55f,1.0f)*/
        );
        set.setDuration(300).start();
    }
    public void uprightAnim(View view,float height,float width) {
        set=new AnimatorSet();
        set.playTogether(
                    ObjectAnimator.ofFloat(view, "translationX", 0, (float) (0.08 * width)),
                    ObjectAnimator.ofFloat(view, "translationY", 0, -(float) (0.15 * height))
                    /*ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.9f),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.9f)*/
            );
        set.setDuration(300).start();
    }
    private void downleftAnim(View view,float height,float width) {
        AnimatorSet set=new AnimatorSet();
        //判断当前屏幕分辨率
        set.playTogether(
                    ObjectAnimator.ofFloat(view,"translationX",(float)(0.08*width),0),
                    ObjectAnimator.ofFloat(view,"translationY",-(float)(0.15*height),0)
                   /* ObjectAnimator.ofFloat(view,"scaleX",0.9f,1.0f),
                    ObjectAnimator.ofFloat(view,"scaleY",0.9f,1.0f)*/
        );
        set.setDuration(300).start();
        set.addListener( new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator arg0) {
            }
            @Override
            public void onAnimationRepeat(Animator arg0) {
            }
            @Override
            public void onAnimationEnd(Animator arg0) {

            }
            @Override
            public void onAnimationCancel(Animator arg0) {
            }
        });
    }

}

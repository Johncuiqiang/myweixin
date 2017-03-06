package com.yzh.weixin.agent;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.yzh.weixin.R;
import com.yzh.weixin.ui.fragment.WeixinFrag;

/**
 * Created by cuiqiang on 2016/4/11.
 */
public class WxAnimationAgent {

    private Animation animation;
    Context context;
    private AnimatorSet set;

    public WxAnimationAgent(Context context) {
        this.context = context;
    }

    public void enterAnim(View view, float height) {
        set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", 0, -height)
        );
        set.setDuration(400).start();
    }

    public void exitAnim(View view, float height) {
        set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationY", -height, 0)
        );
        set.setDuration(400).start();
    }

    public void disappper(View view) {
        set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 1, 0)
        );
        set.setDuration(400).start();
    }

    public void appper(View view) {
        set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 0, 1)
        );
        set.setDuration(400).start();
    }

    public void enterSmallAnim(View view, float height, float width) {
        set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, -width),
                ObjectAnimator.ofFloat(view, "translationY", 0, -height)
                //ObjectAnimator.ofFloat(view,"rotation",0,1080)
        );
        set.setDuration(400).start();
    }

    public void exitSmallAnim(View view, float height, float width) {
        set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", -width, 0),
                ObjectAnimator.ofFloat(view, "translationY", -height, 0)
                // ObjectAnimator.ofFloat(view,"rotation",0,720)
        );
        set.setDuration(400).start();

    }

   /* public void exitAnim(View view) {
        animation = AnimationUtils.loadAnimation(context, R.anim.new_exit);
        view.setAnimation(animation);
    }

    public void enterAnim(View view) {
        animation = AnimationUtils.loadAnimation(context, R.anim.new_enter);
        view.setAnimation(animation);
    }
    public void exit2Anim(View view) {
        animation = AnimationUtils.loadAnimation(context, R.anim.new_exit2);
        view.setAnimation(animation);
    }*/
}

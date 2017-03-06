package com.yzh.weixin.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yzh.weixin.R;


/**
 * 自定义头部标题栏TitleView
 */
public class TitleView extends LinearLayout {

    // 实例控件
    private LinearLayout imgLeft;
    private TextView tvCenter;
    private LinearLayout imgRight;
    private LinearLayout rl;
    private TextView tvRightText;
    private ImageView imgLeftIcon;

    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TitleView(Context context) {
        super(context);
        init(null);
    }

    //初始化
    private void init(AttributeSet attrs) {
        // 加载控件
        LayoutInflater.from(getContext())
                .inflate(R.layout.new_title_view, this);
        rl = (LinearLayout) findViewById(R.id.title_1);
        imgLeft = (LinearLayout) findViewById(R.id.ll_left);
        tvCenter = (TextView) findViewById(R.id.title_1_center);
        imgRight = (LinearLayout) findViewById(R.id.ll_right);
        tvRightText = (TextView) findViewById(R.id.tv_confirm);
        imgLeftIcon = (ImageView) findViewById(R.id.iv_left_icon);
        if (attrs == null) {
            return;
        }
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.TitleView);
        // 获得属性的总数(检测xml文件里写了多少属性)
        int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            // 取出对应属性的名称的下标
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.TitleView_title_background:
                    Drawable drawable = a.getDrawable(index);
                    rl.setBackgroundDrawable(drawable);
                    break;
                case R.styleable.TitleView_title_text:
                    String str = a.getString(index);
                    tvCenter.setText(str);
                    break;
              case R.styleable.TitleView_title_left_src:
                  imgLeftIcon.setImageDrawable(a.getDrawable(index));
                    break;
               /* case R.styleable.TitleView_title_right_src:
                    imgRight.setImageDrawable(a.getDrawable(index));
                    break;*/
                case R.styleable.TitleView_title_text_color:
                    tvCenter.setTextColor(a.getColor(index, Color.BLACK));
                    break;
                case R.styleable.TitleView_title_text_size:
                    tvCenter.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                            a.getDimension(index, 15));
                    break;
                case R.styleable.TitleView_title_left_visiability:
                    int visible = a.getInt(index, 1);
                    if (visible == 0) {
                        imgLeft.setVisibility(View.GONE);

                    } else if (visible == 1) {
                        imgLeft.setVisibility(View.VISIBLE);

                    } else {
                        imgLeft.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.styleable.TitleView_title_right_visiability:
                    visible = a.getInt(index, 1);
                    if (visible == 0) {
                        imgRight.setVisibility(View.GONE);
                    } else if (visible == 1) {
                        imgRight.setVisibility(View.VISIBLE);
                    } else {
                        imgRight.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.styleable.TitleView_text_right_visiability:
                    visible = a.getInt(index, 1);
                    if (visible == 0) {
                        tvRightText.setVisibility(View.GONE);
                    } else if (visible == 1) {
                        tvRightText.setVisibility(View.VISIBLE);
                    } else {
                        tvRightText.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.styleable.TitleView_icon_left_visiability:
                    visible = a.getInt(index, 1);
                    if (visible == 0) {
                        imgLeftIcon.setVisibility(View.GONE);
                    } else if (visible == 1) {
                        imgLeftIcon.setVisibility(View.VISIBLE);
                    } else {
                        imgLeftIcon.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    }

   //  设置左侧图片方法
    public void setLeftImageResource(int resId) {
        imgLeftIcon.setImageResource(resId);
    }

  /*  // 设置右侧图片方法
    public void setRightImageResource(int resId) {
        imgRight.setImageResource(resId);
    }*/

    //设置文本内容
    public void setText(String title) {
        tvCenter.setText(title);
    }

    // 设置文本内容
    public void setText(int titleRes) {
        tvCenter.setText(titleRes);
    }

    public void setLeftIcon(int visible){
        imgLeftIcon.setVisibility(visible);
    }

    public void setRightText(int visible){
        tvRightText.setVisibility(visible);
    }

    public void setimgLeft(int visible){
        imgLeft.setVisibility(visible);
    }

    public void setimgRight(int visible){
        imgRight.setVisibility(visible);
    }


    public void settvCenter(int visible){
        tvCenter.setVisibility(visible);
    }

    public LinearLayout getLeftLayout() {
        return imgLeft;
    }

    public LinearLayout getRightLayout() {
        return imgRight;
    }

    public TextView getTextView() {
        return tvCenter;
    }

    // 设置左侧图片按钮点击事件
    public void setLeftClickListener(OnClickListener clickListener) {
        imgLeft.setOnClickListener(clickListener);
    }

    //设置右侧图片按钮点击事件
    public void setRightClickListener(OnClickListener clickListener) {
        imgRight.setOnClickListener(clickListener);
    }

    public ImageView getLeftImageView() {
        return imgLeftIcon;
    }

    public TextView getRightTextView() {
        return tvRightText;
    }
    // 设置左侧图标按钮点击事件
    public void setLeftIconClickListener(OnClickListener clickListener) {
        imgLeftIcon.setOnClickListener(clickListener);
    }

    //设置右侧文本按钮点击事件
    public void setRightTextClickListener(OnClickListener clickListener) {
        tvRightText.setOnClickListener(clickListener);
    }

    public void setEnable(Boolean flag) {
        if(flag==true){
            imgLeft.setClickable(true);
            imgRight.setClickable(true);
        }else{
            imgLeft.setClickable(false);
            imgRight.setClickable(false);
        }

    }



}

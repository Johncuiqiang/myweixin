package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.utils.ToastUtils;


/**
 * Created by cuiqiang on 2016/5/23.
 */
public class CeshiAct extends Activity {


    private EditText etCeshi;
    private LinearLayout llToolbar;
    private ImageView ivToolbar;
    private TextView tvToolbar;
    private Button btCeshi;
    private CeshiAct ceshiact = this;
    private TextView tvCeshi;
    private Switch open;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ceshi);
        initView();
        initData();
    }

    public void initView() {
        etCeshi = (EditText) findViewById(R.id.et_ceshi);
        llToolbar = (LinearLayout) findViewById(R.id.ll_toolbar);
        ivToolbar = (ImageView) findViewById(R.id.toolbar_icon);
        tvToolbar = (TextView) findViewById(R.id.toolbar_tv);
        btCeshi = (Button) findViewById(R.id.bt_ceshi);
        tvCeshi = (TextView) findViewById(R.id.tv_ceshi);
        open = (Switch) findViewById(R.id.open);
    }
   // android:maxLength="15" 设置显示文本的长度,多余不显示
   // android:lines="2" 设置显示文本的行数
   // android:lines="2" android:ellipsize="end"省略号
    public void initData(){

        open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });
        ((CompoundButton) findViewById(R.id.switch_main_1)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

//    /**
//     * 按下这个按钮进行的颜色过滤
//     */
//    public final static float[] BT_SELECTED=new float[] {
//            2, 0, 0, 0, 2,
//            0, 2, 0, 0, 2,
//            0, 0, 2, 0, 2,
//            0, 0, 0, 1, 0 };
//
//    /**
//     * 按钮恢复原状的颜色过滤
//     */
//    public final static float[] BT_NOT_SELECTED=new float[] {
//            1, 0, 0, 0, 0,
//            0, 1, 0, 0, 0,
//            0, 0, 1, 0, 0,
//            0, 0, 0, 1, 0 };
//
//    /**
//     * 按钮焦点改变
//     */
//    public final static View.OnFocusChangeListener buttonOnFocusChangeListener=new View.OnFocusChangeListener() {
//
//        @Override
//        public void onFocusChange(View v, boolean hasFocus) {
//            if (hasFocus) {
//                v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));
//                v.setBackgroundDrawable(v.getBackground());
//            }
//            else
//            {
//                v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));
//                v.setBackgroundDrawable(v.getBackground());
//            }
//        }
//    };
//
//    /**
//     * 按钮触碰按下效果
//     */
//    public final static View.OnTouchListener buttonOnTouchListener=new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if(event.getAction() == MotionEvent.ACTION_DOWN){
//                v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));
//                v.setBackgroundDrawable(v.getBackground());
//            }
//            else if(event.getAction() == MotionEvent.ACTION_UP){
//                v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));
//                v.setBackgroundDrawable(v.getBackground());
//            }
//            return false;
//        }
//    };
//
//    /**
//     * 设置图片按钮获取焦点改变状态
//     * @param
//     */
//    public final static void setButtonFocusChanged(View inView)
//    {
//        inView.setOnTouchListener(buttonOnTouchListener);
//        inView.setOnFocusChangeListener(buttonOnFocusChangeListener);
//    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("CeAct");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("CeAct");
        MobclickAgent.onPause(this);
    }
}

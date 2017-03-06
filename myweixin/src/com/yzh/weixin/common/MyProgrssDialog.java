package com.yzh.weixin.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.weixin.R;

/**
 * Created by cuiqiang on 2016/5/12.
 */
public class MyProgrssDialog extends Dialog {
    private static MyProgrssDialog mprogrssDialog;
    public MyProgrssDialog(Context context, int theme) {
        super(context, theme);
    }

    public static MyProgrssDialog createProgrssDialog(Context context, String msg,Boolean outside) {
        mprogrssDialog = new MyProgrssDialog(context,
                R.style.My_pressDialogCustom);
        mprogrssDialog.setContentView(R.layout.my_progress_dialog);
        mprogrssDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        mprogrssDialog.setMessage(msg);
        mprogrssDialog.setCanceledOnTouchOutside(outside);
        return mprogrssDialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (null == mprogrssDialog)
            return;
        ImageView loadingImageView = (ImageView) mprogrssDialog
                .findViewById(R.id.iv_progress_dialog_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) loadingImageView
                .getBackground();
        animationDrawable.start();
    }

    public MyProgrssDialog setMessage(String msg) {
        TextView loadingTextView = (TextView) mprogrssDialog
                .findViewById(R.id.tv_progress_dialog_loading);
        if (!TextUtils.isEmpty(msg))
            loadingTextView.setText(msg);
        else
            loadingTextView.setText("正在加载中。。。");
        return mprogrssDialog;
    }
    //编写显示/隐藏 progress dialog的方法：
  //  private static MyProgrssDialog mProgrssDialog;

   /* public static void showProgrssDialog(String msg,Context context) {
        if (null == mcustomProgrssDialog)
            mcustomProgrssDialog = MyProgrssDialog
                    .createProgrssDialog(context);
        if (null != mcustomProgrssDialog) {
            mcustomProgrssDialog.setMessage(msg);
            mcustomProgrssDialog.show();
        }
    }*/

    public static void hideProgressDialog() {
        if ( null!= mprogrssDialog) {
            mprogrssDialog.dismiss();
          //  mprogrssDialog = null;
        }
    }
}
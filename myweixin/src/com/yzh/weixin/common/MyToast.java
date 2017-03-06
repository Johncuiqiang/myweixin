package com.yzh.weixin.common;

import android.app.Activity;
import android.view.View;
import android.widget.*;
import com.yzh.weixin.R;

/**
 * Created by acer on 2016/5/10.
 */
public class MyToast {

     public static void show(String text, int s, Activity mactivity){
         View toastRoot = mactivity.getLayoutInflater().inflate(R.layout.my_toast, null);
         Toast toast=new Toast(mactivity);
         toast.setView(toastRoot);
         TextView tv=(TextView)toastRoot.findViewById(R.id.text);
         ImageView iv=(ImageView)toastRoot.findViewById(R.id.image);
         tv.setText(text);
         iv.setImageResource(s);
         toast.show();
     }
}

package com.yzh.weixin.dialogfragment;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.yzh.weixin.R;

/**
 * Created by cuiqiang on 2017/2/13.
 */

public class LoginDialogFragment extends DialogFragment {

    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
         View view = inflater.inflate(R.layout.view_dialog2, container);
         getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }

}

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

public class EditNameDialogFragment extends DialogFragment {

    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.view_dialog, container);
         getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         getDialog().requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置自定义的title
        getDialog().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.view_title);

    }
}

package com.yzh.weixin.ui.demo;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import com.yzh.weixin.R;
import com.yzh.weixin.autolayout.AutoLayoutActivity;
import com.yzh.weixin.autolayout.utils.AutoUtils;
import com.yzh.weixin.dialogfragment.EditNameDialogFragment;
import com.yzh.weixin.dialogfragment.LoginDialogFragment;


/**
 * Created by cuiqiang on 2016/5/23.
 */
public class DialogFragmentAct extends Activity {

    private Button mBtnOne;
    private Button mBtnTwo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dialog_fragment);
        initView();
        initData();
    }

    public void initView() {
        mBtnOne = (Button) findViewById(R.id.btn_one);
        mBtnTwo = (Button) findViewById(R.id.btn_two);
    }
    public void initData(){
        mBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog();
            }
        });

        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });
    }

    public void showEditDialog() {
        EditNameDialogFragment editDialog = new EditNameDialogFragment();
        editDialog.show(getFragmentManager(), "editDialog");
    }

    public void showLoginDialog() {
        LoginDialogFragment editDialog = new LoginDialogFragment();
        editDialog.show(getFragmentManager(), "loginDialog");
    }

}

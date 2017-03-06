package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.agent.PopwindowAgent;
import android.view.View.OnClickListener;
import com.yzh.weixin.common.MyToast;
import com.yzh.weixin.common.view.TestPopwindow;
import android.widget.PopupWindow.OnDismissListener;
import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/5/17.
 */
public class PopWindowAct  extends Activity implements OnClickListener,
        OnDismissListener {

    private PopWindowAct mcontext = this;
    private ArrayList<PopwindowAgent> mArrayList = new ArrayList<PopwindowAgent>();
    private TestPopwindow mTestPopwindow = null;
    private int mnSeclectItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_popwindow);
        InitUI();

    }

    private void InitUI() {
        if (mArrayList != null) {
            mArrayList.clear();
            for (int i = 0; i < 3; i++) {
                PopwindowAgent itemTest1 = new PopwindowAgent(R.drawable.head_female, "图片一");
                mArrayList.add(itemTest1);
                PopwindowAgent itemTest2 = new PopwindowAgent(R.drawable.head_male, "图片二");
                mArrayList.add(itemTest2);
            }
        }

        // 实例化TestPopwindow2
        mTestPopwindow = new TestPopwindow(this);
        // 设置点击其他位置mTestPopwindow2消失
        mTestPopwindow.setOnDismissListener(this);

        Button buttonTest2 = (Button) findViewById(R.id.buttonTest2);
        buttonTest2.setOnClickListener(this);
    }

    private void OnPopwindowTest2() {
        if (mTestPopwindow == null)
            return;

        //回到接受
        mTestPopwindow.setOnData(new TestPopwindow.OnGetData() {

            //记录上一次选中的item
            @Override
            public int onSeclectItem() {
                return mnSeclectItem;
            }

            //回调接受函数
            @Override
            public void onDataCallBack(int nSectlect,ArrayList<PopwindowAgent> mArrayList) {
                MyToast.show("listview 的点击" + String.valueOf(nSectlect),R.drawable.head_female_small,mcontext);
                mnSeclectItem = nSectlect;
            }

            //传递数据源过去
            @Override
            public ArrayList<PopwindowAgent> onArrayList() {
                return mArrayList;
            }
        });

        // location获得控件的位置
        int[] location = new int[2];
        View v = findViewById(R.id.buttonTest2);
        if (v != null)
            v.getLocationOnScreen(location); // 控件在屏幕的位置
        mTestPopwindow.setAnimationStyle(R.style.AppBaseTheme);

        // mTestPopwindow2弹出在某控件(button)的下面
        mTestPopwindow.showAtLocation(v, Gravity.TOP | Gravity.LEFT,
                location[0] - v.getWidth(), location[1] + v.getHeight());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonTest2:
                OnPopwindowTest2();
                break;
            default:
                break;
        }
    }

    // 点击其他地方消失
    @Override
    public void onDismiss() {
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("PopWindowAct");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("PopWindowAct");
        MobclickAgent.onPause(this);
    }
}

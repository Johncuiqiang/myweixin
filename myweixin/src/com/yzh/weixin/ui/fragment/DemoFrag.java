package com.yzh.weixin.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.MyActivity;
import com.yzh.weixin.R;
import com.yzh.weixin.common.MyToast;
import com.yzh.weixin.common.MyProgrssDialog;
import com.yzh.weixin.common.MyDialog;
import com.yzh.weixin.dialogfragment.EditNameDialogFragment;
import com.yzh.weixin.dialogfragment.LoginDialogFragment;
import com.yzh.weixin.expendable.ExpendMainAct;
import com.yzh.weixin.personal.PersonalMainAct;
import com.yzh.weixin.photo.PhotoActivity;
import com.yzh.weixin.samplewater.WaterMainAct;
import com.yzh.weixin.selectavatar.PhotoSelectAct;
import com.yzh.weixin.slidebottomviewdemo.SlidePanelMainAct;
import com.yzh.weixin.sortlistview.SortMainAct;
import com.yzh.weixin.ui.demo.WeatherAct;
import com.yzh.weixin.ui.demo.ViewPagerAct;
import com.yzh.weixin.ui.demo.*;
import com.yzh.weixin.adapter.CircleAdapter;

/**
 * Created by cuiqiang on 2016/5/10.
 */
public class DemoFrag extends Fragment {

    private CircleAdapter adapter;
    private GridView gv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_demo, null);
        gv = (GridView) v.findViewById(R.id.gridView);
        adapter = new CircleAdapter(getActivity(), null);
        gv.setAdapter(adapter);
        turnActivity();
        return v;
    }

    private void turnActivity() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(getActivity(), SideBarAct.class);
                        startActivity(intent);
                        MobclickAgent.onEvent(getActivity(),"SideBarAct");
                        break;
                    case 1:
                        ClickDialog();
                        MobclickAgent.onEvent(getActivity(),"Dialog");
                        break;
                    case 2:
                        ClickProgressDialog();
                        MobclickAgent.onEvent(getActivity(),"ProgressDialog");
                        break;
                    case 3:
                        ClickToast();
                        MobclickAgent.onEvent(getActivity(),"Toast");
                        break;
                    case 4:
                        Intent intent2 = new Intent(getActivity(), WebViewAct.class);
                        startActivity(intent2);
                        MobclickAgent.onEvent(getActivity(),"webview");
                        break;
                    case 5:
                        Intent intent3 = new Intent(getActivity(), PopWindowAct.class);
                        startActivity(intent3);
                        MobclickAgent.onEvent(getActivity(),"popwindow");
                        break;
                    case 6:
                        Intent intent4 = new Intent(getActivity(), FlowCloudActivity.class);
                        startActivity(intent4);
                        MobclickAgent.onEvent(getActivity(),"flow");
                        break;
                    case 7:
                        Intent intent5 = new Intent(getActivity(), ViewPagerAct.class);
                        startActivity(intent5);
                        MobclickAgent.onEvent(getActivity(),"viewpager");
                        break;
                    case 8:
                        Intent intent6 = new Intent(getActivity(), PhotoActivity.class);
                        startActivity(intent6);
                        MobclickAgent.onEvent(getActivity(),"photo");
                        break;
                    case 9:
                        Intent intent7 = new Intent(getActivity(), PhotoSelectAct.class);
                        startActivity(intent7);
                        MobclickAgent.onEvent(getActivity(),"newphoto");
                        break;
                    case 10:
                        Intent intent8 = new Intent(getActivity(), WaterMainAct.class);
                        startActivity(intent8);
                        MobclickAgent.onEvent(getActivity(),"water");
                        break;
                    case 11:
                        Intent intent9 = new Intent(getActivity(), VoiceAct.class);
                        startActivity(intent9);
                        MobclickAgent.onEvent(getActivity(),"voice");
                        break;
                    case 12:
                        Intent intent10 = new Intent(getActivity(), CeshiAct.class);
                        startActivity(intent10);
                        MobclickAgent.onEvent(getActivity(),"ceshi");
                        break;
                    case 13:
                        Intent intent11 = new Intent(getActivity(), SharedMenuAct.class);
                        startActivity(intent11);
                        MobclickAgent.onEvent(getActivity(),"sharemenu");
                        break;
                    case 14:
                        Intent intent12 = new Intent(getActivity(), ViewPagerAnimatorAct.class);
                        startActivity(intent12);
                        MobclickAgent.onEvent(getActivity(),"viewpagerAnim");
                        break;
                    case 15:
                        Intent intent13 = new Intent(getActivity(), SubwayAct.class);
                        startActivity(intent13);
                        MobclickAgent.onEvent(getActivity(),"subway");
                        break;
                    case 16:
                        Intent intent14 = new Intent(getActivity(), CeshiAnimAct.class);
                        startActivity(intent14);
                        MobclickAgent.onEvent(getActivity(),"CeshiAnimAct");
                        break;
                    case 17:
                        Intent intent15 = new Intent(getActivity(), SlindingAct.class);
                        startActivity(intent15);
                        MobclickAgent.onEvent(getActivity(),"SlindingAct");
                        break;
                    case 18:
                        Intent intent16 = new Intent(getActivity(), ZxingAct.class);
                        startActivity(intent16);
                        MobclickAgent.onEvent(getActivity(),"ZxingAct");
                        break;
                    case 19:
                        Intent intent17 = new Intent(getActivity(), AutoAct.class);
                        startActivity(intent17);
                        MobclickAgent.onEvent(getActivity(),"AutoAct");
                        break;
                    case 20:
                        Intent intent18 = new Intent(getActivity(), NewAct.class);
                        startActivity(intent18);
                        MobclickAgent.onEvent(getActivity(),"NewAct");
                        break;
                    case 21:
                        Intent intent19 = new Intent(getActivity(), LvCallBackAct.class);
                        startActivity(intent19);
                        MobclickAgent.onEvent(getActivity(),"LvCallBackAct");
                        break;
                    case 22:
                        Intent intent20 = new Intent(getActivity(), SortMainAct.class);
                        startActivity(intent20);
                        MobclickAgent.onEvent(getActivity(),"SortMainAct");
                        break;
                    case 23:
                        Intent intent21 = new Intent(getActivity(), SlidePanelMainAct.class);
                        startActivity(intent21);
                        MobclickAgent.onEvent(getActivity(),"SlidePanelMainAct");
                        break;
                    case 24:
                        Intent intent22 = new Intent(getActivity(), ExpendMainAct.class);
                        startActivity(intent22);
                        MobclickAgent.onEvent(getActivity(),"ExpendMainAct");
                        break;
                    case 25:
                        Intent intent23 = new Intent(getActivity(), PersonalMainAct.class);
                        startActivity(intent23);
                        MobclickAgent.onEvent(getActivity(),"PersonalMainAct");
                        break;
                    case 26:
                        Intent intent24 = new Intent(getActivity(), AutoListAct.class);
                        startActivity(intent24);
                        MobclickAgent.onEvent(getActivity(),"AutoListAct");
                        break;
                    case 27:
                        Intent intent25 = new Intent(getActivity(), WeatherAct.class);
                        startActivity(intent25);
                        MobclickAgent.onEvent(getActivity(),"WeatherAct");
                        break;
                    case 28:
                        Intent intent26 = new Intent(getActivity(), DialogFragmentAct.class);
                        startActivity(intent26);
                        MobclickAgent.onEvent(getActivity(),"");
                        break;
                }
            }
        });
    }

    /**
     * 自定义提示对话框
     */
    private void ClickDialog() {
        MyDialog.Builder builder = new MyDialog.Builder(getActivity());
        builder.setMessage("确定退出登录状态吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }
    /**
     * 自定义吐司
     */
    private void ClickToast() {
       MyActivity mActivity=(MyActivity)getActivity();
       MyToast.show("这是一个带图片的吐司",R.drawable.weather_bigsonw,mActivity);
    }
    /**
     * 自定义提示进度框
     */
    private void ClickProgressDialog() {
        MyProgrssDialog.createProgrssDialog(getActivity(),"正在加载中。。。",true).show();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("DemoFrag"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("DemoFrag");
    }

}
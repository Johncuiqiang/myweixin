package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.adapter.SubwayAdapter;
import com.yzh.weixin.bean.Type;

import java.util.ArrayList;

/**
 * Created by cuiqiang on 2016/5/23.
 */
public class SubwayAct extends Activity {

    private ArrayList<Type> sList = new ArrayList<>();
    private ListView lv;
    private Button bg1;
    private Button bg2;
    private Button bg3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_subway);
        initView();
    }
    private void loadData1() {
        sList.clear();
        Type type=new Type(1,R.drawable.add_busines_trip);
        sList.add(type);
        Type type1=new Type(0,R.drawable.add_vacation);
        sList.add(type1);
        Type type2=new Type(0,R.drawable.add_busines_trip);
        sList.add(type2);
        Type type3=new Type(1,R.drawable.add_vacation);
        sList.add(type3);
        SubwayAdapter subwayAdapter = new SubwayAdapter(this,sList);
        lv.setAdapter(subwayAdapter);
    }
    private void loadData2() {
        sList.clear();
        Type type=new Type(1,R.drawable.add);
        sList.add(type);
        Type type1=new Type(0,R.drawable.add_vacation);
        sList.add(type1);
        Type type2=new Type(0,R.drawable.add_rest);
        sList.add(type2);
        Type type3=new Type(1,R.drawable.add_vacation);
        sList.add(type3);
        SubwayAdapter subwayAdapter = new SubwayAdapter(this,sList);
        lv.setAdapter(subwayAdapter);
    }
    private void loadData3() {
        sList.clear();
        Type type=new Type(1,R.drawable.add_contact);
        sList.add(type);
        Type type1=new Type(0,R.drawable.add_rest);
        sList.add(type1);
        Type type2=new Type(0,R.drawable.add_busines_trip);
        sList.add(type2);
        Type type3=new Type(1,R.drawable.add_vacation);
        sList.add(type3);
        SubwayAdapter subwayAdapter = new SubwayAdapter(this,sList);
        lv.setAdapter(subwayAdapter);
    }
    public void  initView(){
        lv =(ListView)findViewById(R.id.lv);
        bg1 = (Button) findViewById(R.id.bg1);
        bg2 = (Button) findViewById(R.id.bg2);
        bg3 = (Button) findViewById(R.id.bg3);
        bg1.setOnClickListener(turn);
        bg2.setOnClickListener(time);
        bg3.setOnClickListener(cheap);
        bg1.performClick();
    }
    private View.OnClickListener turn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            bg1.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_corners_bg_left));
            bg2.getBackground().setAlpha(0);
            bg3.getBackground().setAlpha(0);
            bg2.setBackgroundColor(Color.parseColor("#ffffff"));
            bg1.setTextColor(Color.parseColor("#ffffff"));
            bg2.setTextColor(Color.parseColor("#000000"));
            bg3.setTextColor(Color.parseColor("#000000"));
            loadData1();
        }
    };
    private View.OnClickListener time = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            bg2.setBackgroundColor(Color.parseColor("#bcbcbc"));
            bg1.setTextColor(Color.parseColor("#000000"));
            bg2.setTextColor(Color.parseColor("#ffffff"));
            bg3.setTextColor(Color.parseColor("#000000"));
            bg1.getBackground().setAlpha(0);
            bg3.getBackground().setAlpha(0);
            loadData2();
        }
    };

    private View.OnClickListener cheap = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            bg1.getBackground().setAlpha(0);
            bg2.getBackground().setAlpha(0);
            bg2.setBackgroundColor(Color.parseColor("#ffffff"));
            bg3.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_corners_bg_right));
            bg1.setTextColor(Color.parseColor("#000000"));
            bg2.setTextColor(Color.parseColor("#000000"));
            bg3.setTextColor(Color.parseColor("#ffffff"));
            loadData3();
        }
    };
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SubWayAct");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SubWayAct");
        MobclickAgent.onPause(this);
    }
}

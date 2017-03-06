package com.yzh.weixin.gaode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.yzh.weixin.R;
import com.yzh.weixin.adapter.GaodeMainAdapter;
import com.yzh.weixin.autolayout.AutoLayoutActivity;

/**
 * Created by cuiqiang on 2016/6/29.
 */
public class GaodeMainAct extends AutoLayoutActivity {

    private ListView lvMain;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_gaode_main);
        initView();
        initData();
    }

    public void initView() {
        lvMain = (ListView)findViewById(R.id.lv_main);
        GaodeMainAdapter gaodeMainAdapter = new GaodeMainAdapter(this);
        lvMain.setAdapter(gaodeMainAdapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(GaodeMainAct.this, LocationAct.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //精准定位，改变可视化区域，离目的地更近，提升用户体验
                        Intent intent1 = new Intent(GaodeMainAct.this, LocationMoreAct.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(GaodeMainAct.this, RouteActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                }
            }
        });
    }

    public void initData() {

    }
}

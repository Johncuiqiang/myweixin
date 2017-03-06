package com.yzh.weixin.samplewater;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;

public class WaterMainAct extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SGV Sample");
        setContentView(R.layout.act_main_water);

        findViewById(R.id.btn_sgv).setOnClickListener(this);
        findViewById(R.id.btn_sgv_fragment).setOnClickListener(this);
        findViewById(R.id.btn_sgv_empty_view).setOnClickListener(this);
        findViewById(R.id.btn_listview).setOnClickListener(this);
    }


    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.btn_sgv) {
            startActivity(new Intent(this, StaggeredGridActivity.class));
        }
        else if (v.getId() == R.id.btn_sgv_fragment) {
            startActivity(new Intent(this, StaggeredGridActivityFragment.class));
        }
        else if (v.getId() == R.id.btn_sgv_empty_view) {
            startActivity(new Intent(this, StaggeredGridEmptyViewActivity.class));
        }
        else if (v.getId() == R.id.btn_listview) {
            startActivity(new Intent(this, ListViewActivity.class));
        }
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

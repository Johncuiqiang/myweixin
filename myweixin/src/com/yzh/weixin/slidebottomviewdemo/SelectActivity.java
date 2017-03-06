package com.yzh.weixin.slidebottomviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.yzh.weixin.R;

public class SelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_slide_select);

        findViewById(R.id.tv_zhihu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, SlidePanelMainAct.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_listview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.tv_scrollview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, ScrollViewActivity.class);
                startActivity(intent);
            }
        });
    }

}

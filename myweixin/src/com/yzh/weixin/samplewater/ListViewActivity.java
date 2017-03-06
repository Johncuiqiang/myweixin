package com.yzh.weixin.samplewater;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;

import java.util.List;

public class ListViewActivity extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_view_water);

        setTitle("ListView");

        final ListView listView = (ListView) findViewById(R.id.list_view);

        LayoutInflater layoutInflater = getLayoutInflater();

        View header = layoutInflater.inflate(R.layout.item_header_footer_water, null);
        View footer = layoutInflater.inflate(R.layout.item_header_footer_water, null);
        TextView txtHeaderTitle = (TextView) header.findViewById(R.id.txt_title);
        TextView txtFooterTitle =  (TextView) footer.findViewById(R.id.txt_title);
        txtHeaderTitle.setText("THE HEADER!");
        txtFooterTitle.setText("THE FOOTER!");

        listView.addHeaderView(header);
        listView.addFooterView(footer);

        final SampleAdapter adapter = new SampleAdapter(this, R.id.txt_line1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        final List<String> sampleData = SampleData.generateSampleData();
        for (String data : sampleData) {
            adapter.add(data);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
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

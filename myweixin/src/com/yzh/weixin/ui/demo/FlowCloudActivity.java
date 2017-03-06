package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.flowcloud.TagBaseAdapter;
import com.yzh.weixin.flowcloud.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiqiang on 2016/5/17.
 */
public class FlowCloudActivity  extends Activity {

    private TagCloudLayout mContainer;
    private TagBaseAdapter mAdapter;
    private List<Beanimg> mList;
    private EditText etAdd;
    private Beanimg bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flow_cloud);
        mContainer = (TagCloudLayout) findViewById(R.id.container);
        etAdd = (EditText) findViewById(R.id.add_et);
        mList = new ArrayList<>();
        bean=new Beanimg();
        bean.texts="feili";
        bean.img=R.drawable.tag_view;
        mList.add(bean);
        mAdapter = new TagBaseAdapter(this,mList);
        mContainer.setAdapter(mAdapter);

        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg();
            }
        });

       /* mContainer.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                Toast.makeText(FlowCloudActivity.this,mList.get(position),Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    public void selectImg(){
        String text = etAdd.getText().toString().toLowerCase().trim();
        Beanimg bean1=new Beanimg();
        bean1.texts=text;
        int x = new Long(Math.round(Math.random() * 3 + 1)).intValue();
        if(x==1){
            bean1.img=R.drawable.shape_cloud2;
        }else if(x==2){
            bean1.img=R.drawable.shape_cloud;
        }else if(x==3){
            bean1.img=R.drawable.shape_cloud3;
        }else{
            bean1.img=R.drawable.tag_view;
        }
        mList.add(bean1);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public class Beanimg {
        public int img;
        public String texts;
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("FlowAct");
        MobclickAgent.onResume(this);       //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("FlowAct");
        MobclickAgent.onPause(this);
    }
}

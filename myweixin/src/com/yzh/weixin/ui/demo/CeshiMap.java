package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.agent.HobbySearch;
import com.yzh.weixin.agent.PathSearch;


/**
 * Created by cuiqiang on 2016/5/11.
 */
public class CeshiMap extends Activity {

    MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private Context mcontext=this;
    private PathSearch mPathSearch;
    private HobbySearch mHobbySearch;
    private EditText etSearch;
    private EditText etCity;
    private EditText etStart;
    private EditText etEnd;
    private Button btSearch;
    private Button btRoute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.act_ceshi_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
       //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //开启交通图
        mBaiduMap.setTrafficEnabled(true);
       // mBaiduMap.setOnMapDrawFrameCallback(callback);
        etSearch = (EditText) findViewById(R.id.et_search);
        etCity = (EditText) findViewById(R.id.et_city);
        etStart = (EditText) findViewById(R.id.et_start);
        etEnd = (EditText) findViewById(R.id.et_end);
        btSearch = (Button)findViewById(R.id.bt_search);
        btRoute = (Button)findViewById(R.id.bt_route);
        btSearch.setOnClickListener(search);
        btRoute.setOnClickListener(route);
        initData();
    }
    private  View.OnClickListener search= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = etSearch.getText().toString().toLowerCase().trim();
            mHobbySearch = new HobbySearch(mcontext);
            mHobbySearch.setmBaiduMap(mBaiduMap);
            mHobbySearch.search(name);


        }
    };
    private  View.OnClickListener route= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String  city = etCity.getText().toString().toLowerCase().trim();
            String  text1 = etStart.getText().toString().toLowerCase().trim();
            String  text2 = etEnd.getText().toString().toLowerCase().trim();

            mPathSearch = new PathSearch(mcontext);
            mPathSearch.setmBaiduMap(mBaiduMap);
            mPathSearch.search(text1,text2,city);

        }
    };

    private void initData() {

        double latitude = 40.050966; //纬度
        double longitude = 116.303128; //经度
        //定义Maker坐标点
        LatLng point = new LatLng(latitude, longitude);
       //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.add_vacation);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        MobclickAgent.onPageStart("CeMap");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
        MobclickAgent.onPageEnd("CeMap");
        MobclickAgent.onPause(this);
    }

}


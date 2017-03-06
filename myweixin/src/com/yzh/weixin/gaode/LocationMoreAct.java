package com.yzh.weixin.gaode;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.*;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.yzh.weixin.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by cuiqiang on 2016/5/23.
 */
public class LocationMoreAct extends Activity implements View.OnClickListener, AMap.CancelableCallback {

    MapView mMapView = null;
    private AMap aMap;

    private UiSettings mUiSettings;
    private static final int SCROLL_BY_PX = 100;
    private Boolean flag = false;
    private Button zoomIn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_gaode);
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            mUiSettings = aMap.getUiSettings();

        }
        mUiSettings.setZoomControlsEnabled(false);
        Button stopAnimation = (Button) findViewById(R.id.stop_animation);
        stopAnimation.setOnClickListener(this);
        ToggleButton animate = (ToggleButton) findViewById(R.id.animate);
        animate.setOnClickListener(this);
        zoomIn = (Button) findViewById(R.id.zoom_in);
        zoomIn.setOnClickListener(this);
        //zoomIn.performClick();
        Button zoomOut = (Button) findViewById(R.id.zoom_out);
        zoomOut.setOnClickListener(this);
        Button Zhongguancun = (Button) findViewById(R.id.Zhongguancun);
        Zhongguancun.setOnClickListener(this);
        Zhongguancun.performClick();
    }



    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }




    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private void changeCamera(CameraUpdate update, AMap.CancelableCallback callback) {
        boolean animated = ((CompoundButton) findViewById(R.id.animate))
                .isChecked();
        if (animated) {
            /*if(flag){
                aMap.animateCamera(update, 2000, callback);
                flag=false;
            }else{*/
                aMap.animateCamera(update, 1000, callback);
            //}
        } else {
            aMap.moveCamera(update);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 点击停止动画按钮响应事件
             */
            case R.id.stop_animation:
                aMap.stopAnimation();
                break;
            /**
             * 点击地图放大按钮响应事件
             */
            case R.id.zoom_in:
                changeCamera(CameraUpdateFactory.zoomIn(), null);
                break;
            /**
             * 点击地图缩小按钮响应事件
             */
            case R.id.zoom_out:
                changeCamera(CameraUpdateFactory.zoomOut(), null);
                break;
            /**
             * 点击“去中关村”按钮响应事件
             */
            case R.id.Zhongguancun:
                changeCamera(
                        CameraUpdateFactory.newCameraPosition(new CameraPosition(
                                Constants.ZHONGGUANCUN, 18, 0, 30)), null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onCancel() {

    }


}

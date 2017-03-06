package com.yzh.weixin.agent;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.*;
import com.yzh.weixin.R;
import com.yzh.weixin.overlayutil.DrivingRouteOverlay;
import com.yzh.weixin.utils.ContextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiqiang on 2016/5/11.
 */
public class PathSearch {

    private BaiduMap mBaiduMap;
    private Context context;

    public PathSearch(Context context) {
        this.context =context;
    }

  //调用方法
    public void setmBaiduMap(BaiduMap mBaiduMap) {
        this.mBaiduMap = mBaiduMap;
    }

    public BaiduMap getmBaiduMap() {
        return mBaiduMap;
    }
    //调用方法
    public void search(String text1,String text2,String city) {
        //第一：   获取RoutePlanSearch对象
        RoutePlanSearch planSearch = RoutePlanSearch.newInstance();
        //第二：  注册返回结果的监听 MyOnGetRoutePlanResultListener
        planSearch.setOnGetRoutePlanResultListener( new MyOnGetRoutePlanResultListener());

       /* DrivingRoutePlanOption option = new DrivingRoutePlanOption();
        option.policy(DrivingRoutePlanOption.DrivingPolicy. ECAR_TIME_FIRST); // 设置策略*/

        PlanNode stNode = PlanNode.withCityNameAndPlaceName(city,text1);
        PlanNode enNode = PlanNode.withCityNameAndPlaceName(city,text2);

        //   PlanNode from = PlanNode. withLocation(new LatLng(40.065796,86.349868));
        //      option.from(stNode); // 起点
        //   PlanNode to = PlanNode. withLocation(new LatLng(40.065796,116.349868));
        //      option.to(enNode); // 终点
        planSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode)
                .to(enNode));
        //    List<PlanNode> list = new ArrayList<PlanNode>();
        //     list.add(PlanNode. withCityNameAndPlaceName("北京", "天安门" ));
        //     option.passBy(list);    // 设置途经点
        // 第三 发起搜索请求   planSearch.drivingSearch(option);
        //  planSearch.drivingSearch(option);
        //  planSearch.destory();
    }

    private class MyOnGetRoutePlanResultListener implements OnGetRoutePlanResultListener {
        // 搜索返回的驾车结果
        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            // 搜索结果返回
            if (result == null
                    || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND ) {// 没有查询到结果
                Toast. makeText(context, "没有查询到结果" , Toast.LENGTH_SHORT).show();
                return;
            }
            // 创建行车路线覆盖物
            DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
            // 把点击事件传递下来
            mBaiduMap.setOnMarkerClickListener(overlay); // 传递点击事件
            overlay.setData(result.getRouteLines().get(0)); // 展示第1条行车路线
            overlay.addToMap(); // 添加行车路线覆盖物到地图
            overlay.zoomToSpan();  // 缩放地图以展示全部路线
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }

        // 搜索返回的公交换乘结果
        @Override
        public void onGetTransitRouteResult(TransitRouteResult arg0) {
        }
        // 搜索返回的步行结果
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult arg0) {
        }
    }
    class MyDrivingRouteOverlay extends DrivingRouteOverlay{
        public MyDrivingRouteOverlay(BaiduMap arg0) {
            super(arg0);
        }
        @Override
        public BitmapDescriptor getStartMarker() { //起点图标
            return BitmapDescriptorFactory.fromResource(R.drawable.add_busines_trip);
        }
        @Override
        public BitmapDescriptor getTerminalMarker() { // 终点图标
            return BitmapDescriptorFactory.fromResource(R.drawable.add_rest);
        }
    }
}
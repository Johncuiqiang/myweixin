package com.yzh.weixin.agent;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.*;
import com.yzh.weixin.overlayutil.PoiOverlay;
import com.yzh.weixin.utils.ContextUtil;

/**
 * Created by cuiqiang on 2016/5/11.
 */
public class HobbySearch {

    private BaiduMap mBaiduMap;
    private Context context;

    public HobbySearch(Context context) {
        this.context=context;
    }

    //调用方法
    public void setmBaiduMap(BaiduMap mBaiduMap) {
        this.mBaiduMap = mBaiduMap;
    }

    public BaiduMap getmBaiduMap() {
        return mBaiduMap;
    }

    //调用方法
    public void search(String text) {
        //第一： 获取PoiSearch对象
        PoiSearch poiSearch = PoiSearch.newInstance();
        //第二：  注册返回结果的监听
        poiSearch.setOnGetPoiSearchResultListener(new MyOnGetPoiSearchResultListener());

        PoiBoundSearchOption option = new PoiBoundSearchOption();
        option.keyword(text);// 设置关键字
        //矩形范围搜索   新建一个矩形
        LatLngBounds bounds = new LatLngBounds.Builder()//LatLngBounds 地理范围构造器
                .include(new LatLng(40.049233, 116.302675))
                .include(new LatLng(40.050645, 116.303695))
                .build();

        option.bound(bounds); // 设置搜索的矩形范围
        //第三：  发起搜索请求
        poiSearch.searchInBound(option);


    }

    class MyOnGetPoiSearchResultListener implements OnGetPoiSearchResultListener {
        @Override
        public void onGetPoiDetailResult(PoiDetailResult result) {
        }

        @Override
        public void onGetPoiResult(PoiResult result) {
            // 搜索结果返回 // 没有查询到结果
            if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                Toast.makeText(context, "没有查询到结果", Toast.LENGTH_SHORT).show();
                return;
            }
            //搜索回来的结果 就是 一个覆盖物
            // 创建覆盖物
            PoiOverlay poiOverlay = new PoiOverlay(mBaiduMap);
            // 设置数据
            poiOverlay.setData(result); // 设置PoiOverlay数据

            // 缩放地图，使所有Overlay都在合适的视野内
            // 注：该方法只对Marker类型的overlay有效
            poiOverlay.addToMap(); // 添加PoiOverlay到地图中
            poiOverlay.zoomToSpan(); // 缩放地图，使所有Overlay都在合适的视野内     注： 该方法只对Marker类型的overlay有效
        }

    }
}

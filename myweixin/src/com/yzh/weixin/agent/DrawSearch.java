package com.yzh.weixin.agent;

import android.graphics.Color;
import android.graphics.PointF;
import android.widget.Button;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.yzh.weixin.overlayutil.OverlayManager;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiqiang on 2016/5/11.
 */
public class DrawSearch {
    Button mBtnPre = null; // 上一个节点
    Button mBtnNext = null;// 下一个节点
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;
    int nodeIndex = -1;// 节点索引,供浏览节点时使用

    // 调用方法
    private BaiduMap mBaiduMap;
    private LatLng latlng1 = new LatLng(39.97923, 116.357428);
    private LatLng latlng2 = new LatLng(79.96923, 70.437428);

    private List<LatLng> latLngPolygon;
    {
        latLngPolygon = new ArrayList<LatLng>();
        latLngPolygon.add(latlng1);
        latLngPolygon.add(latlng2);
    }
    RouteLine route = null;
    private float[] vertexs;
    private FloatBuffer vertexBuffer;
    // 定义地图绘制每一帧时 OpenGL 绘制的回调接口
    BaiduMap.OnMapDrawFrameCallback callback = new BaiduMap.OnMapDrawFrameCallback() {
        public void onMapDrawFrame(GL10 gl, MapStatus drawingMapStatus) 	{
            if (mBaiduMap.getProjection() != null) {
                // 计算折线的 opengl 坐标
                calPolylinePoint(drawingMapStatus);
                // 绘制折线
                drawPolyline(gl, Color.argb(255, 255, 0, 0), vertexBuffer, 10, 3, drawingMapStatus);
            }
        }
    };


    // 计算折线 OpenGL 坐标
    public void calPolylinePoint(MapStatus mspStatus) {
        PointF[] polyPoints = new PointF[latLngPolygon.size()];
        vertexs = new float[3 * latLngPolygon.size()];
        int i = 0;
        for (LatLng xy : latLngPolygon) {
            // 将地理坐标转换成 openGL 坐标
            polyPoints[i] = mBaiduMap.getProjection().toOpenGLLocation(xy, mspStatus);
            vertexs[i * 3] = polyPoints[i].x;
            vertexs[i * 3 + 1] = polyPoints[i].y;
            vertexs[i * 3 + 2] = 0.0f;
            i++;
        }
        for (int j = 0; j < vertexs.length; j++) {
            // Log.d(, "vertexs[" + j + "]: " + vertexs[j]);
        }
        vertexBuffer = makeFloatBuffer(vertexs);
    }

    //创建OpenGL绘制时的顶点Buffer
    private FloatBuffer makeFloatBuffer(float[] fs) {
        ByteBuffer bb = ByteBuffer.allocateDirect(fs.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(fs);
        fb.position(0);
        return fb;
    }

    // 绘制折线
    private void drawPolyline(GL10 gl, int color, FloatBuffer lineVertexBuffer, float lineWidth, int pointSize, MapStatus drawingMapStatus) {

        gl.glEnable(GL10.GL_BLEND);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        float colorA = Color.alpha(color) / 255f;
        float colorR = Color.red(color) / 255f;
        float colorG = Color.green(color) / 255f;
        float colorB = Color.blue(color) / 255f;

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, lineVertexBuffer);
        gl.glColor4f(colorR, colorG, colorB, colorA);
        gl.glLineWidth(lineWidth);
        gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, pointSize);

        gl.glDisable(GL10.GL_BLEND);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}

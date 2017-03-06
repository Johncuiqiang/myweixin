package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.*;
import android.widget.Button;
import com.yzh.weixin.constant.Constants;
import com.yzh.weixin.R;
import com.yzh.weixin.common.MyDialog;
import com.yzh.weixin.utils.PrefUtils;
import com.yzh.weixin.video.VideoSurfaceDemo;

import java.io.File;

/**
 * Created by cuiqiang on 2016/6/24.
 */
public class VideoAct extends Activity {

    private Button start;// 开始录制按钮
    private Button stop;// 停止录制按钮
    private MediaRecorder mRecorder;;// 录制视频的类
    private SurfaceView surfaceview;// 显示视频的控件
    // 用来显示视频的一个接口，我靠不用还不行，也就是说用mediarecorder录制视频还得给个界面看
    // 想偷偷录视频的同学可以考虑别的办法。。嗯需要实现这个接口的Callback接口
    // 系统的视频文件
    private File videoFile ;
    // 记录是否正在进行录制
    private boolean isRecording = false;

    private SurfaceHolder surfaceHolder;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        // 设置横屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 选择支持半透明模式,在有surfaceview的activity中使用。
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.act_video);
        init();
    }

    private void init() {
        start = (Button) this.findViewById(R.id.start);
        stop = (Button) this.findViewById(R.id.stop);
        start.setOnClickListener(new TestVideoListener());
        stop.setOnClickListener(new TestVideoListener());
        surfaceview = (SurfaceView) this.findViewById(R.id.surfaceview);
        // 设置Surface不需要自己维护缓冲区
        surfaceview.getHolder().setType(SurfaceHolder
                .SURFACE_TYPE_PUSH_BUFFERS);
        // 设置分辨率
        surfaceview.getHolder().setFixedSize(320, 280);
        // 设置该组件让屏幕不会自动关闭
        surfaceview.getHolder().setKeepScreenOn(true);
    }

    class TestVideoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v == start) {
                try
                {

                    // 创建保存录制视频的视频文件
                    videoFile = new File(Environment
                            .getExternalStorageDirectory()
                            .getCanonicalFile() + "/myvideo.mp4");
                    // 创建MediaPlayer对象
                    mRecorder = new MediaRecorder();
                    mRecorder.reset();
                    // 设置从麦克风采集声音
                    mRecorder.setAudioSource(MediaRecorder
                            .AudioSource.MIC);
                    // 设置从摄像头采集图像
                    mRecorder.setVideoSource(MediaRecorder
                            .VideoSource.CAMERA);
                    // 设置视频文件的输出格式
                    // 必须在设置声音编码格式、图像编码格式之前设置
                    mRecorder.setOutputFormat(MediaRecorder
                            .OutputFormat.MPEG_4);
                    // 设置声音编码的格式
                    mRecorder.setAudioEncoder(MediaRecorder
                            .AudioEncoder.DEFAULT);
                    // 设置图像编码的格式
                    mRecorder.setVideoEncoder(MediaRecorder
                            .VideoEncoder.MPEG_4_SP);
                    mRecorder.setVideoSize(320, 280);
                    // 每秒 4帧
                    mRecorder.setVideoFrameRate(4);
                    mRecorder.setOutputFile(videoFile.getAbsolutePath());
                    PrefUtils.getString(VideoAct.this, Constants.VIDEO_PATH,videoFile.getAbsolutePath());
                    // 指定使用SurfaceView来预览视频
                    mRecorder.setPreviewDisplay(surfaceview
                            .getHolder().getSurface());  //①
                    mRecorder.prepare();
                    // 开始录制
                    mRecorder.start();
                    System.out.println("---recording---");
                    // 让record按钮不可用。
                    start.setEnabled(false);
                    // 让stop按钮可用。
                    stop.setEnabled(true);
                    isRecording = true;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (v == stop) {
                // 如果正在进行录制
                if (isRecording)
                {
                    // 停止录制
                    mRecorder.stop();
                    // 释放资源
                    mRecorder.release();
                    mRecorder = null;
                    // 让record按钮可用。
                    start.setEnabled(true);
                    // 让stop按钮不可用。
                    stop.setEnabled(false);
                    ClickDialog();
                }

            }

        }

    }
    /**
     * 自定义提示对话框
     */
    private void ClickDialog() {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        builder.setMessage("是否保存视频？");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(VideoAct.this, VideoSurfaceDemo.class);
                startActivity(intent);
                dialog.dismiss();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }

}

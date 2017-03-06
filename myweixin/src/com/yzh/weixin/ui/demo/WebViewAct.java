package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import com.yzh.weixin.common.MyProgrssDialog;

/**
 * Created by cuiqiang on 2016/5/17.
 */
public class WebViewAct extends Activity {
    private Context context=this;
    private WebView myWebView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://www.baidu.com");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        // 解决webview的适配屏幕问题
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //本地文件
        //myWebView.loadUrl(“file:///android_asset/XX.html“);
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                System.out.println("开始加载...");
                MyProgrssDialog.createProgrssDialog(context,"",false).show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //链接跳转
                System.out.println("url:" + url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("加载结束...");
                MyProgrssDialog.hideProgressDialog();
            }
        });


    }
    /**
     * 按键响应，在WebView中查看网页时，按返回键的时候按浏览历史退回,如果不做此项处理则整个WebView返回退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack())
        {
            // 返回键退回
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
    //目前双击事件暂无bug
   /*可是加上上述代码就会出现一个问题，如果你的webview里面不是纯图片的话，双击webview
    放大后的webview再双击缩小，缩小后的界面会将文字全部展示在左侧，右侧有一部分空白，
    找了半天也没有找到解决方法，最后只有把双击事件禁止掉了，last_time未定义*/
   /* public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                long current_time = System.currentTimeMillis();
                long d_time = current_time - last_time;
                if (d_time < 300) {
                    last_time = current_time;
                    return true;
                } else {
                    last_time = current_time;
                }
                break;
        }
        return super.onTouchEvent(event);
    }*/

}

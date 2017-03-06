package com.yzh.weixin.slidebottomviewdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import com.yzh.weixin.R;
import com.yzh.weixin.slidebottompanel.SlideBottomPanel;


public class SlidePanelMainAct extends Activity {

    private String webUrl = "http://www.zhihu.com/question/29416073/answer/44340933";
    private WebView webView;
    private TextView tvEdit;
    private SlideBottomPanel sbv;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_slide_panel_main);

        sbv = (SlideBottomPanel) findViewById(R.id.sbv);
        tvEdit = (TextView) findViewById(R.id.tv_edit);
        webView = (WebView) findViewById(R.id.wv_main);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(webUrl);
        webView.setWebChromeClient(new WebChromeClient());
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SlidePanelMainAct.this, InputActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (sbv.isPanelShowing()) {
            sbv.hide();
            return;
        }
        super.onBackPressed();
    }
}

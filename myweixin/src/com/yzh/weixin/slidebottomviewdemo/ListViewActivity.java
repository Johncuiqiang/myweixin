package com.yzh.weixin.slidebottomviewdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.yzh.weixin.R;
import com.yzh.weixin.slidebottompanel.SlideBottomPanel;


import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private String webUrl = "http://www.zhihu.com/question/29416073/answer/44340933";
    private WebView webView;
    private ListView listView;
    private ArrayList<String> list = new ArrayList<>();
    private SlideBottomPanel sbv;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_slide_panel_list);

        sbv = (SlideBottomPanel) findViewById(R.id.sbv);
        webView = (WebView) findViewById(R.id.wv_main);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(webUrl);
        webView.setWebChromeClient(new WebChromeClient());

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_slide, getData()));
    }

    private ArrayList<String> getData()
    {
        for (int i = 0; i < 20; i++) {
            list.add("Item " + i);
        }
        return list;
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

package com.yzh.weixin.ui.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeng.analytics.MobclickAgent;
import com.yzh.weixin.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cuiqiang on 2016/4/29.
 */
public class WeatherAct extends Activity {

    private TextView tvCurtemp;
    private TextView tvMoisture;
    private TextView tvWeather;
    private RequestQueue mRequestQueue;
    private String url;
    private TextView tvCurtemp1;
    private TextView tvMoisture1;
    private TextView tvWeather1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_weather);
        tvCurtemp = (TextView) findViewById(R.id.tv_temp);
        tvMoisture = (TextView) findViewById(R.id.tv_moisture);
        tvWeather = (TextView) findViewById(R.id.tv_weather);
        tvCurtemp1 = (TextView) findViewById(R.id.tv_temp1);
        tvMoisture1 = (TextView) findViewById(R.id.tv_moisture1);
        tvWeather1 = (TextView) findViewById(R.id.tv_weather1);
        doNet();
        parseGosn();
    }

    /*
       Volley提供了JsonObjectRequest,JsonArrayRequest,StringRequest等Request形式.
       JsonObjectReques,t返回JSON对象.
       JsonArrayRequest,返回JsonArray.
       StringRequest,返回String这样可以自己处理数据更加灵活.
       另外可以继承Request<T>自定义Request.
    */
    public void doNet() {
        mRequestQueue = Volley.newRequestQueue(this);
        url = "http://op.juhe.cn/onebox/weather/query?cityname=%E5%8C%97%E4%BA%AC&key=520f029c392682d4229df1f78856cd95";
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(jr);
    }


    public void parseJSON(JSONObject response) {
        try {
            JSONObject jsonObj = response.getJSONObject("result").getJSONObject("data")
                    .getJSONObject("realtime").getJSONObject("weather");
            String curtemp = jsonObj.getString("temperature");
            String moisture = jsonObj.getString("humidity");
            String weather = jsonObj.getString("info");
            tvCurtemp.setText("高温"+"  "+curtemp);
            tvMoisture.setText("湿度"+"  "+moisture);
            tvWeather.setText("天气"+"  "+weather);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /*后台如果用gosn直接生成的字符串,获取后台数据直接解析较好*/
    public void parseGosn() {
           String jsonData = "{\"temperature\":\"22\",\"humidity\":\"30\",\"info\":\"晴\"}";
            Gson gson = new Gson();
            Weather weather = gson.fromJson(jsonData, Weather.class);

           tvCurtemp1.setText("高温Gosn"+"  "+weather.getHumidity());
           tvMoisture1.setText("湿度Gosn"+"  "+weather.getTemperature());
           tvWeather1.setText("天气Gosn"+"  "+weather.getInfo());

    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);       //统计时长
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    public class Weather {
        private String temperature;
        private String humidity;
        private String info;

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
    }
}

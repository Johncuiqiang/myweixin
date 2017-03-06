package com.yzh.weixin.utils;

import android.widget.ImageView;
import android.widget.TextView;
import com.yzh.weixin.R;


/**
 * Created by cuiqiang on 2016/4/14.
 */
public class WeatherSelectUtil {

    public static void select(String day, ImageView iv) {
        if (day.equals("小雨")) {
            iv.setImageResource(R.drawable.weather_smallrain);
        } else if (day.equals("中雨")) {
            iv.setImageResource(R.drawable.weather_rain);
        } else if (day.equals("大雨")) {
            iv.setImageResource(R.drawable.weather_bigrain);
        } else if (day.equals("小雪")) {
            iv.setImageResource(R.drawable.weather_smallsnow);
        } else if (day.equals("中雪")) {
            iv.setImageResource(R.drawable.weather_snow);
        } else if (day.equals("大雪")) {
            iv.setImageResource(R.drawable.weather_bigsonw);
        } else if (day.equals("雨夹雪")) {
            iv.setImageResource(R.drawable.weather_rainandsnow);
        } else if (day.equals("多云")) {
            iv.setImageResource(R.drawable.weather_cloud);
        } else if (day.equals("阴")) {
            iv.setImageResource(R.drawable.weather_yin);
        } else if (day.equals("雾霾")) {
            iv.setImageResource(R.drawable.weather_haze);
        } else if (day.equals("暴雪")) {
            iv.setImageResource(R.drawable.weather_heavysnow);
        } else if (day.equals("暴雨")) {
            iv.setImageResource(R.drawable.weather_heavyrain);
        } else {
            iv.setImageResource(R.drawable.weather_sun);
        }
    }

    public static void selectBlack(String day, ImageView iv) {
        if (day.equals("小雨")) {
            iv.setImageResource(R.drawable.weather_smallrain);
        } else if (day.equals("中雨")) {
            iv.setImageResource(R.drawable.rain_black);
        } else if (day.equals("大雨")) {
            iv.setImageResource(R.drawable.bigrain_black);
        } else if (day.equals("小雪")) {
            iv.setImageResource(R.drawable.weather_smallsnow);
        } else if (day.equals("中雪")) {
            iv.setImageResource(R.drawable.snow_black);
        } else if (day.equals("大雪")) {
            iv.setImageResource(R.drawable.bigsnow_black);
        } else if (day.equals("雨夹雪")) {
            iv.setImageResource(R.drawable.snow_rain_black);
        } else if (day.equals("多云")) {
            iv.setImageResource(R.drawable.cloud_black);
        } else if (day.equals("阴")) {
            iv.setImageResource(R.drawable.yin_black);
        } else if (day.equals("雾霾")) {
            iv.setImageResource(R.drawable.wumai_black);
        } else if (day.equals("暴雪")) {
            iv.setImageResource(R.drawable.heavy_snow_black);
        } else if (day.equals("暴雨")) {
            iv.setImageResource(R.drawable.heavy_rain_black);
        } else {
            iv.setImageResource(R.drawable.sunny_black);
        }
    }

    public static void setDirection(String temp, TextView tv) {
        int i = Integer.parseInt(temp);
        if (i < 0) {
            tv.setText("↓");
        } else {
            tv.setText("↑");
        }
    }
}

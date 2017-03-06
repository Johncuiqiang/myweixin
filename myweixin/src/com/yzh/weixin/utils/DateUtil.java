package com.yzh.weixin.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cuiqiang on 2016/4/11.
 */
public class DateUtil {

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return  sdf.format(new Date());
    }
    public static String getCurrentTimeWx() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd");
        return  sdf.format(new Date());
    }
    private static Calendar mCalendar = Calendar.getInstance();

    public static int getSecond() {
        int second = mCalendar.get(Calendar.SECOND);
        return second;
    }
    public static int getMinuter() {
        int minute = mCalendar.get(Calendar.MINUTE);
        return minute;
    }

    public static int getHour() {
        int hour = mCalendar.get(Calendar.HOUR);
        return hour;
    }
    public static int getApm() {
        int apm = mCalendar.get(Calendar.AM_PM);
        return apm;
    }
    public static int getYear() {
        int year = mCalendar.get(Calendar.YEAR);
        return year;
    }
    public static int getMouth() {
        int  month = mCalendar.get(Calendar.MONTH)+1;
        return month;
    }
    public static int getDay() {
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }
    public static String getWeek() {
        int week = mCalendar.get(Calendar.DAY_OF_WEEK);
        if (Calendar.MONDAY == mCalendar.get(Calendar.DAY_OF_WEEK)) {
            return "星期一";
        }
        if (Calendar.TUESDAY == mCalendar.get(Calendar.DAY_OF_WEEK)) {
            return "星期二";
        }
        if (Calendar.WEDNESDAY == mCalendar.get(Calendar.DAY_OF_WEEK)) {
            return "星期三";
        }
        if (Calendar.THURSDAY == mCalendar.get(Calendar.DAY_OF_WEEK)) {
            return "星期四";
        }
        if (Calendar.FRIDAY == mCalendar.get(Calendar.DAY_OF_WEEK)) {
            return "星期五";
        }
        if (Calendar.SATURDAY == mCalendar.get(Calendar.DAY_OF_WEEK)) {
            return "星期六";
        }
        if (Calendar.SUNDAY == mCalendar.get(Calendar.DAY_OF_WEEK)) {
            return "星期日";
        }
        return "";
    }
}

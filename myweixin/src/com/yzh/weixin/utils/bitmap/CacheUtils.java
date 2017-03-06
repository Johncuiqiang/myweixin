package com.yzh.weixin.utils.bitmap;

import android.content.Context;
import com.yzh.weixin.utils.PrefUtils;

/**
 * 网络缓存工具类
 *
 * @author Kevin
 * @date 2016-1-28
 */
public class CacheUtils {

    /**
     * 写缓存
     * 以url为key, 以json为value保存起来
     */
    public static void setCache(String url, String json, Context ctx) {
        //sdcard, db, sharePreference
        //sdcard: 以url为文件名, 以json为文件内容存起来
        PrefUtils.putString(ctx, url, json);
    }

    /**
     * 读缓存
     */
    public static String getCache(String url, Context ctx) {
        return PrefUtils.getString(ctx, url, null);
    }
}

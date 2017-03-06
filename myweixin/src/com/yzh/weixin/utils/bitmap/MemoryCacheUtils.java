package com.yzh.weixin.utils.bitmap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 内存缓存
 *
 * @author Kevin
 * @date 2016-2-17
 */
public class MemoryCacheUtils {

    //private HashMap<String, SoftReference<Bitmap>> mMemoryCache = new HashMap<String, SoftReference<Bitmap>>();
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCacheUtils() {
        //LruCache
        //LRU:last recenttly used 最近最少使用算法
        long maxMemory = Runtime.getRuntime().maxMemory();//虚拟机分配的而最大内存, 默认16M
        System.out.println("maxMemory:" + maxMemory);
        mMemoryCache = new LruCache<String, Bitmap>((int) (maxMemory / 8)) {

            //返回每个对象的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //value.getByteCount();
                // return getRowBytes() * getHeight();
                //计算图片对象的大小
                int totalBytes = value.getRowBytes() * value.getHeight();
                return totalBytes;
            }

        };
    }

    public void setCache(Bitmap bitmap, String url) {
        //使用软引用将Bitmap包装起来
        //		SoftReference<Bitmap> soft = new SoftReference<Bitmap>(bitmap);
        //		mMemoryCache.put(url, soft);
        mMemoryCache.put(url, bitmap);
    }

    public Bitmap getCache(String url) {
        //		//return mMemoryCache.get(url);
        //		SoftReference<Bitmap> soft = mMemoryCache.get(url);
        //		if (soft != null) {
        //			Bitmap bitmap = soft.get();
        //			return bitmap;
        //		}
        return mMemoryCache.get(url);
    }
}

package com.yzh.weixin.utils.bitmap;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class MyBitmapUtils {

    private NetCacheUtils mNetUtils;
    private LocalCacheUtils mLocalUtils;
    private MemoryCacheUtils mMemoryUtils;

    public MyBitmapUtils() {
        mMemoryUtils = new MemoryCacheUtils();
        mLocalUtils = new LocalCacheUtils();
        mNetUtils = new NetCacheUtils(mLocalUtils, mMemoryUtils);
    }

    public void display(ImageView imageView, String url) {
      //优先内存缓存
        Bitmap bitmap = mMemoryUtils.getCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            System.out.println("从内存加载图片啦");
            return;
        }

        //其次本地缓存
        bitmap = mLocalUtils.getCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            System.out.println("从本地加载图片啦");

            //写内存缓存
            mMemoryUtils.setCache(bitmap, url);
            return;
        }

        //最后网络缓存
        mNetUtils.getBitmapFromNet(imageView, url);
    }

}

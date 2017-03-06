package com.yzh.weixin.utils.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络缓存
 *
 * @author Kevin
 * @date 2016-2-17
 */
public class NetCacheUtils {

    private LocalCacheUtils mLocalUtils;
    private MemoryCacheUtils mMemoryUtils;

    public NetCacheUtils(LocalCacheUtils localUtils,
                         MemoryCacheUtils memoryUtils) {
        mLocalUtils = localUtils;
        mMemoryUtils = memoryUtils;
    }

    public void getBitmapFromNet(ImageView imageView, String url) {
        new BitmapTask().execute(imageView, url);
    }

    class BitmapTask extends AsyncTask<Object, Void, Bitmap> {

        private ImageView imageView;
        private String url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Object... params) {
            imageView = (ImageView) params[0];
            url = (String) params[1];

            imageView.setTag(url);//将url和imageView绑定在一起

            Bitmap bitmap = download(url);

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            if (result != null) {
                //由于listview的重用机制,可能导致不同item加载了同一张图片, 产生图片错乱
                //解决方案: 在给imageView设置图片之前, 重新校验一次
                String bindUrl = (String) imageView.getTag();
                if (url.equals(bindUrl)) {
                    imageView.setImageBitmap(result);
                    System.out.println("从网络下载图片啦");

                    //写本地缓存
                    mLocalUtils.setCache(result, url);

                    //写内存缓存
                    mMemoryUtils.setCache(result, url);
                }
            }
        }

    }

    public Bitmap download(String url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream in = conn.getInputStream();

                //根据输入流生成bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(in);

                return bitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

        }

        return null;
    }

}

package com.yzh.weixin.utils.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import com.yzh.weixin.utils.MD5Encoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 本地缓存
 *
 * @author Kevin
 * @date 2016-2-17
 */
public class LocalCacheUtils {

    private static final String LOCAL_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/myweixin/bitmapcache";

    //写缓存
    public static void setCache(Bitmap bitmap, String url) {
        //写到sdcard
        File dir = new File(LOCAL_PATH);
        if (!dir.exists() || !dir.isDirectory()) {//不存在或者不是文件夹
            dir.mkdirs();//创建文件夹
        }

        try {
            File file = new File(dir, MD5Encoder.encode(url));//以url的md5为文件名
            //将图片压缩存储在本地
            bitmap.compress(CompressFormat.JPEG, 100,
                    new FileOutputStream(file));//参1:压缩格式, 参2:压缩质量(0-100);参3:文件输出流

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读缓存
    public static Bitmap getCache(String url) {
        try {
            File file = new File(LOCAL_PATH, MD5Encoder.encode(url));
            if (file.exists()) {
                //有缓存
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
                        file));
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

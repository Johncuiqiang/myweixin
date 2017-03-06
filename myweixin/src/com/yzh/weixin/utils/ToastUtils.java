package com.yzh.weixin.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * toast工具类
 * 
 */
public class ToastUtils {

	public static void show(Context ctx, String text) {
		Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
	}

	public static void showLong(Context ctx, String text) {
		Toast.makeText(ctx, text,Toast.LENGTH_LONG ).show();
	}
}

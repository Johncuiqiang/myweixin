package com.yzh.weixin.utils;

import java.text.DecimalFormat;

/**
 * Created by zhanghengqiu on 2015/12/4.
 * Copyright(c) by Yingzhi Tech and WHRTTV
 * User: fred
 * Date: 13-12-08 15:24
 */
public final class ErrorUtil {
    private static DecimalFormat df = new DecimalFormat("000000");
    public static String format(String msg, int errorCode){
        return msg + "(#" + df.format(errorCode) + ")";
    }

    public static String format(int msgId, int errorCode){
        return ContextUtil.getInstance().getResources().getString(msgId) + "(#" + df.format(errorCode) + ")";
    }
}

package com.yzh.weixin.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zhangyipeng on 16/1/16.
 */
public class Type{

    public int type;
    public int mList;
    public Type(int type ,Integer mList) {
        this.mList=mList;
        this.type = type;
    }
}

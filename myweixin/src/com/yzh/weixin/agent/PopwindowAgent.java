package com.yzh.weixin.agent;

/**
 * Created by cuiqiang on 2016/5/17.
 */
public class PopwindowAgent {
    private int mImageViewID;
    private String mstrName;

    public PopwindowAgent(){

    }

    public PopwindowAgent(int ImageViewID, String strName) {
        this.mImageViewID = ImageViewID;
        this.mstrName = strName;
    }

    public int getImageViewID() {
        return mImageViewID;
    }

    public String getstrName() {
        return mstrName;
    }
}

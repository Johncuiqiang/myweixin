package com.yzh.weixin.bean;

import java.io.Serializable;

/**
 * Created by zhangyipeng on 16/1/16.
 */
public class Share implements Serializable{

    private String name;
    private Integer icon;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }
}

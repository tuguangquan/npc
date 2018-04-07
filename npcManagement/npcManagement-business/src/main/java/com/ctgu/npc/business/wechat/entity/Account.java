package com.ctgu.npc.business.wechat.entity;

/**
 * Created by 54027 on 2018/4/6.
 */
public class Account {
    private int id;
    private String appId;
    private String appSecert;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppSecert() {
        return appSecert;
    }

    public void setAppSecert(String appSecert) {
        this.appSecert = appSecert;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}

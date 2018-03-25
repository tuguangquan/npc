package com.ctgu.npc.business.wechat.entity;

/**
 * Created by 54027 on 2018/3/24.
 */
public class UserWeChat {
    private int id;
    private String unionId;
    private String userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}

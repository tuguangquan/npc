package com.ctgu.npc.business.news.entity;

/**
 * Created by user on 2018/4/19.
 */
public class NewsExtend extends News{

    private String content;
    private String createUserName;
    private String createTime;

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}

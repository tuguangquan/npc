package com.ctgu.npc.business.wechat.coreServlet.process;

/**
 * Created by Administrator on 2017/11/15 0015.
 */
public class TextMessageResp extends RespBaseMessage {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}

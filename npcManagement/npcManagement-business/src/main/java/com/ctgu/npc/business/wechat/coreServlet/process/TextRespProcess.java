package com.ctgu.npc.business.wechat.coreServlet.process;


import com.ctgu.npc.business.wechat.util.MessageUtil;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/15 0015.
 */
public class TextRespProcess implements RespProcess {

    @Override
    public String getRespMessage(Map<String, String> requestMap) {
        // 发送方帐号（open_id）
        String fromUserName = requestMap.get("FromUserName");
        // 公众帐号
        String toUserName = requestMap.get("ToUserName");
        // 消息内容
        String msg = requestMap.get("Content");
        // 回复文本消息fromUserName
        TextMessageResp textMessage = new TextMessageResp();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(0);
        //todo 根据msg在数据库中匹配回复消息，如果没有匹配上，则回复默认消息
        textMessage.setContent(msg);
        return MessageUtil.textMessageToXml(textMessage);
    }
}
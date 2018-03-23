package com.ctgu.npc.business.wechat.coreServlet;


import com.ctgu.npc.business.wechat.coreServlet.process.TextRespProcess;
import com.ctgu.npc.business.wechat.entity.WeiXinChannel;
import com.ctgu.npc.business.wechat.service.WeChatService;
import com.ctgu.npc.business.wechat.util.MessageUtil;
import com.ctgu.npc.business.wechat.util.OpenIdUtil;
import com.ctgu.npc.business.wechat.util.SignUtil;
import com.ctgu.npc.business.wechat.util.WeChatUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/15 0015.
 */
public class CoreServlet extends HttpServlet {

    private static final long serialVersionUID = 4440739483644821986L;
    private WeChatService weChatService;

    public CoreServlet() {
        ServletContext sc = this.getServletContext();
        WebApplicationContext wac = (WebApplicationContext)sc.getAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        WeChatService weChatService = (WeChatService)wac.getBean("weChatService");
    }

    /**
     * 确认请求来自微信服务器
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 调用核心业务类接收消息、处理消息
        String respMessage = processRequest(request);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 消息类型
            String msgTypeReq = requestMap.get("MsgType");
            if (msgTypeReq.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                String openId = requestMap.get("FromUserName");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    requestMap.put("Content","谢谢您的关注！");
                    WeiXinChannel weiXinChannel = weChatService.getWeiXinChannelByOpId(openId);
                    if (weiXinChannel == null){
                        weiXinChannel = new WeiXinChannel();
                        weiXinChannel.setMemberId(UUID.randomUUID().toString().replace("-", ""));
                        weiXinChannel.setOpId(openId);
                        weiXinChannel.setUnionId(WeChatUtil.getUnionId(openId));
                        weiXinChannel.setStatus(1);
                        weiXinChannel.setFinalLoginDate(new Date());
                        weiXinChannel.setAddDate(new Date());
                        weChatService.addWeChat(weiXinChannel);
                    }else{
                        weiXinChannel.setStatus(1);
                        weChatService.updateWeChat(weiXinChannel);
                    }
                    return new TextRespProcess().getRespMessage(requestMap);
                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    WeiXinChannel weiXinChannel = weChatService.getWeiXinChannelByOpId(openId);
                    if (weiXinChannel == null){
                        weiXinChannel = new WeiXinChannel();
                        weiXinChannel.setMemberId(UUID.randomUUID().toString().replace("-", ""));
                        weiXinChannel.setOpId(openId);
                        weiXinChannel.setUnionId(WeChatUtil.getUnionId(openId));
                        weiXinChannel.setStatus(0);
                        weiXinChannel.setFinalLoginDate(new Date());
                        weiXinChannel.setAddDate(new Date());
                        weChatService.addWeChat(weiXinChannel);
                    }else{
                        weiXinChannel.setStatus(0);
                        weChatService.updateWeChat(weiXinChannel);
                    }
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    requestMap.put("Content","我们的菜单更新了，快来试试我们的新功能吧！！！");
                    return new TextRespProcess().getRespMessage(requestMap);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }
}

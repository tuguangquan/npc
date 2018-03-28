package com.ctgu.npc.business.wechat.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.wechat.util.OpenIdUtil;
import com.ctgu.npc.business.wechat.util.WeChatUtil;
import com.ctgu.npc.business.workingplf.entity.ReceivedMessage;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.logger.PlatformLogger;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by user on 2018/3/22.
 */
@Component
@Path("/weChat")
public class WeChatServiceWeb {

    PlatformLogger logger = PlatformLogger.getLogger(WeChatServiceWeb.class);

    private static String secretKey = FundamentalConfigProvider.get("npc.key");

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getUnionID")
    @POST
    public String getUnionID(@FormParam("code") String code,@FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(code + MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
       String unionId = OpenIdUtil.getUnionId(code);
        if (unionId==null || unionId.equals("null")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "获取openId失败!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(unionId, JsonResultUtils.Code.SUCCESS);
    }

    //发送微信消息
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/sendMsg")
    @POST
    public String sendMsg(@FormParam("content") String content,@FormParam("msgType") String msgType,@FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(content+msgType+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        if(content==null||content.equals("")||msgType==null||msgType.equals("")){
            return JsonResultUtils.getCodeAndMesByString(400, "参数不能为空!");
        }
        try {
            content = java.net.URLDecoder.decode(content,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("content decode failed");
            e.printStackTrace();
        }
        System.out.println(content);
        String result = "";
        if (msgType.equals("3.1.1")){
            result = WeChatUtil.sendCustomToUser(content);
        }else if(msgType.equals("3.3")){
            result = WeChatUtil.sendTemplateToUser(content);
        }
        if (result.equals("error")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "发送失败!");
        }else if(result.equals("success")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "发送成功!");
        }else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "发送异常!");
        }
    }
}
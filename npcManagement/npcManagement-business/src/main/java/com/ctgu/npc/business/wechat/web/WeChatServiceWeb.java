package com.ctgu.npc.business.wechat.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.wechat.entity.Account;
import com.ctgu.npc.business.wechat.service.AccountService;
import com.ctgu.npc.business.wechat.util.AesUtil;
import com.ctgu.npc.business.wechat.util.OpenIdUtil;
import com.ctgu.npc.business.wechat.util.WeChatUtil;
import com.ctgu.npc.business.workingplf.entity.ReceivedMessage;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.logger.PlatformLogger;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AccountService accountService;

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getUnionID")
    @POST
    public String getUnionID(@FormParam("encryptedData") String encryptedData,@FormParam("iv") String iv,@FormParam("code") String code,@FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(encryptedData+iv+code+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "code 不能为空!");
        }
        //获取会话密钥（session_key）
        String session_key = OpenIdUtil.getSession_key(code);
        String unionId = null;

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                JSONObject userInfoJSON = JSONObject.fromObject(result);
                unionId = userInfoJSON.get("unionId").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "unionid 获取失败!");
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(unionId, JsonResultUtils.Code.SUCCESS);
    }

    //发送微信消息
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/sendMsg")
    @POST
    public String sendMsg(@FormParam("content") String content,@FormParam("appId") String appId,@FormParam("msgType") String msgType,@FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(content+appId+msgType+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        if (appId==null || appId.equals("") || appId.equals("undefined")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "参数appId有误!");
        }
        Account account = accountService.findByAppId(appId);
        if (account == null){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "appId对应的服务号不存在，是不是appId填写错了!");
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
            result = WeChatUtil.sendCustomToUser(content,account.getAppId(),account.getAppSecert());
        }else if(msgType.equals("3.3")){
            result = WeChatUtil.sendTemplateToUser(content,account.getAppId(),account.getAppSecert());
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

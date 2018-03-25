package com.ctgu.npc.business.wechat.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.wechat.util.OpenIdUtil;
import com.ctgu.npc.business.workingplf.entity.ReceivedMessage;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by user on 2018/3/22.
 */
@Component
@Path("/weChat")
public class WeChatServiceWeb {

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
}

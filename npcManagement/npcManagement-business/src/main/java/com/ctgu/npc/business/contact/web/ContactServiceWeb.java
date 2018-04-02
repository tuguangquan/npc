package com.ctgu.npc.business.contact.web;


import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.contact.entity.*;
import com.ctgu.npc.business.contact.service.ContactService;
import com.ctgu.npc.business.contact.service.SqmyService;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.logger.PlatformLogger;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 联系互动 == 模块
 *
 * @author 旺旺
 */
@Component
@Path("/contact")
public class ContactServiceWeb {

    PlatformLogger logger = PlatformLogger.getLogger(ContactServiceWeb.class);

    @Autowired
    private ContactService contactService;
    @Autowired
    private SqmyService sqmyService;

    private static String secretKey = FundamentalConfigProvider.get("npc.key");

    /**
     * 我的社情民意列表
     * Description:
     * Company: ctgu
     *
     * @param loginName
     * @param level_code
     * @return
     * @author : youngmien
     * @date 2017-7-26 上午10:15:47
     */
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/savePersonalInfo")
    @POST
    public String mySqmyList(@FormParam("loginName") String loginName, @FormParam("level_code") String level_code, @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(loginName + level_code + MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        List<Sqmy> aList = sqmyService.getMySqmyList(loginName, level_code);
        return JsonResultUtils.getObjectResultByStringAsDefault(aList, JsonResultUtils.Code.SUCCESS);
    }

    /**
     * 社情民意详细
     * Description:
     * Company: ctgu
     *
     * @param theObjId
     * @param key
     * @return
     * @author : youngmien
     * @date 2017-7-26 上午10:26:54
     */
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/SqmyDetail")
    @POST
    public String getSqmyDetail(@FormParam("theObjId") String theObjId, @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(theObjId + MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        SqmyDetail theObj = new SqmyDetail();
        Sqmy sqmy = sqmyService.get(theObjId);
        theObj.setSqmy(sqmy);
        LeaveMessage leaveMessage = sqmyService.getLeaveMessage(sqmy.getQzmsgID());
        theObj.setLeaveMessage(leaveMessage);
        List<AnswerSqmy> alist = sqmyService.getAnswerSqmy(sqmy.getId());
        theObj.setaList(alist);
        return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
    }

    /**
     * ===我的留言分页列表
     *
     * @param loginName
     * @param curPageStr
     * @param level_code
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/myLeaveWordPage")
    @POST
    public String myLeaveWordPage(@FormParam("loginName") String loginName, @FormParam("pageNum") String pageNum,
                                  @FormParam("level_code") String level_code, @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(loginName + pageNum + level_code + MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        int curPage = 1;
        if (pageNum != null) {
            try {
                curPage = (int) StringUtils.toInteger(pageNum);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        PagesUtil<LeaveWord> pages = contactService.myLeaveWordPage(loginName,
                curPage, level_code);
        return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
    }

    /**
     * === 留言详细
     *
     * @param theObjId
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getInfoLeaveWord")
    @POST
    public String getInfoLeaveWord(@FormParam("theObjId") String theObjId, @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(theObjId + MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        LeaveWord theObj = contactService.getInfoLeaveWord(theObjId);
        return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
    }

    /**
     * 回复留言
     *
     * @param loginName
     * @param replyContent
     * @param phone
     * @return
     */
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/replyLeaveWord")
    @POST
    public String replyLeaveWord(@FormParam("loginName") String loginName, @FormParam("replyContent") String replyContent,
                                 @FormParam("phone") String phone, @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(loginName + replyContent+phone+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        String result = "false";
        if (replyContent != null && phone !=null) {
            result = contactService.replyLeaveWord(phone,replyContent, loginName);
        }
        logger.info("return result->" + result);
        if (result.equals("false")){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "回复短信发送失败!");
        }else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "回复短信发送成功!");
        }
    }

}

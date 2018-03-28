package com.ctgu.npc.business.sys.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.sys.dto.OfficeInfo;
import com.ctgu.npc.business.sys.entity.Office;
import com.ctgu.npc.business.sys.service.OfficeService;
import com.ctgu.npc.business.sys.service.UserService;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.service.SysService;
import com.ctgu.npc.business.wechat.entity.UserWeChat;
import com.ctgu.npc.business.wechat.entity.WeiXinChannel;
import com.ctgu.npc.business.wechat.service.UserWeChatService;
import com.ctgu.npc.business.wechat.service.WeChatService;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.logger.PlatformLogger;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created by user on 2018/3/19.
 */
@Component
@Path("/sys")
public class SysServiceWeb {

    PlatformLogger logger = PlatformLogger.getLogger(SysServiceWeb.class);

    private static String secretKey = FundamentalConfigProvider.get("npc.key");
    @Autowired
    private UserService userService;

    @Autowired
    private SysService systemService;

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private UserWeChatService userWeChatService;

    @Autowired
    private OfficeService officeService;

    /**
     * 修改个人用户密码
     * @param oldPassword
     * @param newPassword
     * @param loginName
     * @return
     */
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/updatePwd")
    @POST
    public String updatePwd( @FormParam("loginName") String loginName,
                             @FormParam("oldPassword") String oldPassword,
                             @FormParam("newPassword") String newPassword,
                             @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(loginName+oldPassword+newPassword + MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        Users theUser = userService.getUser(loginName);
        if(theUser != null){
            if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
                if (SysService.validatePassword(oldPassword, theUser.getPassword())){
                    systemService.updatePasswordById(theUser.getId(),  newPassword);
                    return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "update password success!");
                }else{
                    return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "update password failed!");
                }
            }else{
                return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "密码不能为空!");
            }
        }else{
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "未找到该用户!");
        }
    }

    /**
     * 查询用户是否登陆
     * @param unionId
     * @return
     */
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/checkLogin")
    @POST
    public String checkLogin( @FormParam("unionId") String unionId,
                        @FormParam("key") String key){
        String keyWord = MD5Util.md5Encode(unionId+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        WeiXinChannel weiXinChannel = weChatService.getWeiXinChannelByUnionId(unionId);
        if (weiXinChannel==null){ //说明没有关注或者关注时，sql写入错误，建议重新关注
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请先关注我们服务号!");
        }
         UserWeChat userWeChat = userWeChatService.findByUnionId(unionId);
        if (userWeChat!=null){  //还没有关联
            return JsonResultUtils.getObjectResultByStringAsDefault(true, JsonResultUtils.Code.SUCCESS);
        }else{
            return JsonResultUtils.getObjectResultByStringAsDefault(false, JsonResultUtils.Code.SUCCESS);
        }
    }
    /**
     * 根据用户名密码进行登录判断
     * @param uname
     * @param pswd
     * @return
     */
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/login")
    @POST
    public String login(@FormParam("uname") String uname,
                        @FormParam("pswd") String pswd,
                        @FormParam("unionId") String unionId,
                        @FormParam("key") String key){
        String keyWord = MD5Util.md5Encode(uname+pswd+unionId+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        WeiXinChannel weiXinChannel = weChatService.getWeiXinChannelByUnionId(unionId);
        if (weiXinChannel==null){ //说明没有关注或者关注时，sql写入错误，建议重新关注
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请先关注我们服务号!");
        }
        Users users;
        if (StringUtils.isMobile(uname))
        {
            users = userService.getUserByMobile(uname,pswd);
        }else{
            users = userService.getUserByParams(uname,pswd);
        }
        //登陆成功将登陆用户信息和微信表关联
        if (users!=null){
            UserWeChat userWeChat = userWeChatService.findByUnionId(unionId);
            if (userWeChat!=null){  //还没有关联
                userWeChat = new UserWeChat();
                userWeChat.setUnionId(unionId);
                userWeChat.setUserId(users.getId());
                userWeChatService.add(userWeChat);
            }
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(users, JsonResultUtils.Code.SUCCESS);

    }


    /**
     * 根据用户的级别编码查询用户级别名称
     * @param levels
     * @return
     */
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getLevelMaps")
    @POST
    public String getLevelByList(@FormParam("levels") String levels,
                                 @FormParam("key") String key){
        String keyWord = MD5Util.md5Encode(levels+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        List<Map<String,Object>> levelMaps = new ArrayList<Map<String,Object>>();
        if(levels != null){
            String[] str = levels.split(",");
            for(int i = 0;i < str.length; i++){
                Map<String, Object> map = new HashMap<String, Object>();
                String value = userService.getOfficeNameByCode(str[i]);
                map.put("level_code", str[i]);
                map.put("level_title", value);
                levelMaps.add(map);
            }
        }
        return JsonResultUtils.getObjectResultByStringAsDefault(levelMaps, JsonResultUtils.Code.SUCCESS);

    }

    /**
     *
     * Description:根据userId获取该user的所有系统级别
     * Company: ctgu
     * @author : youngmien
     * @date  2017-1-17 上午9:13:02
     * @param userId
     * @return String 如100,10010
     */
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getLevelsByUserId")
    @POST
    public String getLevelsByUserId(@FormParam("userId") String userId,
                                    @FormParam("key") String key){
        String keyWord = MD5Util.md5Encode(userId+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        String levels = userService.getLevelsByUserId(userId);
        System.out.println("levels->" + levels);
        return JsonResultUtils.getObjectResultByStringAsDefault(levels, JsonResultUtils.Code.SUCCESS);
    }



    /**
     * 用户分页
     * @param pageNum
     * @return
     */
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/page")
    @POST
    public String getPagesByNum(@FormParam("pageNum") String pageNum,
                                @FormParam("key") String key){
        String keyWord = MD5Util.md5Encode(pageNum+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        int curPageI = 1;
        if(pageNum != null){
            try {
                curPageI = Integer.parseInt(pageNum);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        //获取总记录数
        int rowCount = userService.getRowCount();
        PagesUtil pagesUtil = new PagesUtil();
        pagesUtil.setRowCount(rowCount);
        if(pagesUtil.getTotalPages() < curPageI){
            curPageI = pagesUtil.getTotalPages();
        }
        pagesUtil = userService.getPagesByNum(pagesUtil);
        System.out.println("list->"+pagesUtil.getLists().size());
        return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);
    }


    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getChildOfficeByTypeAndCode")
    @POST
    public String getChildOfficeByTypeAndCode(@FormParam("code") String code,
                                              @FormParam("type") String type,
                                              @FormParam("key") String key){
        String keyWord = MD5Util.md5Encode(code+type+ MD5Util.getDateStr() + secretKey);
        if (code==null || code.equals("undefined")||type==null ||
                type.equals("undefined")||!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        List<OfficeInfo> offices = officeService.getChildOfficeByTypeAndCode(type,code);
        return JsonResultUtils.getObjectResultByStringAsDefault(offices, JsonResultUtils.Code.SUCCESS);
    }

    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getOffice")
    @POST
    public String getOffice(@FormParam("code") String code,
                                              @FormParam("key") String key){
        String keyWord = MD5Util.md5Encode(code+ MD5Util.getDateStr() + secretKey);
        if (code==null || code.equals("undefined")||!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        OfficeInfo office = officeService.getOfficeByCode(code);
        return JsonResultUtils.getObjectResultByStringAsDefault(office, JsonResultUtils.Code.SUCCESS);
    }

}

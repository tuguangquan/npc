package com.ctgu.npc.business.sys.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.sys.dto.OfficeInfo;
import com.ctgu.npc.business.sys.service.UserService;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.sys.entity.Office;
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
import java.util.List;

/**
 * Created by user on 2018/3/19.
 */
@Component
@Path("/user")
public class UserServiceWeb {

    PlatformLogger logger = PlatformLogger.getLogger(UserServiceWeb.class);

    private static String secretKey = FundamentalConfigProvider.get("npc.key");
    @Autowired
    private UserService userService;


    /**===代表团分页列表
     * 根据系统级别编码查询部门信息(如代表团)并包含分页信息
     * @param pageNum
     * @param level_code
     * @param office_type
     * @return
     */
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getOfficeNameByLevelCodePages")
    @POST
    public String getOfficeNameByLevelCodePages(@FormParam("pageNum") String pageNum,
                                                @FormParam("level_code") String level_code,
                                                @FormParam("office_type") String office_type,
                                                @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(pageNum +level_code+office_type+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        int curPage = 1;
        if (pageNum != null) {
            try {
                curPage = Integer.parseInt(pageNum);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        PagesUtil<OfficeInfo>  pagesUtil= userService.getOfficeNameByLevelCodePages(level_code,office_type,curPage);
        return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);
    }

    /**
     * 根据系统级别部门编码查询信息列表(如代表团)
     * @param pageNum
     * @param level_code
     * @param office_type
     * @return
     */
    @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getOfficeByLevelCode")
    @POST
    public String getOfficeByLevelCode(@FormParam("pageNum") String pageNum,
                                           @FormParam("level_code") String level_code,
                                           @FormParam("office_type") String office_type,
                                           @FormParam("key") String key){
        String keyWord = MD5Util.md5Encode(pageNum +level_code+office_type+ MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)){
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        int curPage = 1;
        if (pageNum != null) {
            try {
                curPage = Integer.parseInt(pageNum);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        List<OfficeInfo>  lists = userService.getOfficeNameByLevelCode(level_code,office_type,curPage);
        return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
    }

}

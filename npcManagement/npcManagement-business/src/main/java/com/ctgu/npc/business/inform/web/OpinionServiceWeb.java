package com.ctgu.npc.business.inform.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.inform.entity.Opinion;
import com.ctgu.npc.business.inform.entity.OpinionReport;
import com.ctgu.npc.business.inform.entity.Report;
import com.ctgu.npc.business.inform.service.OpinionService;
import com.ctgu.npc.business.sys.service.UserService;
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
 * 意见管理
 * @author 旺旺
 *
 */

@Component
@Path("/opinion")
public class OpinionServiceWeb {

	PlatformLogger logger = PlatformLogger.getLogger(OpinionServiceWeb.class);

	@Autowired
	private OpinionService opinionService;
	
	@Autowired
	private UserService userService;

	private static String secretKey = FundamentalConfigProvider.get("npc.key");

	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/addsaveOpinion")
	@POST
	public String addsaveOpinion(@FormParam("level_code") String level_code,@FormParam("theObjId") String theObjId,
								 @FormParam("loginName") String loginName,@FormParam("json_str") String json_str,@FormParam("key") String key)  {
		String keyWord = MD5Util.md5Encode(level_code+theObjId+loginName+json_str+key+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		String userId = userService.getUser(loginName).getId();
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<OpinionReport>() {}.getType();
			OpinionReport theObj = (OpinionReport) gson.fromJson(json_str, type);
			Opinion opinion = theObj.getOpinion();
			Report report = theObj.getReport();
			opinionService.addsaveOpinion(opinion,report,userId,level_code);
			return JsonResultUtils.getObjectResultByStringAsDefault("true", JsonResultUtils.Code.SUCCESS);
		}
		return JsonResultUtils.getObjectResultByStringAsDefault("false", JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * 报告列表
	 * 根据系统级别分页查询代表报告列表
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListReport")
	@POST
	public String getListReport(@FormParam("level_code") String level_code,@FormParam("pageNum") String pageNum,
									  @FormParam("key") String key) {
		List<Report> lists;
		String keyWord = MD5Util.md5Encode(level_code+pageNum+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		lists = opinionService.getListReport(level_code,curPage);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * 报告列表
	 * 根据系统级别分页查询代表报告列表包含分页信息
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListReportPage")
	@POST
	public String getListReportPage(@FormParam("level_code") String level_code,@FormParam("pageNum") String pageNum,
											   @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code+pageNum+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		PagesUtil<Report> lists;
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		lists = opinionService.getListReportPage(level_code,curPage);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * 根据Report的id查询详细
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getDetailByIdReport")
	@POST
	public String getDetailByIdReport(@FormParam("theObjId") String theObjId,
									  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Report report = opinionService.getDetailByIdReport(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(report, JsonResultUtils.Code.SUCCESS);
	}
	
	
	
	
	/**
	 * 根据系统级别分页查询意见列表
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListOpinion")
	@POST
	public String getListOpinion(@FormParam("level_code") String level_code,@FormParam("pageNum") String pageNum,
										@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code+pageNum+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		List<Opinion> lists = opinionService.getListOpinion(level_code,curPage);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * 根据opinion的id查询详细
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getDetailByIdOpinion")
	@POST
	public String getDetailByIdOpinion(@FormParam("theObjId") String theObjId,
										@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Opinion opinion = opinionService.getDetailByIdOpinion(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(opinion, JsonResultUtils.Code.SUCCESS);
	}

}

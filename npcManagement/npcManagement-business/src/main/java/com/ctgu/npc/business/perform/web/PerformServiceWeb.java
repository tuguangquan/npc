package com.ctgu.npc.business.perform.web;


import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.perform.entity.*;
import com.ctgu.npc.business.perform.service.PerformService;
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
 * 履职服务
 * 
 * @author 旺旺
 * 
 */

@Component
@Path("/perform")
public class PerformServiceWeb {

	PlatformLogger logger = PlatformLogger.getLogger(PerformServiceWeb.class);

	private final String TAG = "PerformController";
	private static String secretKey = FundamentalConfigProvider.get("npc.key");
	@Autowired
	private PerformService performService;
	
	/**
	 * === 我的代表工作
	 * @param loginName
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/myJobReportPage")
	@POST
	public String myJobReportPage(@FormParam("loginName") String loginName,
								  @FormParam("pageNum") String pageNum,
								  @FormParam("level_code") String level_code,
								  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+pageNum+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
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
		PagesUtil<JobReport> pages = performService.myJobReportPage(
				loginName, curPage, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}


	/**
	 * === 提交我的代表工作
	 * @param loginName
	 * @param json_str
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/saveMyPerformJob")
	@POST
	public String addMyPerformJob(@FormParam("loginName") String loginName,
								  @FormParam("json_str") String json_str,
								  @FormParam("level_code") String level_code,
								  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+json_str+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		JobReport theObj = new JobReport();
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<JobReport>() {
			}.getType();
			theObj = (JobReport) gson.fromJson(json_str, type);
			if(theObj != null){
				//System.out.println("->theObj" + theObj.toString() );
				performService.addMyPerformJob(theObj, loginName, level_code);
				return "save success!";
			}else{
				return "save fail!";
				}
		} else {
			return "save fail!";
		}
	}
	
	
	
	/**
	 * === 提交我的代表活动
	 * @param loginName
	 * @param json_str
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/saveMyPerformAct")
	@POST
	public String saveMyPerformAct(@FormParam("loginName") String loginName,
								   @FormParam("json_str") String json_str,
								   @FormParam("level_code") String level_code,
								   @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+json_str+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		PerformReport theObj = new PerformReport();
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<PerformReport>() {
			}.getType();
			theObj = (PerformReport) gson.fromJson(json_str, type);
			if(theObj != null){
				//System.out.println(theObj.toString());
				performService.addMyPerformAct(theObj, loginName, level_code);
				return "save success!";
			}else{
				return "save fail!";
			}
		} else {
			return "save fail!";
		}
	}
	
	

	/**
	 * ===我的代表活动-- 分页列表
	 * 
	 * @param loginName
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/myPerformActPage")
	@POST
	public String myPerformActPage(@FormParam("loginName") String loginName,
								   @FormParam("pageNum") String pageNum,
								   @FormParam("level_code") String level_code,
								   @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+pageNum+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
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
		PagesUtil<PerformReport> pages = performService.myPerformActPage(
				loginName, curPage, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * ===我的代表活动--详细信息
	 * 
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoMyPerformAct")
	@POST
	public String getInfoMyPerformAct(@FormParam("theObjId") String theObjId,
									  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		PerformReport performReport = performService.getInfoPerformReport(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(performReport, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * ===我的履职报告
	 * 
	 * @param loginName
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/myWorkReportPage")
	@POST
	public String myWorkReportPage(@FormParam("loginName") String loginName,
								   @FormParam("pageNum") String pageNum,
								   @FormParam("level_code") String level_code,
								   @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+pageNum+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
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

		PagesUtil<WorkReport> pages = performService.myWorkReportPage(
				loginName, curPage, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * === 我的履职报告详细
	 * 
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoMyWorkReport")
	@POST
	public String getInfoMyWorkReport(@FormParam("theObjId") String theObjId,
									  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		WorkReport theObj = performService.getInfoMyWorkReport(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);

	}
	
	/**
	 * === 我的履职统计
	 * @param loginName
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/myReportStatisticList")
	@POST
	public String myReportStatisticList(@FormParam("loginName") String loginName,
										@FormParam("level_code") String level_code,
										@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		List<MyReportEntry> pages = performService.myReportStatisticList(
				loginName,  level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * === 获取登记类型列表
	 * @param loginName
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/actTypeList")
	@POST
	public String actTypeList(@FormParam("loginName") String loginName,
							  @FormParam("level_code") String level_code,
							  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		List<ActType> lists = performService.actTypeList(
				loginName,  level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}


	/**
	 * 代表工作查询
	 * 
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/jobSearchPage")
	@POST
	public String jobSearchPage(@FormParam("pageNum") String pageNum,
								@FormParam("level_code") String level_code,
								@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
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
		PagesUtil<JobReport> pages = performService.jobSearchPage(curPage,
				level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * ===代表工作查询详细===
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoJobReport")
	@POST
	public String getInfoJobReport( @FormParam("theObjId") String theObjId,
								   @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		JobReport theObj = performService.getInfojobSearchDetail(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * === 代表活动查询
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/actSearchPage")
	@POST
	public String actSearchPage(@FormParam("pageNum") String pageNum,
								@FormParam("level_code") String level_code,
								@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
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
		PagesUtil<PerformReport> pages = performService.actSearchPage(curPage,
				level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * === 履职报告查询
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/workSearchPage")
	@POST
	public String workSearchPage(@FormParam("pageNum") String pageNum,
								 @FormParam("level_code") String level_code,
								 @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
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
		PagesUtil<WorkReport> pages = performService.workSearchPage(curPage,
				level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * === 履职报告详细
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoworkSearchDetail")
	@POST
	public String getInfoworkSearchDetail(@FormParam("theObjId") String theObjId,
										  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		WorkReport theObj = performService.getInfoworkSearchDetail(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
	}
	
}

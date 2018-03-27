package com.ctgu.npc.business.learning.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.learning.entity.*;
import com.ctgu.npc.business.learning.service.LearningService;
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

/**
 * 学习交流
 * @author 旺旺
 *
 */
@Component
@Path("/learning")
public class LearningServiceWeb {

	PlatformLogger logger = PlatformLogger.getLogger(LearningServiceWeb.class);

	@Autowired
	private LearningService learningService;

	private static String secretKey = FundamentalConfigProvider.get("npc.key");
	/**
	 * 人大规章制度
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @param typeValue
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/listPageNpcRule")
	@POST
	public String listPageNpcRule(@FormParam("level_code") String level_code,
			                      @FormParam("loginName") String loginName,
								  @FormParam("pageNum") String pageNum,
								  @FormParam("typeValue") String typeValue,
								  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName + pageNum +typeValue+ MD5Util.getDateStr() + secretKey);
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

		PagesUtil<Rule> pages = learningService.listPageNpcRule(
				loginName, curPage, level_code,typeValue);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}
	/**
	 * 规章制度详细
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoRule")
	@POST
	public String getInfoRule(@FormParam("theObjId") String theObjId,
							  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Rule theObj = learningService.getInfoRule(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);

	}
	
	/**
	 * 履职学习培训资料分页列表
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/listPageMaterial")
	@POST
	public String listPageMaterial(@FormParam("level_code") String level_code,
								   @FormParam("loginName") String loginName,
								   @FormParam("curPageStr") String pageNum,
								   @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName + pageNum + MD5Util.getDateStr() + secretKey);
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
		PagesUtil<Material> pages = learningService.listPageMaterial(
				loginName, curPage, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * 履职学习培训资料详细
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoMaterial")
	@POST
	public String getInfoMaterial(@FormParam("theObjId") String theObjId,
								  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Material theObj = learningService.getInfoMaterial(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);

	}
	
	
	
	/**
	 * 履职活动培训信息列表
	 * @param level_code
	 * @param pageNum
	 * @param loginName
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/listPageTraining")
	@POST
	public String listPageTraining(@FormParam("level_code") String level_code,
								   @FormParam("loginName") String loginName,
								   @FormParam("pageNum") String pageNum,
								   @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName + pageNum + MD5Util.getDateStr() + secretKey);
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

		PagesUtil<Training> pages = learningService.listPageTraining(
				loginName, curPage, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * 履职活动培训详细
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoTraining")
	@POST
	public String getInfoTraining(@FormParam("theObjId") String theObjId,
								  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Training theObj = learningService.getInfoTraining(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);

	}
	
	
	/**
	 * === 优秀建议议案
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/listPageExceSuggestion")
	@POST
	public String listPageExceSuggestion(@FormParam("level_code") String level_code,
										 @FormParam("loginName") String loginName,
										 @FormParam("pageNum") String pageNum,
										 @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName + pageNum + MD5Util.getDateStr() + secretKey);
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
		PagesUtil<ExceSuggestion> pages = learningService.listPageExceSuggestion(
				loginName, curPage, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}
	/**
	 * 优秀建议议案详细
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoExceSuggestion")
	@POST
	public String getInfoExceSuggestion(@FormParam("theObjId") String theObjId,
										@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		ExceSuggestion theObj = learningService.getInfoExceSuggestion(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * 优秀履职报告分页列表
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/listPageExceWorkReport")
	@POST
	public String listPageExceWorkReport(@FormParam("level_code") String level_code,
										 @FormParam("loginName") String loginName,
										 @FormParam("pageNum") String pageNum,
										 @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName + pageNum + MD5Util.getDateStr() + secretKey);
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
		PagesUtil<ExceWorkReport> pages = learningService.listPageExceWorkReport(
				loginName, curPage, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * === 优秀履职报告详细
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoExceWorkReport")
	@POST
	public String getInfoExceWorkReport(@FormParam("theObjId") String theObjId,
										@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId +MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		ExceWorkReport theObj = learningService.getInfoExceWorkReport(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
	}
	

	/**
	 * 优秀调研报告分页列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = { "listPageExceReport" })
	@ResponseBody
	public PagesUtil<ExceReport> listPageExceReport(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");
		
		//String typeValue = request.getParameter("typeValue");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<ExceReport> pages = learningService.listPageExceReport(
				loginName, curPage, level_code);
		return pages;
	}*/
	
	/**
	 * 优秀调研报告详细
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = { "getInfoMaterial" })
	@ResponseBody
	public ExceReport getInfoExceReport(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		ExceReport theObj = learningService.getInfoExceReport(id);

		return theObj;

	}*/
	
}

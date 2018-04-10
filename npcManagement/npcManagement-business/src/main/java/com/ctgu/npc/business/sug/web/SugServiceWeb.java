package com.ctgu.npc.business.sug.web;

import com.ctgu.npc.business.common.contant.Contant;
import com.ctgu.npc.business.common.utils.GsonUtils;
import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.sug.dto.SuggestionDto;
import com.ctgu.npc.business.sug.entity.*;
import com.ctgu.npc.business.sug.service.SugService;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.logger.PlatformLogger;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("/sug")
public class SugServiceWeb {

	PlatformLogger logger = PlatformLogger.getLogger(SugServiceWeb.class);

	private static String secretKey = FundamentalConfigProvider.get("npc.key");

	@Autowired
	private SugService sugService;
	/**
	 * 沟通信息
	 * Description:
	 * Company: ctgu
	 * @author : youngmien
	 * @date  2016-9-27 下午5:22:14
	 * @param theObjId
	 * @return
	 */
   @Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/communicationInfo")
    @POST
	public String communicationInfo(@FormParam("theObjId") String theObjId,
											   @FormParam("key") String key){
	   String keyWord = MD5Util.md5Encode(theObjId+ MD5Util.getDateStr() + secretKey);
	   if (!keyWord.equals(key)){
		   return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
	   }
		CommunicationForm communicationForm = sugService.getCommunicationForm(theObjId);
	   return JsonResultUtils.getObjectResultByStringAsDefault(communicationForm, JsonResultUtils.Code.SUCCESS);

   }


	/**
	 * 下载办理答复件
	 * @throws Exception
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/answerFileDownLoad")
	@POST
	public void answerFileDownLoad(@FormParam("theObjId") String theObjId,
								   @FormParam("key") String key) throws Exception{

	}



	/**
	 * 议案建议审核
	 * Description:
	 * Company: ctgu
	 * @author : youngmien
	 * @date  2016-8-29 下午3:59:24
	 * @param loginName
	 * @param json_str
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/saveCheckSug")
	@POST
	public String saveCheckSug(@FormParam("loginName") String loginName,
							   @FormParam("json_str") String json_str,
							   @FormParam("level_code") String level_code,
							   @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(loginName+json_str+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<Suggestion>() {
			}.getType();
			Suggestion sug = gson.fromJson(json_str, type);
			sugService.saveCheckSug(sug, loginName, level_code);
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "success!");
		} else {
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "fail!");
		}
	}





	/**
	 * 建议议案评价
	 * @param loginName
	 * @param level_code
	 * @param json_str
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/saveEvaluteSug")
	@POST
	public String saveEvaluteSug(@FormParam("loginName") String loginName,
								 @FormParam("json_str") String json_str,
								 @FormParam("level_code") String level_code,
								 @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(loginName+json_str+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<Suggestion>() {
			}.getType();
			Suggestion sug = gson.fromJson(json_str, type);
			sugService.saveEvaluteSug(sug, loginName, level_code);
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "success!");
		} else {
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "fail!");
		}


	}
	/**
	 * 建议议案删除
	 * @param sugId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/deleteSug")
	@POST
	public String deleteSug(@FormParam("sugId") String sugId,
							@FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(sugId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Suggestion suggestion = sugService.selectSugById(sugId);
		if (suggestion == null){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "不存在该数据记录!");
		}
		if (suggestion.getStatus().equals(Contant.sug_status_2) || suggestion.getStatus().equals(Contant.sug_status_1)){
			sugService.deleteSug(sugId);
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "delete success!");
		}else{
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "delete filed!");
		}
	}

	/**
	 * ===办理=待签收===交办直接办理
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugAssignDealList")
	@POST
	public String sugAssignDealList(@FormParam("pageNum") String pageNum,
									@FormParam("level_code") String level_code,
									@FormParam("type_value") String type_value,
									@FormParam("status") String status,
									@FormParam("loginName") String loginName,
									@FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+status+loginName+ MD5Util.getDateStr() + secretKey);
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
		PagesUtil<AssignForm> pagesUtil = sugService.sugAssignDealList(
				curPage, level_code,status,type_value,loginName);
		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);


	}





	/**
	 * ===交办任务列表===
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugAssignList")
	@POST
	public String sugAssignList(@FormParam("pageNum") String pageNum,
											   @FormParam("level_code") String level_code,
											   @FormParam("type_value") String type_value,
											   @FormParam("status") String status,
											   @FormParam("loginName") String loginName,
											   @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+status+loginName+ MD5Util.getDateStr() + secretKey);
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

		PagesUtil<AssignForm> pagesUtil = sugService.sugAssignList(
				curPage, level_code,status,type_value,loginName);
		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);


	}



	/**
	 * ===转交列表===议案建议
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 * @param status
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugTransferList")
	@POST
	public String sugTransferList(@FormParam("pageNum") String pageNum,
												   @FormParam("level_code") String level_code,
												   @FormParam("type_value") String type_value,
												   @FormParam("status") String status,
												   @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+status+status+ MD5Util.getDateStr() + secretKey);
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

		PagesUtil<TransferForm> pagesUtil = sugService.sugTransferList(
				curPage, level_code,status,type_value);
		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);


	}



	/**
	 * 立案列表
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugPutonList")
	@POST
	public String sugPutonList(@FormParam("pageNum") String pageNum,
											  @FormParam("level_code") String level_code,
											  @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(pageNum+level_code+ MD5Util.getDateStr() + secretKey);
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

		PagesUtil<Suggestion> pagesUtil = sugService.sugPutonList(
				curPage, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);


	}




	/**
	 * === 审核列表
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/checkList")
	@POST
	public String sugCheckList(@FormParam("pageNum") String pageNum,
											  @FormParam("level_code") String level_code,
											  @FormParam("type_value") String type_value,
											  @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+ MD5Util.getDateStr() + secretKey);
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

		PagesUtil<Suggestion> pagesUtil = sugService.sugCheckList(
				curPage, level_code, type_value);

		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);


	}



	/**
	 * ===优秀的
	 * 查看本级议案建议列表包含分页信息
	 *
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugListExcellentPage")
	@POST
	public String sugListExcellentPage(@FormParam("pageNum") String pageNum,
													  @FormParam("level_code") String level_code,
													  @FormParam("type_value") String type_value,
													  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+ MD5Util.getDateStr() + secretKey);
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
		PagesUtil<Suggestion> pagesUtil = sugService.sugListExcellentPage(
				curPage, level_code, type_value);

		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);

	}

	/**
	 * ===重点的
	 * 查看本级议案建议列表包含分页信息
	 *
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugListEmphasisPage")
	@POST
	public String sugListEmphasisPage(@FormParam("pageNum") String pageNum,
													 @FormParam("level_code") String level_code,
													 @FormParam("type_value") String type_value,
													 @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+ MD5Util.getDateStr() + secretKey);
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

		PagesUtil<Suggestion> pagesUtil = sugService.sugListEmphasisPage(
				curPage, level_code, type_value);
		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);

	}

	/**
	 * ===代表团的
	 * 根据代表团id查询本代表团所有的suggestion包含分页信息
	 *
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 *  @param team_id
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugListOfficePage")
	@POST
	public String sugListOfficePage(@FormParam("pageNum") String pageNum,
									@FormParam("level_code") String level_code,
									@FormParam("type_value") String type_value,
									@FormParam("team_id") String team_id,
									@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+team_id+ MD5Util.getDateStr() + secretKey);
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
		PagesUtil<Suggestion> pagesUtil = sugService.sugListOfficePage(curPage,
				level_code, team_id, type_value);
		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);

	}

	/**
	 * ===代表们的
	 * 查看本级代表们议案建议列表 包含分页信息
	 *
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugListAllPage")
	@POST
	public String sugListAllPage(@FormParam("pageNum") String pageNum,
												@FormParam("level_code") String level_code,
												@FormParam("type_value") String type_value,
												@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+ MD5Util.getDateStr() + secretKey);
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
		PagesUtil<Suggestion> pagesUtil = sugService.sugListAllPage(curPage,
				level_code, type_value);

		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);

	}// -----sugListAllPage END ------------------

	/**
	 * ===我领衔的
	 *  根据登录名和系统等级分页查询suggestion 的List包含分页信息
	 *
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 *  @param loginName
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/mySugListHeadPage")
	@POST
	public String mySugListHeadPage(@FormParam("pageNum") String pageNum,
									@FormParam("level_code") String level_code,
									@FormParam("type_value") String type_value,
									@FormParam("loginName") String loginName,
									@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+loginName+ MD5Util.getDateStr() + secretKey);
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
		PagesUtil<Suggestion> pagesUtil = sugService.mySugListHeadPage(
				loginName, curPage, level_code, type_value);

		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);

	}

	/**
	 * ===我联名的
	 * 根据登录名和系统等级分页查询我联名的suggestion 的List包含分页信息
	 *
	 * @param pageNum
	 * @param level_code
	 * @param type_value
	 *  @param loginName
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/mySugListJoinPage")
	@POST
	public String mySugListJoinPage(@FormParam("pageNum") String pageNum,
									@FormParam("level_code") String level_code,
									@FormParam("type_value") String type_value,
									@FormParam("loginName") String loginName,
									@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+type_value+loginName+ MD5Util.getDateStr() + secretKey);
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

		PagesUtil<Suggestion> pagesUtil = sugService.mySugListJoinPage(
				loginName, curPage, level_code, type_value);
		return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);

	}

	/**===新增
	 * 新增议案建议
	 *
	 * @param loginName
	 * @param json_str
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/mySugHeadAdd")
	@POST
	public String mySugHeadAdd(@FormParam("loginName") String loginName,
							   @FormParam("json_str") String json_str,
							   @FormParam("level_code") String level_code,
							   @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+json_str+level_code+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Suggestion sug;
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<Suggestion>() {
			}.getType();
			sug = gson.fromJson(json_str, type);
			sugService.mySugHeadAdd(sug, loginName, level_code);
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "save success!");
		} else {
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "save fail!!");
		}
	}

	/*
	 * ===议案详细
	 * 根据suggestion的id和suggestion的type查找Suggestion议案的详细
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/billsDetail")
	@POST
	public String billsDetail(@FormParam("sugId") String sugId,@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(sugId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		BillsEntity theObj = sugService.billsDetail(sugId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
	}



	/*
	 * ===建议详细
	 * 根据suggestion的id和suggestion的type查找Suggestion的详细
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugInfoHead")
	@POST
	public String sugInfoHead(@FormParam("sugId") String sugId,
								  @FormParam("key") String key) {
		String type = "1";
		String keyWord = MD5Util.md5Encode(sugId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Suggestion sug = sugService.getSuggestionByIdType(sugId, type);
		if (sug != null) {
			// 获取联名人
			if (StringUtils.isNotEmpty(sug.getSecondWriterIDS())) {
				List<Users> list = sugService.getSecondWriter(sug
						.getSecondWriterIDS());
				String str = "";
				for (Users u : list) {
					str += u.getName() + ",";
				}
				sug.setSecondWriter(str.substring(0, str.length() - 1));
			}


		}
		return JsonResultUtils.getObjectResultByStringAsDefault(sug, JsonResultUtils.Code.SUCCESS);
	}






	/**
	 * 议案建议的转交信息
	 * @param sugId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListTransfer")
	@POST
	public String getListTransfer(@FormParam("sugId") String sugId,
											  @FormParam("key") String key)
	{
		String keyWord = MD5Util.md5Encode(sugId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		List<TransferForm> lists = sugService.getListTransfer(sugId);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * 交办信息
	 * @param sugId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListAssign")
	@POST
	public String getListAssign(@FormParam("sugId") String sugId,
										  @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(sugId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		List<AssignForm> lists = sugService.getListAssign(sugId);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);


	}

	/**
	 * 承办信息
	 * @param sugId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListUnitAnswer")
	@POST
	public String getListUnitAnswer(@FormParam("sugId") String sugId,
												  @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(sugId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		List<UnitAnswerForm> lists = sugService.getListUnitAnswer(sugId);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);


	}

	/**
	 * 议案建议处理信息
	 * @param sugId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getSugProcessInfo")
	@POST
	public String getSugProcessInfo(@FormParam("sugId") String sugId,
											@FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(sugId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		SugProcessInfo theObj = sugService.getSugProcessInfo(sugId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
	}


	// ===== 以下是用PrintWriter将结果输出至客户端
	// ============================================
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/myHeadSugList")
	@POST
	public String myHeadSugList(@FormParam("loginName") String loginName,
							  @FormParam("pageNum") String pageNum,
							  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+pageNum+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}

		String jsonStr = null;
		PrintWriter out = null;

		int curPage = 1;
		if (pageNum != null) {
			try {
				curPage = Integer.parseInt(pageNum);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.myHeadSugList(loginName, curPage);

		if (sugList != null && sugList.size() > 0) {
			// System.out.println("size->" + sugList.size());
			jsonStr = GsonUtils.getJsonString(sugList);
		} else {
			jsonStr = "fail";
		}
		return JsonResultUtils.getObjectResultByStringAsDefault(jsonStr, JsonResultUtils.Code.SUCCESS);
	}

	/*
	 * 根据id查找Suggestion(我的领衔),
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/headsuginfo")
	@POST
	public String headSugInfo(@FormParam("sugId") String sugId,
							@FormParam("pageNum") String pageNum,
							@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(sugId+pageNum+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		String jsonStr = null;
		PrintWriter out = null;


		// System.out.println("sugId->" + id);
		Suggestion sug = sugService.getSuggestionById(sugId);

		if (sug != null) {

			// 获取联名人
			if (StringUtils.isNotEmpty(sug.getSecondWriterIDS())) {
				List<Users> list = sugService.getSecondWriter(sug
						.getSecondWriterIDS());
				String str = "";
				for (Users u : list) {
					str += u.getName() + ",";
				}
				/*
				 * model.addAttribute("secondWriter", str.substring(0,
				 * str.length() - 1));
				 */
				System.out.println("2nd writer->"
						+ str.substring(0, str.length() - 1));
				sug.setSecondWriter(str.substring(0, str.length() - 1));
			}

			if (StringUtils.isNotEmpty(sug.getStatus())) {
				String states = sugService.getStatus(sug.getStatus(),
						"sug_status");
				System.out.println("states->" + states);
				sug.setState(states);
			}

			/*
			 * if(StringUtils.isNotEmpty(sug.getContent())){ String content =
			 * (StringUtils.replaceHtml( StringUtils.toHtml(sug.getContent())));
			 * sug.setContent(content); System.out.println("content->" +
			 * content); }
			 */
			// model.addAttribute("sug", sug);
			// attr.addAttribute("sug", sug);

			jsonStr = GsonUtils.getJsonString(sug);
			System.out.println("1->" + jsonStr);

		} else {
			jsonStr = "fail";
		}

		return JsonResultUtils.getObjectResultByStringAsDefault(jsonStr, JsonResultUtils.Code.SUCCESS);

		// return "redirect:/myHeadSugList";
	}

	// ==============修改前的==================
	/**
	 * 查看本级优秀议案建议列表
	 *
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugListExcellent")
	@POST
	public String sugListExcellent(@FormParam("pageNum") String pageNum,
											 @FormParam("level_code") String level_code,
											 @FormParam("key") String key) {

		String keyWord = MD5Util.md5Encode(pageNum+level_code+ MD5Util.getDateStr() + secretKey);
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

		List<Suggestion> sugList = sugService.sugListExcellent(curPage,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(sugList, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * 优秀的 查看本级议案建议列表包含分页信息
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = { "sugListExcellentPage" })
	@ResponseBody
	public PagesUtil<Suggestion> sugListExcellentPageType(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// String loginName = request.getParameter("loginName");

		String pageNum = request.getParameter("curPage");
		// System.out.println("curPage->" + pageNum);

		String level_code = request.getParameter("level_code");

		String type_value = request.getParameter("type_value");

		int curPage = 1;
		if (pageNum != null) {
			try {
				curPage = Integer.parseInt(pageNum);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}


		 * List<Suggestion> sugList = sugService.sugListExcellent(curPage,
		 * level_code, request, response);

		PagesUtil<Suggestion> pagesUtil = sugService.sugListExcellentPage(
				curPage, level_code, type_value);

		return pagesUtil;
	}*/

	/**
	 * 重点的 查看本级议案建议
	 *
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugListEmphasis")
	@POST
	public String sugListEmphasis(@FormParam("pageNum") String pageNum,
											@FormParam("level_code") String level_code,
											@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+ MD5Util.getDateStr() + secretKey);
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

		List<Suggestion> sugList = sugService.sugListEmphasis(curPage,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(sugList, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * 根据代表团id查询本代表团所有的suggestion
	 *
	 * @param team_id
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugListOffice")
	@POST
	public String sugListOffice(@FormParam("pageNum") String pageNum,
										  @FormParam("level_code") String level_code,
										  @FormParam("team_id") String team_id,
										  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+team_id+ MD5Util.getDateStr() + secretKey);
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

		List<Suggestion> sugList = sugService.sugListOffice(curPage,
				level_code, team_id);
		return JsonResultUtils.getObjectResultByStringAsDefault(sugList, JsonResultUtils.Code.SUCCESS);

	}

	/**
	 * 所有代表 查看本级代表们议案建议
	 *
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/sugListAll")
	@POST
	public String sugListAll(@FormParam("pageNum") String pageNum,
									   @FormParam("level_code") String level_code,
									   @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(pageNum+level_code+ MD5Util.getDateStr() + secretKey);
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

		List<Suggestion> sugList = sugService.sugListAll(curPage, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(sugList, JsonResultUtils.Code.SUCCESS);

	}

	/**
	 * 根据登录名和系统等级分页查询我领衔的suggestion 的List
	 *
	 * @param loginName
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/mySugListHead")
	@POST
	public String mySugListHead(@FormParam("loginName") String loginName,
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
				curPage = Integer.parseInt(pageNum);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.mySugListHead(loginName, curPage,
				level_code);

		return JsonResultUtils.getObjectResultByStringAsDefault(sugList, JsonResultUtils.Code.SUCCESS);

	}

	/**
	 * 根据登录名和系统等级分页查询我联名的suggestion 的List
	 *
	 * @param loginName
	 * @param pageNum
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/mySugListJoin")
	@POST
	public String mySugListJoin(@FormParam("loginName") String loginName,
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
				curPage = Integer.parseInt(pageNum);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.mySugListJoin(loginName, curPage,
				level_code);

		return JsonResultUtils.getObjectResultByStringAsDefault(sugList, JsonResultUtils.Code.SUCCESS);

	}


	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/updateSug")
	@POST
	public String updateSug(@FormParam("sugId") String sugId,
								@FormParam("title") String title,
								@FormParam("secondWriterIDS") String secondWriterIDS,
								@FormParam("content") String content,
								@FormParam("keyWord") String keyWord,
								@FormParam("key") String key) {
		String keyW = MD5Util.md5Encode(sugId+title+secondWriterIDS+content+keyWord+ MD5Util.getDateStr() + secretKey);
		if (!keyW.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		if (sugId==null || sugId.equals("")){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数sugId不能为空!");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sugId",sugId);

		if (title!=null && !title.equals("")){
			map.put("title",title);
		}
		if (secondWriterIDS!=null && !secondWriterIDS.equals("")){
			map.put("secondWriterIDS",secondWriterIDS);
		}
		if (content!=null && !content.equals("")){
			map.put("content",content);
		}
		if (keyWord!=null && !keyWord.equals("")){
			map.put("keyWord",keyWord);
		}
		sugService.updateSug(map);
		return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "update success!");

	}

}

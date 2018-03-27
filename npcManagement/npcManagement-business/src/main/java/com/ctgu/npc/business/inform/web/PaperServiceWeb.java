package com.ctgu.npc.business.inform.web;

import com.ctgu.npc.business.basicInfo.service.NpcService;
import com.ctgu.npc.business.common.utils.DateUtils;
import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.inform.entity.*;
import com.ctgu.npc.business.inform.service.PaperService;
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
 * 问卷管理
 * @author 旺旺
 *
 */
@Component
@Path("/paper")
public class PaperServiceWeb {

	PlatformLogger logger = PlatformLogger.getLogger(PaperServiceWeb.class);

	@Autowired
	private PaperService paperService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private NpcService npcService;

	private static String secretKey = FundamentalConfigProvider.get("npc.key");
	/**
	 * 问卷统计
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-9 下午4:03:18
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/staticPaper")
	@POST
	public String staticPaper(@FormParam("theObjId") String theObjId,@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Paper paper = paperService.get(theObjId);
		List<PaperQues> qlist = paperService.findQuestionList(theObjId);
		List<PaperResult> rlist = paperService.findResultList(theObjId);
		Integer joinTotal = paperService.getJoinTotal(theObjId);
		PaperStatis theObj = new PaperStatis();
		theObj.setPaper(paper);
		theObj.setJoinTotal(joinTotal);
		theObj.setQlist(qlist);
		theObj.setRlist(rlist);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
		}

	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/submitPaper")
	@POST
	public String submitPaper( @FormParam("level_code") String level_code,@FormParam("theObjId") String theObjId,
										   @FormParam("loginName") String loginName,@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code+theObjId+loginName+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		String id = theObjId;
		String userId = userService.getUser(loginName).getId();
		Paper paper = paperService.get(id);
		List<PaperQues> qlist = paperService.findQuestionList(id);
		List<PaperQuesAns> alist = paperService.findAnswerList(id);
		return JsonResultUtils.getObjectResultByStringAsDefault(alist, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * 保存答卷
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-11 上午11:27:20
	 * @param loginName
	 * @param json_str
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/addsaveAnswer")
	@POST
	public String addsaveAnswer(@FormParam("loginName") String loginName,@FormParam("json_str") String json_str,
								@FormParam("level_code") String level_code,@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+json_str+level_code+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		String userId = userService.getUser(loginName).getId();
		Answer answer = new Answer();
		answer.setUserId(StringUtils.toInteger(userId));
		answer.setSubTime(DateUtils.getDateTime());
		answer.setLevel(level_code);
		if (json_str != null) {
			Gson gson = new Gson(); 
			 List<PaperQuesAns> theList = gson .fromJson(
					 json_str, 
					 new TypeToken<List<PaperQuesAns>>() {}.getType()
					 );
			if (theList != null && theList.size() > 0 ) {
				answer.setPaperId(theList.get(0).getPaperId());
				for (PaperQuesAns paperQuesAns : theList) 
				{
					answer.setQuestionId(paperQuesAns.getQuestionId());
					answer.setAnswer(paperQuesAns.getAnswer());
					paperService.addsaveAnswer(answer);
				}
				return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "save success!");
			}else{
				return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "save fail!");
			}
		} else {
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "save fail!");
		}
	}


	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/addsavePaper")
	@POST
	public String addsave(@FormParam("loginName") String loginName,@FormParam("json_str") String json_str,
						  @FormParam("level_code") String level_code,@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName+json_str+level_code+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Paper theObj;
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<Paper>() {
			}.getType();
			theObj = gson.fromJson(json_str, type);
			if(theObj != null){
				paperService.addsavePaper(theObj, loginName, level_code);
				return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.SUCCESS.getCode(), "save success!");
			}else{
				return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "save fail!");
				}
		} else {
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "save fail!");
		}
	}
	
	/**
	 * 根据系统级别查询问卷列表
	 * @param level_code
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListPaper")
	@POST
	public String getListPaper(@FormParam("level_code") String level_code,@FormParam("pageNum") String pageNum,@FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(level_code+pageNum+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		PagesUtil<Paper> pages;
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		pages = paperService.getListPaper(level_code,curPage);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * 问卷详细信息
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getDetailPaper")
	@POST
	public String getDetailPaper(@FormParam("theObjId") String theObjId,@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Paper paper = paperService.getDetailPaper(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(paper, JsonResultUtils.Code.SUCCESS);
	}
	
}

package com.ctgu.npc.business.inform.web;

import com.ctgu.npc.business.basicInfo.service.NpcService;
import com.ctgu.npc.business.common.utils.DateUtils;
import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.inform.entity.*;
import com.ctgu.npc.business.inform.service.TestService;
import com.ctgu.npc.business.sys.service.UserService;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 工作测评
 * Title:TestController 
 * Description: 
 * Company: ctgu 
 * @author : youngmien
 * @date  2016-9-20 上午10:37:42
 */
@Component
@Path("/test")
public class TestServiceWeb {
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private NpcService npcService;

	private static String secretKey = FundamentalConfigProvider.get("npc.key");
	
	/**
	 * 根据系统级别查询工作评议列表
	 * @param level_code
	 * @param pageNum
	 * @param loginName
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListDiscussion")
	@POST
	public String getListDiscussion(@FormParam("level_code") String level_code,
												   @FormParam("pageNum") String pageNum,
												   @FormParam("loginName") String loginName,
												   @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(level_code + pageNum + loginName+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		PagesUtil<Discussion> pages  = testService.getListDiscussion(level_code,curPage);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * 专项工作详细信息
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getDetailDiscussion")
	@POST
	public String getDetailDiscussion(@FormParam("theObjId") String theObjId,
										  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Discussion theObj = testService.getDetailDiscussion(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);

	}
	
	
	/**
	 * 代表的测评详细结果
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 上午11:40:25
	 * @param level_code
	 * @param theObjId
	 * @param loginName
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getDisTestResult")
	@POST
	public String getDisTestResult(@FormParam("level_code") String level_code,
										  @FormParam("theObjId") String theObjId,
										  @FormParam("loginName") String loginName,
										  @FormParam("key") String key) {

		String keyWord = MD5Util.md5Encode(level_code + theObjId + loginName+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		String userId = userService.getUser(loginName).getId();
		Choice choice = testService.getChoice(level_code,theObjId,userId);
		Discussion dis = testService.getDetailDiscussion(theObjId);//测试表
		List<DisTest> dlist = testService.findListDisTest(theObjId);//本测试的测评单位
		DisTestChoice dtc = testService.getDisTestChoice(theObjId,userId);
		List<DisTestChoice> clist = testService.findListDisTestChoice(dtc);
		DisTestResult theObj = new DisTestResult();
		if(dis.getStatus().equals("2")){//已经测评过了
			if(testService.isSubmit(choice)){//未提交
				if(clist != null && clist.size() > 0){
					//theObj.setClist(clist);
				}
			}else{//已经提交
				if(dlist != null && dlist.size() > 0){
					//theObj.setDlist(dlist);
				}
			}
		}
		else//还未测评 
		{
			if(testService.isAnswerUser(theObjId,level_code,userId)){//已经答过了
				if(clist != null && clist.size() > 0){
					//theObj.setClist(clist);
				}
			}else{//还未答题
				if(dlist != null && dlist.size() > 0){
					//theObj.setDlist(dlist);
				}
			}
		}
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);


	}
	
	/**
	 * 代表测评答题详细
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午4:10:23
	 * @param level_code
	 * @param theObjId
	 * @param loginName
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/npcreplyDetail")
	@POST
	public String npcreplyDetail(@FormParam("level_code") String level_code,
											  @FormParam("theObjId") String theObjId,
											  @FormParam("loginName") String loginName,
											  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + theObjId + loginName+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		//String userId = userService.getUser(loginName).getId();
		String npcId =  npcService.getBasicInfo(loginName, level_code).getId();
		
		Discussion dis = testService.getDetailDiscussion(theObjId);
		List<DisTestChoice> dtclist = testService.findListDisTestChoiceById(theObjId,npcId);
		return JsonResultUtils.getObjectResultByStringAsDefault(dtclist, JsonResultUtils.Code.SUCCESS);


	}
	
	/**
	 * 代表测评未提交
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-24 上午10:04:05
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/npcTesDetail")
	@POST
	public String npcTesDetail(@FormParam("theObjId") String theObjId,
									  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode( theObjId + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		List<DisTest> dtclist = testService.findListDisTest(theObjId);//本测试的测评单位
		return JsonResultUtils.getObjectResultByStringAsDefault(dtclist, JsonResultUtils.Code.SUCCESS);


	}
	
	/**
	 * 
	 * Description:测评统计 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-23 上午10:32:52
	 * @param theObjId
	 * @param loginName
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/staticDis")
	@POST
	public String staticDis( @FormParam("theObjId") String theObjId,
									@FormParam("loginName") String loginName,
									@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId  + loginName+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		String userId = userService.getUser(loginName).getId();
		
		Discussion dis = testService.getDetailDiscussion(theObjId);

		List<DisTest> dlist = testService.findListDisTest(theObjId);
		
		List<DisResult> rlist = testService.findListDisResult(theObjId);
		
		//List<DisChoice> clist = testService.findRemarkList(id);
		Integer joinTotal = testService.getJoinTotal(theObjId);
		
		DisTestResult theObj = new DisTestResult();

		theObj.setJoinTotal(joinTotal);
		
		if(dlist != null && dlist.size() > 0){
			theObj.setTitle(dlist.get(0).getTitle());
			theObj.setDescription(dlist.get(0).getDescription());
		}
		
		List<DisResultStastic> list = new ArrayList<DisResultStastic>();
		
		for(int i = 0; i < dlist.size(); i++){
			
			DisResultStastic drs = new DisResultStastic();
			drs.setUnit(dlist.get(i).getUnit());
			int testId = dlist.get(i).getTestId();
			drs.setTestId(testId);
			drs.setJoinTotal(joinTotal);
			
			for (DisResult dr: rlist){
				if(dr.getTestId() == testId){
					if("a".equals(dr.getChoice())){
						drs.setChooseNumA(dr.getChooseNum());
					}else if("b".equals(dr.getChoice())){
						drs.setChooseNumB(dr.getChooseNum());
					}else if("c".equals(dr.getChoice())){
						drs.setChooseNumC(dr.getChooseNum());
					}
				}
			}			
			list.add(drs);
		}
		
		theObj.setList(list);

		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);


	}
	
	/**
	 * 提交测评
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-23 上午10:33:14
	 * @param level_code
	 * @param theObjId
	 * @param loginName
	 * @param json_str
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/submitDis")
	@POST
	public String submitDis(@FormParam("level_code") String level_code,
			                @FormParam("theObjId") String theObjId,
							@FormParam("loginName") String loginName,
							@FormParam("json_str") String json_str,
							@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + theObjId + loginName+ json_str+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		String userId = userService.getUser(loginName).getId();
		if (json_str != null) {
			/*Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<Suggestion>() {
			}.getType();
			Suggestion sug = (Suggestion) gson.fromJson(json_str, type);*/
			
			 Gson gson = new Gson(); 
			 List<Choice> theList = (List<Choice>) gson .fromJson(
					  json_str, 
					  new TypeToken<List<Choice>>(){}.getType()
					  );
		
		/*String q[] = request.getParameterValues("testId");
		String a[] = new String[q.length]; 
		String r[] = request.getParameterValues("remark");
		for(int i = 0 ; i<q.length;i++){
			 a[i] = request.getParameter("answer"+i);
		}*/
		//String disid = request.getParameter("disId");
		
		//Choice choice = new Choice();
		for(Choice choice:theList){
			choice.setNpcId(StringUtils.toInteger(userId));
			choice.setSubTime(DateUtils.getDateTime());
			choice.setLevel(level_code);
			choice.setDisId(StringUtils.toInteger(theObjId));
			testService.saveSubmitChoice(choice);
		}
		/*for(int i = 0 ; i<q.length;i++){
			choice.setTestId(Integer.parseInt(q[i]));
			choice.setChoice(a[i]);
			choice.setRemark(r[i]);
			testService.saveSubmitChoice(choice);
		}*/

		return "success!";
	
	
		//addMessage(redirectAttributes, "保存'" + "'成功");
		} else {
			return "fail!";
		}
		
	}
	
}

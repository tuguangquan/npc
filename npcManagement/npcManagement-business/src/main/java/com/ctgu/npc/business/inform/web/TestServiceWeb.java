package com.ctgu.npc.business.inform.web;

import com.ctgu.npc.business.basicInfo.service.NpcService;
import com.ctgu.npc.business.common.utils.DateUtils;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.inform.entity.*;
import com.ctgu.npc.business.inform.service.TestService;
import com.ctgu.npc.business.sys.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
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
	
	
	
	/**
	 * 根据系统级别查询工作评议列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="getListDiscussion")
	@ResponseBody
	public PagesUtil<Discussion> getListDiscussion(HttpServletRequest request, HttpServletResponse response, Model model){
		PagesUtil<Discussion> pages = new PagesUtil<Discussion>();
		
		String level_code = request.getParameter("level_code");
		String pageNum = request.getParameter("curPage");
		String loginName = request.getParameter("loginName");
		
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		pages = testService.getListDiscussion(level_code,curPage);
		return pages;
		
	}
	
	/**
	 * 专项工作详细信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getDetailDiscussion" })
	@ResponseBody
	public Discussion getDetailDiscussion(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		Discussion theObj = testService.getDetailDiscussion(id);

		return theObj;

	}
	
	
	/**
	 * 代表的测评详细结果
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 上午11:40:25
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getDisTestResult")
	@ResponseBody
	public DisTestResult getDisTestResult(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		String level = request.getParameter("level_code");
		String id = request.getParameter("theObjId");
		String loginName = request.getParameter("loginName");
		String userId = userService.getUser(loginName).getId();
		
		Choice choice = testService.getChoice(level,id,userId);
		
		Discussion dis = testService.getDetailDiscussion(id);//测试表
		
		List<DisTest> dlist = testService.findListDisTest(id);//本测试的测评单位
		
		DisTestChoice dtc = testService.getDisTestChoice(id,userId);
		
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
			if(testService.isAnswerUser(id,level,userId)){//已经答过了
				if(clist != null && clist.size() > 0){
					//theObj.setClist(clist);
				}
			}else{//还未答题
				if(dlist != null && dlist.size() > 0){
					//theObj.setDlist(dlist);
				}
			}
			
		}
		
		return theObj;
		
	}
	
	/**
	 * 代表测评答题详细
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午4:10:23
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "npcreplyDetail")
	@ResponseBody
	public List<DisTestChoice> npcreplyDetail( Model model,HttpServletRequest request ,HttpServletResponse response) {
		
		String level = request.getParameter("level_code");
		String id = request.getParameter("theObjId");
		String loginName = request.getParameter("loginName");
		//String userId = userService.getUser(loginName).getId();
		String npcId =  npcService.getBasicInfo(loginName, level).getId();
		
		Discussion dis = testService.getDetailDiscussion(id);
		List<DisTestChoice> dtclist = testService.findListDisTestChoiceById(id,npcId);
		
		return dtclist;
		
	}
	
	/**
	 * 代表测评未提交
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-24 上午10:04:05
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "npcTesDetail")
	@ResponseBody
	public List<DisTest> npcTesDetail( Model model,HttpServletRequest request ,HttpServletResponse response) {
		
		String level = request.getParameter("level_code");
		String id = request.getParameter("theObjId");
		String loginName = request.getParameter("loginName");
		//String userId = userService.getUser(loginName).getId();
		
		Discussion dis = testService.getDetailDiscussion(id);
		List<DisTest> dtclist = testService.findListDisTest(id);//本测试的测评单位
		
		return dtclist;
		
	}
	
	/**
	 * 
	 * Description:测评统计 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-23 上午10:32:52
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "staticDis")
	@ResponseBody
	public DisTestResult staticDis( Model model,HttpServletRequest request ,HttpServletResponse response) {
		String level = request.getParameter("level_code");
		String id = request.getParameter("theObjId");
		String loginName = request.getParameter("loginName");
		String userId = userService.getUser(loginName).getId();
		
		Discussion dis = testService.getDetailDiscussion(id);

		List<DisTest> dlist = testService.findListDisTest(id);
		
		List<DisResult> rlist = testService.findListDisResult(id);
		
		//List<DisChoice> clist = testService.findRemarkList(id);
		Integer joinTotal = testService.getJoinTotal(id);
		
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
		
		return theObj;
		
		}
	
	/**
	 * 提交测评
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-23 上午10:33:14
	 * @param choice
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = {"submitDis"})
	public String submitDis( Model model,HttpServletRequest request ,HttpServletResponse response) {
		String level = request.getParameter("level_code");
		String disid = request.getParameter("theObjId");
		String loginName = request.getParameter("loginName");
		String userId = userService.getUser(loginName).getId();
		String json_str = request.getParameter("json_str");
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
			choice.setLevel(level);
			choice.setDisId(StringUtils.toInteger(disid));
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

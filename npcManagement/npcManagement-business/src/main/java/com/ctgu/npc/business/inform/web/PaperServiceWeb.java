package com.ctgu.npc.business.inform.web;

import com.ctgu.npc.business.basicInfo.service.NpcService;
import com.ctgu.npc.business.common.utils.DateUtils;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.inform.entity.*;
import com.ctgu.npc.business.inform.service.PaperService;
import com.ctgu.npc.business.sys.service.UserService;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
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
	
	@Autowired
	private PaperService paperService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private NpcService npcService;
	
	/**
	 * 问卷统计
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-9 下午4:03:18
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/staticPaper")
	@POST
	public String staticPaper(@FormParam("theObjId") String theObjId) {
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
	
	
	@RequestMapping(value = "submitPaper")
	@ResponseBody
	public List<PaperQuesAns> submitPaper( HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String level = request.getParameter("level_code");
		String id = request.getParameter("theObjId");
		String loginName = request.getParameter("loginName");
		String userId = userService.getUser(loginName).getId();
		
		Paper paper = paperService.get(id);
		//model.addAttribute("paper",paper);
		List<PaperQues> qlist = paperService.findQuestionList(id);		
		//PaperQuesAns pqa = paperService.getPaperQuesAns(id);		
		List<PaperQuesAns> alist = paperService.findAnswerList(id);
			
		return alist;
		
		}

	/**
	 * 保存答卷
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-11 上午11:27:20
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"addsaveAnswer"})
	@ResponseBody
	public String addsaveAnswer(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		String loginName = request.getParameter("loginName");
		
		String json_str = request.getParameter("json_str");
		//System.out.println(" ->" + json_str);
		
		String level_code = request.getParameter("level_code");
		
		String userId = userService.getUser(loginName).getId();
		Answer answer = new Answer();
		answer.setUserId(StringUtils.toInteger(userId));
		answer.setSubTime(DateUtils.getDateTime());
		answer.setLevel(level_code);
		
		if (json_str != null) {
			Gson gson = new Gson(); 
			 List<PaperQuesAns> theList = (List<PaperQuesAns>) gson .fromJson(
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
				
				return "save success!";
			}else{
				return "save fail!";
			}
		} else {
			return "save fail!";
		}
	}
	
	@RequestMapping(value = {"addsavePaper"})
	@ResponseBody
	public String addsave(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		String loginName = request.getParameter("loginName");

		String json_str = request.getParameter("json_str");
		//System.out.println(" ->" + json_str);
		
		String level_code = request.getParameter("level_code");

		Paper theObj = null;
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<Paper>() {
			}.getType();
			theObj = (Paper) gson.fromJson(json_str, type);
			if(theObj != null){
				//System.out.println("->theObj" + theObj.toString() );
				paperService.addsavePaper(theObj, loginName, level_code);
				return "save success!";
			}else{
				return "save fail!";
				}
		} else {
			return "save fail!";
		}
	}
	
	/**
	 * 根据系统级别查询问卷列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="getListPaper")
	@ResponseBody
	public PagesUtil<Paper> getListPaper(HttpServletRequest request, HttpServletResponse response, Model model){
		PagesUtil<Paper> pages = new PagesUtil<Paper>();
		
		String level_code = request.getParameter("level_code");
		String pageNum = request.getParameter("curPage");
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		pages = paperService.getListPaper(level_code,curPage);
		return pages;
		
	}
	
	/**
	 * 问卷详细信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getDetailPaper" })
	@ResponseBody
	public Paper getDetailPaper(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		Paper theObj = paperService.getDetailPaper(id);

		return theObj;

	}
	
}

package com.ctgu.npc.business.inform.web;

import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.inform.entity.Opinion;
import com.ctgu.npc.business.inform.entity.OpinionReport;
import com.ctgu.npc.business.inform.entity.Report;
import com.ctgu.npc.business.inform.service.OpinionService;
import com.ctgu.npc.business.sys.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 意见管理
 * @author 旺旺
 *
 */

@Controller
public class OpinionServiceWeb {
	
	@Autowired
	private OpinionService opinionService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = {"addsaveOpinion"})
	@ResponseBody
	public String addsaveOpinion( HttpServletRequest request, HttpServletResponse response, Model model)  {
		String level = request.getParameter("level_code");
		String disid = request.getParameter("theObjId");
		String loginName = request.getParameter("loginName");
		String userId = userService.getUser(loginName).getId();
		
		String json_str = request.getParameter("json_str");
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<OpinionReport>() {}.getType();
			OpinionReport theObj = (OpinionReport) gson.fromJson(json_str, type);
			
			/* Gson gson = new Gson(); 
			 List<Choice> theList = (List<Choice>) gson .fromJson(
					  json_str, 
					  new TypeToken<List<Choice>>(){}.getType()
					  );*/
			 
			
			Opinion opinion = theObj.getOpinion();
			Report report = theObj.getReport();
			opinionService.addsaveOpinion(opinion,report,userId,level);
			return "true";
		}
		//addMessage(redirectAttributes, "保存'" + "'成功");
		return "false";
	}
	
	
	/**
	 * 报告列表
	 * 根据系统级别分页查询代表报告列表
	 */
	@RequestMapping(value = {"getListReport"})
	@ResponseBody
	public List<Report> getListReport(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Report> lists = new ArrayList<Report>();
		
		String level_code = request.getParameter("level_code");
		String pageNum = request.getParameter("curPage");
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		lists = opinionService.getListReport(level_code,curPage);
		return lists;
	}
	
	
	/**
	 * 报告列表
	 * 根据系统级别分页查询代表报告列表包含分页信息
	 */
	@RequestMapping(value = {"getListReportPage"})
	@ResponseBody
	public PagesUtil<Report> getListReportPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		PagesUtil<Report> lists = new PagesUtil<Report>();
		
		String level_code = request.getParameter("level_code");
		String pageNum = request.getParameter("curPage");
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		lists = opinionService.getListReportPage(level_code,curPage);
		return lists;
	}
	
	/**
	 * 根据Report的id查询详细
	 */
	@RequestMapping(value = {"getDetailByIdReport"})
	@ResponseBody
	public Report getDetailByIdReport(HttpServletRequest request, HttpServletResponse response, Model model) {
		Report repot = new Report();
		
		String repot_id = request.getParameter("theObjId");
		
		repot = opinionService.getDetailByIdReport(repot_id);
		return repot;
	}
	
	
	
	
	/**
	 * 根据系统级别分页查询意见列表
	 */
	@RequestMapping(value = {"getListOpinion"})
	@ResponseBody
	public List<Opinion> getListOpinion(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Opinion> lists = new ArrayList<Opinion>();
		
		String level_code = request.getParameter("level_code");
		String pageNum = request.getParameter("curPage");
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		lists = opinionService.getListOpinion(level_code,curPage);
		return lists;
	}
	
	/**
	 * 根据opinion的id查询详细
	 */
	@RequestMapping(value = {"getDetailByIdOpinion"})
	@ResponseBody
	public Opinion getDetailByIdOpinion(HttpServletRequest request, HttpServletResponse response, Model model) {
		Opinion opin = new Opinion();
		
		String opin_id = request.getParameter("theObjId");
		
		opin = opinionService.getDetailByIdOpinion(opin_id);
		return opin;
	}

}

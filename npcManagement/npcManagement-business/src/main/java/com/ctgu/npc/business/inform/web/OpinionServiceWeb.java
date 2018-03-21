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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * 意见管理
 * @author 旺旺
 *
 */

@Component
@Path("/opinion")
public class OpinionServiceWeb {
	
	@Autowired
	private OpinionService opinionService;
	
	@Autowired
	private UserService userService;

	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/addsaveOpinion")
	@POST
	public String addsaveOpinion(@FormParam("level_code") String level_code,@FormParam("theObjId") String theObjId
	,@FormParam("loginName") String loginName,@FormParam("json_str") String json_str)  {
		String userId = userService.getUser(loginName).getId();
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<OpinionReport>() {}.getType();
			OpinionReport theObj = (OpinionReport) gson.fromJson(json_str, type);
			Opinion opinion = theObj.getOpinion();
			Report report = theObj.getReport();
			opinionService.addsaveOpinion(opinion,report,userId,level_code);
			return "true";
		}
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

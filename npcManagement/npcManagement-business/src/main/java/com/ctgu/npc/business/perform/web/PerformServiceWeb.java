package com.ctgu.npc.business.perform.web;


import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.perform.entity.*;
import com.ctgu.npc.business.perform.service.PerformService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 履职服务
 * 
 * @author 旺旺
 * 
 */

@Controller
public class PerformServiceWeb {
	
	private final String TAG = "PerformController";

	@Autowired
	private PerformService performService;
	
	/**
	 * === 我的代表工作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "myJobReportPage" })
	@ResponseBody
	public PagesUtil<JobReport> myJobReportPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<JobReport> pages = performService.myJobReportPage(
				loginName, curPage, level_code);
		return pages;
	}


	/**
	 * === 提交我的代表工作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = { "saveMyPerformJob" })
	@ResponseBody
	public String addMyPerformJob(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String loginName = request.getParameter("loginName");

		String json_str = request.getParameter("json_str");
		//System.out.println(TAG + " ->" + json_str);
		
		String level_code = request.getParameter("level_code");

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
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = { "saveMyPerformAct" })
	@ResponseBody
	public String saveMyPerformAct(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String loginName = request.getParameter("loginName");

		String json_str = request.getParameter("json_str");
		//System.out.println(loginName + "->" + json_str);
		
		String level_code = request.getParameter("level_code");

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
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "myPerformActPage" })
	@ResponseBody
	public PagesUtil<PerformReport> myPerformActPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<PerformReport> pages = performService.myPerformActPage(
				loginName, curPage, level_code);
		return pages;
	}

	/**
	 * ===我的代表活动--详细信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoMyPerformAct" })
	@ResponseBody
	public PerformReport getInfoMyPerformAct(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		PerformReport performReport = performService.getInfoPerformReport(id);

		return performReport;

	}

	/**
	 * ===我的履职报告
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "myWorkReportPage" })
	@ResponseBody
	public PagesUtil<WorkReport> myWorkReportPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<WorkReport> pages = performService.myWorkReportPage(
				loginName, curPage, level_code);
		return pages;
	}

	/**
	 * === 我的履职报告详细
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoMyWorkReport" })
	@ResponseBody
	public WorkReport getInfoMyWorkReport(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		WorkReport theObj = performService.getInfoMyWorkReport(id);

		return theObj;

	}
	
	/**
	 * === 我的履职统计
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "myReportStatisticList" })
	@ResponseBody
	public List<MyReportEntry> myReportStatisticList(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		String loginName = request.getParameter("loginName");

		//String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		/*if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}*/

		List<MyReportEntry> pages = performService.myReportStatisticList(
				loginName,  level_code);
		return pages;
	}

	/**
	 * === 获取登记类型列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "actTypeList" })
	@ResponseBody
	public List<ActType> actTypeList(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		String loginName = request.getParameter("loginName");

		//String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		/*if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}*/

		List<ActType> lists = performService.actTypeList(
				loginName,  level_code);
		return lists;
	}


	/**
	 * 代表工作查询
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "jobSearchPage" })
	@ResponseBody
	public PagesUtil<JobReport> jobSearchPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<JobReport> pages = performService.jobSearchPage(curPage,
				level_code);
		return pages;
	}

	/**
	 * ===代表工作查询详细===
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoJobReport" })
	@ResponseBody
	public JobReport getInfoJobReport(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		JobReport theObj = performService.getInfojobSearchDetail(id);

		return theObj;
	}
	
	/**
	 * === 代表活动查询
	 */
	@RequestMapping(value = { "actSearchPage" })
	@ResponseBody
	public PagesUtil<PerformReport> actSearchPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<PerformReport> pages = performService.actSearchPage(curPage,
				level_code);
		return pages;
	}
	
	
	/**
	 * === 履职报告查询
	 */
	@RequestMapping(value = { "workSearchPage" })
	@ResponseBody
	public PagesUtil<WorkReport> workSearchPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<WorkReport> pages = performService.workSearchPage(curPage,
				level_code);
		return pages;
	}
	
	/**
	 * === 履职报告详细
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoworkSearchDetail" })
	@ResponseBody
	public WorkReport getInfoworkSearchDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		WorkReport theObj = performService.getInfoworkSearchDetail(id);

		return theObj;
	}
	
}

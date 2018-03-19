package com.ctgu.npc.business.learning.web;

import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.learning.entity.*;
import com.ctgu.npc.business.learning.service.LearningService;
import com.ctgu.npc.business.sug.service.SugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 学习交流
 * @author 旺旺
 *
 */
@Controller
public class LearningServiceWeb {
	
	
	@Autowired
	private LearningService learningService;
	
	@Autowired
	private SugService sugService;
	
	
	/**
	 * 人大规章制度
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "listPageNpcRule" })
	@ResponseBody
	public PagesUtil<Rule> listPageNpcRule(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");
		
		String typeValue = request.getParameter("typeValue");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = (int) StringUtils.toInteger(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<Rule> pages = learningService.listPageNpcRule(
				loginName, curPage, level_code,typeValue);
		return pages;
	}
	/**
	 * 规章制度详细
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoRule" })
	@ResponseBody
	public Rule getInfoRule(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		Rule theObj = learningService.getInfoRule(id);

		return theObj;

	}
	
	/**
	 * 履职学习培训资料分页列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = { "listPageMaterial" })
	@ResponseBody
	public PagesUtil<Material> listPageMaterial(
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

		PagesUtil<Material> pages = learningService.listPageMaterial(
				loginName, curPage, level_code);
		return pages;
	}

	/**
	 * 履职学习培训资料详细
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoMaterial" })
	@ResponseBody
	public Material getInfoMaterial(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		Material theObj = learningService.getInfoMaterial(id);

		return theObj;

	}
	
	
	
	/**
	 * 履职活动培训信息列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "listPageTraining" })
	@ResponseBody
	public PagesUtil<Training> listPageTraining(
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

		PagesUtil<Training> pages = learningService.listPageTraining(
				loginName, curPage, level_code);
		return pages;
	}
	
	/**
	 * 履职活动培训详细
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoTraining" })
	@ResponseBody
	public Training getInfoTraining(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		Training theObj = learningService.getInfoTraining(id);

		return theObj;

	}
	
	
	/**
	 * === 优秀建议议案
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "listPageExceSuggestion" })
	@ResponseBody
	public PagesUtil<ExceSuggestion> listPageExceSuggestion(
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

		PagesUtil<ExceSuggestion> pages = learningService.listPageExceSuggestion(
				loginName, curPage, level_code);
		return pages;
	}
	/**
	 * 优秀建议议案详细
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoExceSuggestion" })
	@ResponseBody
	public ExceSuggestion getInfoExceSuggestion(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		ExceSuggestion theObj = learningService.getInfoExceSuggestion(id);

		return theObj;

	}
	
	
	/**
	 * 优秀履职报告分页列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "listPageExceWorkReport" })
	@ResponseBody
	public PagesUtil<ExceWorkReport> listPageExceWorkReport(
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

		PagesUtil<ExceWorkReport> pages = learningService.listPageExceWorkReport(
				loginName, curPage, level_code);
		return pages;
	}
	
	/**
	 * === 优秀履职报告详细
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoExceWorkReport" })
	@ResponseBody
	public ExceWorkReport getInfoExceWorkReport(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		ExceWorkReport theObj = learningService.getInfoExceWorkReport(id);

		return theObj;

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

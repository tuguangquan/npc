package com.ctgu.npc.business.contact.web;


import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.contact.entity.*;
import com.ctgu.npc.business.contact.service.ContactService;
import com.ctgu.npc.business.contact.service.SqmyService;
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
import java.util.List;

/**
 * 联系互动 == 模块
 * 
 * @author 旺旺
 * 
 */
@Component
@Path("/contact")
public class ContactServiceWeb {

	private Integer everyPageNumber = 29;
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private SqmyService sqmyService;
	
	
	/**
	 * 我的社情民意列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-26 上午10:15:47
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "mySqmyList" })
	@ResponseBody
	public List<Sqmy> mySqmyList(HttpServletRequest request,
			HttpServletResponse response){
		String loginName = request.getParameter("loginName");

		String level_code = request.getParameter("level_code");
		
		List<Sqmy>  aList = sqmyService.getMySqmyList(loginName,level_code);
		
		return aList;
	}
	
	/**
	 * 社情民意详细
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-26 上午10:26:54
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "SqmyDetail" })
	@ResponseBody
	public SqmyDetail getSqmyDetail(HttpServletRequest request,
			HttpServletResponse response){
		
		String id = request.getParameter("theObjId");
		
		SqmyDetail theObj = new SqmyDetail();
		
		Sqmy sqmy = sqmyService.get(id);
		theObj.setSqmy(sqmy);

		LeaveMessage leaveMessage = sqmyService.getLeaveMessage(sqmy.getQzmsgID());
		theObj.setLeaveMessage(leaveMessage);

		List<AnswerSqmy> alist = sqmyService.getAnswerSqmy(sqmy.getId());
		theObj.setaList(alist);
		
		return theObj;
	}

	/**
	 * ===我的留言分页列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "myLeaveWordPage" })
	@ResponseBody
	public PagesUtil<LeaveWord> myLeaveWordPage(HttpServletRequest request,
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

		PagesUtil<LeaveWord> pages = contactService.myLeaveWordPage(loginName,
				curPage, level_code);
		return pages;
	}
	
	/**
	 * === 留言详细
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "getInfoLeaveWord" })
	@ResponseBody
	public LeaveWord getInfoLeaveWord(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		LeaveWord theObj = contactService.getInfoLeaveWord(id);

		return theObj;

	}
	
	/**
	 * 回复留言
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "replyLeaveWord" })
	@ResponseBody
	public String replyLeaveWord(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String result = "false";
		String loginName = request.getParameter("loginName");

		String json_str = request.getParameter("json_str");
		
		String level_code = request.getParameter("level_code");

		LeaveWord theObj = new LeaveWord();
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<LeaveWord>() {
			}.getType();
			theObj = (LeaveWord) gson.fromJson(json_str, type);
			 
			result = contactService.replyLeaveWord(theObj, loginName, level_code);
			
		} 
		System.out.println("return result->" + result);
		return result;
	}

}

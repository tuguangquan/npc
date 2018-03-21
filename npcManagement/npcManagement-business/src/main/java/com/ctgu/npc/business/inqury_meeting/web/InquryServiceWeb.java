package com.ctgu.npc.business.inqury_meeting.web;

import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.inqury_meeting.entity.Inqury;
import com.ctgu.npc.business.inqury_meeting.entity.Meet;
import com.ctgu.npc.business.inqury_meeting.service.InquryService;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
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
 * 询问列表
 * @author 旺旺
 *
 */
@Component
@Path("/inquery")
public class InquryServiceWeb {

	@Autowired
	InquryService inquryService;
	
	
	/**
	 * 获取询问列表-我的 包含分页信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListMyInquryPage")
	@POST
	public String getListMyInquryPage(@FormParam("level_code") String level_code,
												 @FormParam("loginName") String loginName,
												 @FormParam("pageNum") String pageNum) {
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		PagesUtil<Inqury> lists = inquryService.getListMyInquryPage(loginName,curPage,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}
	
	
	
	/**
	 * 根据inqury的id查询详细信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getDetailByIdInqury"})
	@ResponseBody
	public Inqury getDetailByIdInqury(HttpServletRequest request, HttpServletResponse response, Model model){
		
		String inq_id = request.getParameter("inq_id");
		
		Inqury inqury = new Inqury();
		inqury = inquryService.getDetailByIdInqury(inq_id);
		
		return inqury;
	}

	/**
	 * 获取询问列表-我的
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getListMyInqury"})
	@ResponseBody
	public List<Inqury> getListMyInqury(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Inqury> lists = new ArrayList<Inqury>();
		
		String loginName = request.getParameter("loginName");
		String pageNum = request.getParameter("curPage");
		String level_code = request.getParameter("level_code");
		
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		lists = inquryService.getListMyInqury(loginName,curPage,level_code);
		
		return lists;
		
	}
	
	
	/**
	 * ===询问 处理列表 ==人大工作人员
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getListInquryHandlePage"})
	@ResponseBody
	public PagesUtil<Inqury> getListInquryHandlePage(HttpServletRequest request, HttpServletResponse response, Model model) {
		PagesUtil<Inqury> lists = new PagesUtil<Inqury>();
		
		String loginName = request.getParameter("loginName");
		String pageNum = request.getParameter("curPage");
		String level_code = request.getParameter("level_code");
		
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		lists = inquryService.getListInquryHandlePage(loginName,curPage,level_code);
		
		return lists;
		
	}
	
	/**
	 *=== 答复询问====
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getListInquryReplyPage"})
	@ResponseBody
	public PagesUtil<Inqury> getListInquryReplyPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		PagesUtil<Inqury> lists = new PagesUtil<Inqury>();
		
		String loginName = request.getParameter("loginName");
		String pageNum = request.getParameter("curPage");
		String level_code = request.getParameter("level_code");
		
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		lists = inquryService.getListInquryReplyPage(loginName,curPage,level_code);
		
		return lists;
		
	}
	
	
	/**
	 * === 我的约见列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getListMyMeetPage"})
	@ResponseBody
	public PagesUtil<Meet> getListMyMeetPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		PagesUtil<Meet> lists = new PagesUtil<Meet>();
		
		String loginName = request.getParameter("loginName");
		String pageNum = request.getParameter("curPage");
		String level_code = request.getParameter("level_code");
		
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		lists = inquryService.getListMyMeetPage(loginName,curPage,level_code);
		
		return lists;
		
	}
	
	/**
	 * === 约见详细信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getDetailByIdMeet"})
	@ResponseBody
	public Meet getDetailByIdMeet(HttpServletRequest request, HttpServletResponse response, Model model){
		
		String theObjId = request.getParameter("theObjId");
		
		Meet theObj = new Meet();
		theObj = inquryService.getDetailByIdMeet(theObjId);
		
		return theObj;
	}
	
	
	/**
	 * ===约见处理列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"getListMeetHandlePage"})
	@ResponseBody
	public PagesUtil<Meet> getListMeetHandlePage(HttpServletRequest request, HttpServletResponse response, Model model) {
		PagesUtil<Meet> lists = new PagesUtil<Meet>();
		
		String loginName = request.getParameter("loginName");
		String pageNum = request.getParameter("curPage");
		String level_code = request.getParameter("level_code");
		
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		lists = inquryService.getListMeetHandlePage(loginName,curPage,level_code);
		
		return lists;
		
	}
	
}

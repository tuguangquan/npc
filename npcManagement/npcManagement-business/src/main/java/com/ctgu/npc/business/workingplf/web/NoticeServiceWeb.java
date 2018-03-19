package com.ctgu.npc.business.workingplf.web;

import com.ctgu.npc.business.common.persistence.Page;
import com.ctgu.npc.business.common.utils.BisUtils;
import com.ctgu.npc.business.workingplf.entity.ReceivedMessage;
import com.ctgu.npc.business.workingplf.service.NoticeService;
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
 * 通知 Title:NoticeController Description: Company: ctgu
 * 
 * @author : youngmien
 * @date 2017-7-3 上午10:16:24
 */
@Controller
public class NoticeServiceWeb {
	@Autowired
	private NoticeService noticeService;

	/**
	 * 我收到的通知列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-4 下午5:21:20
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "receive")
	@ResponseBody
	public List<ReceivedMessage> receivedNotice(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String loginName = request.getParameter("loginName");
		//String pageNum = request.getParameter("curPage");
		String level_code = request.getParameter("level_code");
		
		int curPage = 1;
		/*if (pageNum != null) {
			try {
				curPage = (int) StringUtils.toInteger(pageNum);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}*/

		List<ReceivedMessage> pages = noticeService.findMimeMessage(new Page<ReceivedMessage>(request, response),
				loginName,level_code,curPage);
		return pages;
	}
	
	
	/**
	 * 我收到的通知详细信息
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-4 下午5:26:36
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "findMimeMessageById" })
	@ResponseBody
	public ReceivedMessage getInfoReceivedMessage(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String id = request.getParameter("theObjId");

		ReceivedMessage theObj = noticeService.findMimeMessageById(id);

		return theObj;

	}
	
	
	/**
	 * 通知反馈
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-5 上午8:23:02
	 * @param receivedMessage
	 * @param model
	 * @return
	 */
    @RequestMapping(value="feedback")
    @ResponseBody
    public String feedback(HttpServletRequest request,
			HttpServletResponse response, Model model){
    	
    	String loginName = request.getParameter("loginName");
		//String pageNum = request.getParameter("curPage");
		String level_code = request.getParameter("level_code");
		
		String json_str = request.getParameter("json_str");
		//System.out.println(" ->" + json_str);

		ReceivedMessage theObj = null;
		  boolean bool = false;
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<ReceivedMessage>() {}.getType();
			theObj = (ReceivedMessage) gson.fromJson(json_str, type);
			if(theObj != null){
				//System.out.println("theObj->" + theObj.getIsAttend() );
				 //更新阅读时间和阅读标志
		        String readTime = BisUtils.getCurrentTime("yyyy-MM-dd HH:mm:ss");
		        theObj.setReadTime(readTime);
		        
		      bool = noticeService.updateMsgRecById(theObj);
		       // theObj = noticeService.findMimeMessageById(theObj.getId());		        
		      
			} 
		}
	    if (bool){
        	return "true";
        }else{
        	return "false";
        }
	}//--- feedback ending-------------------

}

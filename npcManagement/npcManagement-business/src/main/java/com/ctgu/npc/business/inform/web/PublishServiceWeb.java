package com.ctgu.npc.business.inform.web;

import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.inform.entity.Publish;
import com.ctgu.npc.business.inform.service.PublishService;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
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

@Component
@Path("/publish")
public class PublishServiceWeb {
	
	@Resource
	PublishService pubService;
	
	/**
	 * 根据publishId查询publish详细信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getPublishDetailNpc")
	@POST
	public String getPublishDetailNpc(@FormParam("pub_id") String pub_id){
		Publish	pub = pubService.getPublishDetailNpc(pub_id);
		return JsonResultUtils.getObjectResultByStringAsDefault(pub, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * 根据系统级别获取publishList-人大,政府,法院,检察院
	 * @param level_code
	 * @return
	 */
	@RequestMapping(value="getPublishListNpc")
	@ResponseBody
	public List<Publish> getPublishListNpc(HttpServletRequest request, HttpServletResponse response, Model model){
		List<Publish> pubList = new ArrayList<Publish>();
		
		String level_code = request.getParameter("level_code");
		String pageNum = request.getParameter("curPage");
		String pub_col = request.getParameter("pub_col");
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		pubList = pubService.getPublishListNpc(curPage,level_code,pub_col);
		
		return pubList;
		
	}

	
	/**
	 * 根据系统级别获取publishList-人大,政府,法院,检察院 包含分页信息
	 * @param level_code
	 * @return
	 */
	@RequestMapping(value="getPublishListNpcPage")
	@ResponseBody
	public PagesUtil<Publish> getPublishListNpcPage(HttpServletRequest request, HttpServletResponse response, Model model){
		PagesUtil<Publish> pubList = new PagesUtil<Publish>();
		
		String level_code = request.getParameter("level_code");
		String pageNum = request.getParameter("curPage");
		String pub_col = request.getParameter("pub_col");
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		
		pubList = pubService.getPublishListNpcPage(curPage,level_code,pub_col);
		
		return pubList;
		
	}

}

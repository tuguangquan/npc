package com.ctgu.npc.business.basicInfo.web;


import com.ctgu.npc.business.basicInfo.entity.Npc;
import com.ctgu.npc.business.basicInfo.entity.PersonInfo;
import com.ctgu.npc.business.basicInfo.service.NpcService;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/basic")
public class NpcServiceWeb {
	
	@Autowired
	private NpcService npcService;
	
	/**
	 * === 修改我的个人资料
	 * @param loginName
	 * @param json_str
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/savePersonalInfo")
	@POST
	public String savePersonalInfo(@FormParam("loginName") String loginName,@FormParam("json_str") String json_str,
								   @FormParam("level_code") String level_code) {
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<PersonInfo>() {}.getType();
			PersonInfo theObj = (PersonInfo) gson.fromJson(json_str, type);
			npcService.updatePersonalInfo(theObj, loginName, level_code);
			return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.SUCCESS);
		} else {
			return JsonResultUtils.getCodeAndMesByStringAsDefault(JsonResultUtils.Code.ERROR);
		}
	}
	/**
	 * 根据loginName和系统级别查询npc的详细信息
	 * @param loginName
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getBasicInfo")
	@POST
	public String getBasicInfo(@FormParam("loginName") String loginName, @FormParam("level_code") String level_code){
		Npc npc = npcService.getBasicInfo(loginName,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(npc, JsonResultUtils.Code.SUCCESS);
	}

	
	/**
	 * 
	 * Description:根据登录名查询代表信息 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-1-5 上午9:45:58
	 * @param loginName
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getInfoNPCByLoginName")
	@POST
	public String getInfoNPCByLoginName(@FormParam("loginName") String loginName){
		Npc npc = npcService.getInfoNPCByLoginName(loginName);
		return JsonResultUtils.getObjectResultByStringAsDefault(npc, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * 根据代表团的id查询该代表团的代表信息列表
	 * @param mission_id
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getNpcMembsByMissionId")
	@POST
	public String  getNpcMembsByMissionId(@FormParam("mission_id") String mission_id,@FormParam("level_code") String level_code)
	{
		List<Npc>  lists= npcService.getNpcMembsByMissionId(mission_id, level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * 根据代表团的id查询该代表团的代表信息列表包含分页信息
	 * @param mission_id
	 * @param level_code
	 * @param curPage
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getNpcMembsByMissionIdPage")
	@POST
	public String getNpcMembsByMissionIdPage(@FormParam("mission_id") String mission_id,@FormParam("level_code") String level_code,
											 @FormParam("curPage") String curPage)
	{
		PagesUtil<Npc> lists;
		int currentPage = 1;
		if (curPage != null) {
			try {
				currentPage = Integer.parseInt(curPage);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		lists = npcService.getNpcMembsByMissionIdPage(mission_id, level_code,currentPage);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * 根据系统级别与代表id查询代表详细信息
	 * @param npc_id
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getNpcDetailById")
	@POST
	public String getNpcMembDetailById(@FormParam("npc_id") String npc_id,@FormParam("level_code") String level_code){
		Npc npc = npcService.getNpcMembDetailById(npc_id,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(npc, JsonResultUtils.Code.SUCCESS);
	}

	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/test")
	@GET
	public String test(){
		int i = npcService.test();
		return JsonResultUtils.getObjectResultByStringAsDefault(i, JsonResultUtils.Code.SUCCESS);
	}
}

package com.ctgu.npc.business.inqury_meeting.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.inqury_meeting.entity.Inqury;
import com.ctgu.npc.business.inqury_meeting.entity.Meet;
import com.ctgu.npc.business.inqury_meeting.service.InquryService;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

	private static String secretKey = FundamentalConfigProvider.get("npc.key");
	/**
	 * 获取询问列表-我的 包含分页信息
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListMyInquryPage")
	@POST
	public String getListMyInquryPage(@FormParam("level_code") String level_code,
									  @FormParam("loginName") String loginName,
									  @FormParam("pageNum") String pageNum,
									  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName+ pageNum + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		PagesUtil<Inqury> lists = inquryService.getListMyInquryPage(loginName,curPage,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}
	
	
	
	/**
	 * 根据inqury的id查询详细信息
	 * @param inq_id
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getDetailByIdInqury")
	@POST
	public String getDetailByIdInqury(@FormParam("inq_id") String inq_id,
									  @FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(inq_id +MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Inqury inqury = inquryService.getDetailByIdInqury(inq_id);
		return JsonResultUtils.getObjectResultByStringAsDefault(inqury, JsonResultUtils.Code.SUCCESS);
	}

	/**
	 * 获取询问列表-我的
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListMyInqury")
	@POST
	public String getListMyInqury(@FormParam("level_code") String level_code,
								  @FormParam("loginName") String loginName,
								  @FormParam("pageNum") String pageNum,
								  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName+ pageNum + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		List<Inqury> lists = inquryService.getListMyInqury(loginName,curPage,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}
	
	/**
	 * ===询问 处理列表 ==人大工作人员
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListInquryHandlePage")
	@POST
	public String getListInquryHandlePage(@FormParam("level_code") String level_code,
										  @FormParam("loginName") String loginName,
										  @FormParam("pageNum") String pageNum,
										  @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName+ pageNum + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		PagesUtil<Inqury> lists = inquryService.getListInquryHandlePage(loginName,curPage,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
		
	}
	
	/**
	 *=== 答复询问====
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListInquryReplyPage")
	@POST
	public String getListInquryReplyPage(@FormParam("level_code") String level_code,
										 @FormParam("loginName") String loginName,
										 @FormParam("pageNum") String pageNum,
										 @FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName+ pageNum + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		PagesUtil<Inqury> lists = inquryService.getListInquryReplyPage(loginName,curPage,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * === 我的约见列表
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListMyMeetPage")
	@POST
	public String getListMyMeetPage(@FormParam("level_code") String level_code,
									@FormParam("loginName") String loginName,
									@FormParam("pageNum") String pageNum,
									@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName+ pageNum + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}

		PagesUtil<Meet> lists  = inquryService.getListMyMeetPage(loginName,curPage,level_code);

		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
		
	}
	
	/**
	 * === 约见详细信息
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getDetailByIdMeet")
	@POST
	public String getDetailByIdMeet(@FormParam("theObjId") String theObjId,
									@FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(theObjId + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Meet theObj  = inquryService.getDetailByIdMeet(theObjId);

		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * ===约见处理列表
	 * @param level_code
	 * @param loginName
	 * @param pageNum
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getListMeetHandlePage")
	@POST
	public String getListMeetHandlePage(@FormParam("level_code") String level_code,
										@FormParam("loginName") String loginName,
										@FormParam("pageNum") String pageNum,
										@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(level_code + loginName+ pageNum + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		PagesUtil<Meet> lists = inquryService.getListMeetHandlePage(loginName,curPage,level_code);
		return JsonResultUtils.getObjectResultByStringAsDefault(lists, JsonResultUtils.Code.SUCCESS);
		
	}
	
}

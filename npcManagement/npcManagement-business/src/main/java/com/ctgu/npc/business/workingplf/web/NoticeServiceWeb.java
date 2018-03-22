package com.ctgu.npc.business.workingplf.web;

import com.ctgu.npc.business.common.utils.BisUtils;
import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.workingplf.entity.ReceivedMessage;
import com.ctgu.npc.business.workingplf.service.NoticeService;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 通知 Title:NoticeController Description: Company: ctgu
 * 
 * @author : youngmien
 * @date 2017-7-3 上午10:16:24
 */
@Component
@Path("/notice")
public class NoticeServiceWeb {
	private static String secretKey = FundamentalConfigProvider.get("npc.key");
	@Autowired
	private NoticeService noticeService;

	/**
	 * 我收到的通知列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-4 下午5:21:20
	 * @param loginName
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/receive")
	@POST
	public String receivedNotice(@FormParam("loginName") String loginName,@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(loginName + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		List<ReceivedMessage> pages = noticeService.findMimeMessage(loginName);
		return JsonResultUtils.getObjectResultByStringAsDefault(pages, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * 我收到的通知详细信息
	 * Description: `
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-4 下午5:26:36
	 * @param theObjId
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/findMimeMessageById")
	@POST
	public String getInfoReceivedMessage(@FormParam("theObjId") String theObjId,@FormParam("key") String key) {
		String keyWord = MD5Util.md5Encode(theObjId + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		ReceivedMessage theObj = noticeService.findMimeMessageById(theObjId);
		return JsonResultUtils.getObjectResultByStringAsDefault(theObj, JsonResultUtils.Code.SUCCESS);

	}
	
	
	/**
	 * 通知反馈
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-5 上午8:23:02
	 * @param loginName
	 * @param level_code
	 * @param json_str
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/feedback")
	@POST
    public String feedback(@FormParam("loginName") String loginName,@FormParam("level_code") String level_code,
						   @FormParam("json_str") String json_str,@FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(loginName+ level_code+json_str+ MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
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
			} 
		}
	    if (bool){
        	return "true";
        }else{
        	return "false";
        }
	}

}

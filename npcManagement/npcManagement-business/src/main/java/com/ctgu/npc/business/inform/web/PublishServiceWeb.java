package com.ctgu.npc.business.inform.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.inform.entity.Publish;
import com.ctgu.npc.business.inform.service.PublishService;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/publish")
public class PublishServiceWeb {
	
	@Resource
	PublishService pubService;

	private static String secretKey = FundamentalConfigProvider.get("npc.key");
	/**
	 * 根据publishId查询publish详细信息
	 * @param pub_id
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getPublishDetailNpc")
	@POST
	public String getPublishDetailNpc(@FormParam("pub_id") String pub_id,@FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(pub_id + MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		Publish	pub = pubService.getPublishDetailNpc(pub_id);
		return JsonResultUtils.getObjectResultByStringAsDefault(pub, JsonResultUtils.Code.SUCCESS);
	}
	
	
	/**
	 * 根据系统级别获取publishList-人大,政府,法院,检察院
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getPublishListNpc")
	@POST
	public String getPublishListNpc(@FormParam("level_code") String level_code,@FormParam("pageNum") String pageNum,
									@FormParam("pub_col") String pub_col,@FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(level_code+pageNum+pub_col+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		List<Publish> pubList = pubService.getPublishListNpc(curPage,level_code,pub_col);
		return JsonResultUtils.getObjectResultByStringAsDefault(pubList, JsonResultUtils.Code.SUCCESS);
		
	}

	
	/**
	 * 根据系统级别获取publishList-人大,政府,法院,检察院 包含分页信息
	 * @param level_code
	 * @return
	 */
	@Produces( MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Path("/getPublishListNpcPage")
	@POST
	public String getPublishListNpcPage(@FormParam("level_code") String level_code,@FormParam("pageNum") String pageNum,
										@FormParam("pub_col") String pub_col,@FormParam("key") String key){
		String keyWord = MD5Util.md5Encode(level_code+pageNum+pub_col+MD5Util.getDateStr() + secretKey);
		if (!keyWord.equals(key)){
			return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
		}
		int curPage = 1;
		if(pageNum != null){
			curPage = Integer.valueOf(pageNum);
		}
		PagesUtil<Publish> pubList = pubService.getPublishListNpcPage(curPage,level_code,pub_col);
		return JsonResultUtils.getObjectResultByStringAsDefault(pubList, JsonResultUtils.Code.SUCCESS);
		
	}

}

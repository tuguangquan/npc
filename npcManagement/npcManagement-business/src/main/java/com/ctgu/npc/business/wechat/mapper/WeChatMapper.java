package com.ctgu.npc.business.wechat.mapper;

import java.util.Map;

public interface WeChatMapper {
	
	/**
	 * 用户关注就增加到C_weixin表中
	 * @param map
	 * @return
	 */
	public int addWeChat(Map<String, Object> map);



}

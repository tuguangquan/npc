package com.ctgu.npc.business.wechat.mapper;

import com.ctgu.npc.business.wechat.entity.WeiXinChannel;
import org.apache.ibatis.annotations.Param;


public interface WeChatMapper {

	/**
	 * 用户关注就增加到C_weixin表中
	 * @param
	 * @return
	 */
	public int addWeChat(WeiXinChannel weiXinChannel);

	public void updateWeChat(WeiXinChannel weiXinChannel);

	/**
	 * 根据openId查询详细
	 * @param opId
	 * @return
	 */
	public WeiXinChannel getWeiXinChannelByOpId(@Param("opId") String opId);
	/**
	 * 根据openId查询详细
	 * @param unionId
	 * @return
	 */
	public WeiXinChannel getWeiXinChannelByUnionId(@Param("unionId") String unionId);

}

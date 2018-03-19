package com.ctgu.npc.business.workingplf.mapper;


import com.ctgu.npc.business.workingplf.entity.ReceivedMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {

	/**
	 * 
	 * Description: 查询发送给我的通知
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-4 上午11:25:03
	 * @param aObj
	 * @return 通知List
	 */
	public List<ReceivedMessage> findMimeMessage(ReceivedMessage receivedMessage);

	/**
	 * 通知详细
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-4 下午5:33:12
	 * @param id
	 * @return
	 */
	public ReceivedMessage findMimeMessageById(@Param("id") String id);

	/**
	 * 更新通知
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-5 上午8:29:27
	 * @param receivedMessage
	 */
	public void updateMsgRecById(ReceivedMessage receivedMessage);

}

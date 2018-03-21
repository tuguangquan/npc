package com.ctgu.npc.business.workingplf.service;

import com.ctgu.npc.business.common.persistence.Page;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import com.ctgu.npc.business.workingplf.entity.ReceivedMessage;
import com.ctgu.npc.business.workingplf.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly=true)
public class NoticeService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private NoticeMapper noticeMapper;

	/**
	 * 发送给我的通知
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-4 下午2:41:51
	 * @param page
	 * @param loginName
	 * @param level_code
	 * @param curPage
	 * @return
	 */
	public List<ReceivedMessage> findMimeMessage(String loginName
			) {

		ReceivedMessage aObj = new ReceivedMessage();
		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);
		String userID = auser.getId();
		aObj.setReceiverId(Integer.parseInt(userID));
		List<ReceivedMessage> theList = noticeMapper.findMimeMessage(aObj);
		
		return theList;
	}

	/**
	 * 通知详细
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-4 下午5:27:49
	 * @param id
	 * @return
	 */
	public ReceivedMessage findMimeMessageById(String id) {
		// TODO Auto-generated method stub
		return noticeMapper.findMimeMessageById(id);
	}

	@Transactional(readOnly=false)
	public boolean updateMsgRecById(ReceivedMessage receivedMessage) {
		try{
            noticeMapper.updateMsgRecById(receivedMessage);
            return true;
        }catch(Exception e){
            return false;
        }
		
	}
}

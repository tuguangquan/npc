package com.ctgu.npc.business.workingplf.service;


import com.ctgu.npc.business.common.persistence.Page;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import com.ctgu.npc.business.workingplf.entity.ReceivedMessage;
import com.ctgu.npc.business.workingplf.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class NoticeService {
	
	@Autowired
	UserMapper userDao;
	
	@Autowired
	private NoticeMapper noticeDao;

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
	public List<ReceivedMessage> findMimeMessage(Page<ReceivedMessage> page, String loginName,
			String level_code, int curPage) {

		ReceivedMessage aObj = new ReceivedMessage();
		
		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();
		
		aObj.setReceiverId(Integer.parseInt(userID));
		
		// 设置分页参数
		//aObj.setPage(page);
       
		// 执行分页查询
        //page.setList(noticeDao.findMimeMessage(aObj));
		List<ReceivedMessage> theList = noticeDao.findMimeMessage(aObj);
		
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
		return noticeDao.findMimeMessageById(id);
	}

	@Transactional(readOnly=false)
	public boolean updateMsgRecById(ReceivedMessage receivedMessage) {
		try{
            noticeDao.updateMsgRecById(receivedMessage);
            return true;
        }catch(Exception e){
            return false;
        }
		
	}
}

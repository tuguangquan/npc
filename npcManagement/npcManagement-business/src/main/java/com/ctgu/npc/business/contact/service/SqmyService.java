package com.ctgu.npc.business.contact.service;

import com.ctgu.npc.business.basicInfo.mapper.NpcMapper;
import com.ctgu.npc.business.contact.entity.AnswerSqmy;
import com.ctgu.npc.business.contact.entity.LeaveMessage;
import com.ctgu.npc.business.contact.entity.Sqmy;
import com.ctgu.npc.business.contact.mapper.AnswerMapper;
import com.ctgu.npc.business.contact.mapper.LeaveMsgMapper;
import com.ctgu.npc.business.contact.mapper.SqmyMapper;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.OfficeMapper;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 社情民意接口实现类
 * Title:SqmyService 
 * Description: 
 * Company: ctgu 
 * @author : youngmien
 * @date  2017-7-26 上午8:49:21
 */
@Transactional(readOnly = true)
public class SqmyService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private NpcMapper npcMapper;
	
	@Autowired
	private SqmyMapper sqmyMapper;

	@Autowired
	private AnswerMapper answerMapper;

	@Autowired
	private LeaveMsgMapper leaveMsgMapper;

	@Autowired
	private OfficeMapper officeMapper;

	public List<Sqmy> getMySqmyList(String loginName, String level_code) {
		// TODO Auto-generated method stub
		List<Sqmy> aList = new ArrayList<Sqmy>();
		
		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);
		String userID = auser.getId();

		Sqmy theObj = new Sqmy();
		if (StringUtils.isEmpty(theObj.getYear())) {
			theObj.setYear(npcMapper.findPeriod(level_code).getYear());
		}
		theObj.setFirstWriterID(userID);
		theObj.setLevel(level_code);
		
		aList = sqmyMapper.mySqmyList(theObj);
		return aList;
	}

	public Sqmy get(String id) {
		// TODO Auto-generated method stub
		return sqmyMapper.get(id);
	}

	public LeaveMessage getLeaveMessage(String qzmsgID) {
		// TODO Auto-generated method stub
		return sqmyMapper.getLeaveMessage(qzmsgID);
	}

	public List<AnswerSqmy> getAnswerSqmy(String id) {
		// TODO Auto-generated method stub
		return sqmyMapper.getAnswerSqmy(id);
	}

}

package com.ctgu.npc.business.contact.service;


import com.ctgu.npc.business.basicInfo.mapper.NpcMapper;
import com.ctgu.npc.business.common.utils.MsgUtils;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.contact.entity.LeaveWord;
import com.ctgu.npc.business.contact.mapper.ContactMapper;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
public class ContactService {

	@Autowired
	private ContactMapper contactMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private NpcMapper npcMapper;

	/**
	 * ===我的留言分页列表
	 * 
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<LeaveWord> myLeaveWordPage(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		List<LeaveWord> lists = new ArrayList<LeaveWord>();

		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);
		String userID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsMyLeaveWord(userID, level_code);
		PagesUtil<LeaveWord> pagesUtil = new PagesUtil<LeaveWord>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("id", userID);
		map.put("level", level_code);

		lists = contactMapper.getListmyLeaveWord(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * === 我的留言列表记录数
	 * 
	 * @param userID
	 * @param level_code
	 * @return
	 */
	private int getRowsMyLeaveWord(String userID, String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", userID);
		map.put("level", level_code);

		return contactMapper.getRowsMyLeaveWord(map);
	}

	/**
	 * 留言详细
	 * 
	 * @param id
	 * @return
	 */
	public LeaveWord getInfoLeaveWord(String id) {
		// TODO Auto-generated method stub
		return contactMapper.getInfoLeaveWord(id);
	}

	/**
	 * 留言回复
	 * 
	 * @param theObj
	 * @param loginName
	 * @param level_code
	 */
	public String replyLeaveWord(LeaveWord theObj, String loginName,
			String level_code) {

		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);

		String senderName = auser.getName();
		String replyContent = theObj.getContent();
		String phone = theObj.getPhone();
		String bool = MsgUtils.send(senderName, replyContent, phone);
		return bool;

	}

}

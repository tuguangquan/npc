package com.ctgu.npc.business.inqury_meeting.service;


import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.inqury_meeting.entity.Inqury;
import com.ctgu.npc.business.inqury_meeting.entity.Meet;
import com.ctgu.npc.business.inqury_meeting.mapper.InquryMapper;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InquryService {

	@Resource
	InquryMapper inquryDao;

	@Resource
	private UserMapper userDao;

	/**
	 * 获取询问列表-我的
	 * 
	 * @param user_id
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public List<Inqury> getListMyInqury(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		List<Inqury> lists = new ArrayList<Inqury>();

		Users auser = new Users(loginName);
		String user_id = userDao.getUserByLoginName(auser).getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByLevelUserIdInqury(user_id, level_code);

		// System.out.println("myHeadSugList->row = " + rowCount);

		PagesUtil pagesUtil = new PagesUtil();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("user_id", user_id);
		map.put("level_code", level_code);

		lists = inquryDao.getListMyInqury(map);

		return lists;
	}

	/**
	 * 根据user_id,level_code查询询问列表总记录数-我的
	 * 
	 * @param user_id
	 * @param level_code
	 * @return
	 */
	private int getRowsByLevelUserIdInqury(String user_id, String level_code) {
		// TODO Auto-generated method stub

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("level_code", level_code);

		return inquryDao.getRowsByLevelUserIdInqury(map);
	}

	/**
	 * 根据inqury的id查询详细信息
	 * 
	 * @param inq_id
	 * @return
	 */
	public Inqury getDetailByIdInqury(String id) {
		// TODO Auto-generated method stub
		return inquryDao.getDetailByIdInqury(id);
	}

	/**
	 * 获取询问列表-我的 包含分页信息
	 * 
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<Inqury> getListMyInquryPage(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		List<Inqury> lists = new ArrayList<Inqury>();

		Users auser = new Users(loginName);
		String user_id = userDao.getUserByLoginName(auser).getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByLevelUserIdInqury(user_id, level_code);

		// System.out.println("myHeadSugList->row = " + rowCount);

		PagesUtil<Inqury> pagesUtil = new PagesUtil<Inqury>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("user_id", user_id);
		map.put("level_code", level_code);

		lists = inquryDao.getListMyInqury(map);
		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * === 询问处理 ==人大工作人员
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<Inqury> getListInquryHandlePage(String loginName,
			int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<Inqury> lists = new ArrayList<Inqury>();

		Users auser = new Users(loginName);
		String user_id = userDao.getUserByLoginName(auser).getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByLevelInqury( level_code);

		// System.out.println("myHeadSugList->row = " + rowCount);

		PagesUtil<Inqury> pagesUtil = new PagesUtil<Inqury>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("user_id", user_id);
		map.put("level", level_code);

		lists = inquryDao.getListInquryHandle(map);
		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * === 询问处理记录数 ==
	 * @param level_code
	 * @return
	 */
	private int getRowsByLevelInqury(String level_code) {
		// TODO Auto-generated method stub
		Inqury inqury = new Inqury();
		inqury.setLevel(level_code);
		
		return inquryDao.getRowsByLevelInqury(inqury);
		
	}

	/**
	 * === 答复询问 ======
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<Inqury> getListInquryReplyPage(String loginName,
			int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<Inqury> lists = new ArrayList<Inqury>();

		Users auser = new Users(loginName);
		String user_id = userDao.getUserByLoginName(auser).getId();
		String unit_id = auser.getOfficeId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByLevelUnitInqury( level_code, unit_id);

		// System.out.println("myHeadSugList->row = " + rowCount);

		PagesUtil<Inqury> pagesUtil = new PagesUtil<Inqury>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("unitId", unit_id);
		map.put("level", level_code);

		lists = inquryDao.getListInquryReply(map);
		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	private int getRowsByLevelUnitInqury(String level_code, String unit_id) {
		// TODO Auto-generated method stub
		Inqury inqury = new Inqury();
		inqury.setLevel(level_code);
		inqury.setUnitId(unit_id);
		
		return inquryDao.getRowsByLevelUnitInqury(inqury);
	}

	/**
	 * === 我的约见 列表
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<Meet> getListMyMeetPage(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		List<Meet> lists = new ArrayList<Meet>();

		Users auser = new Users(loginName);
		String user_id = userDao.getUserByLoginName(auser).getId();
		//String unit_id = auser.getOfficeId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsMyMeet( level_code, user_id);

		// System.out.println("myHeadSugList->row = " + rowCount);

		PagesUtil<Meet> pagesUtil = new PagesUtil<Meet>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("npcId", user_id);
		map.put("level", level_code);

		lists = inquryDao.getListMyMeet(map);
		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * === 我的约见记录数
	 * @param level_code
	 * @param unit_id
	 * @return
	 */
	private int getRowsMyMeet(String level_code, String npcId) {
		// TODO Auto-generated method stub
		Meet theObj = new Meet();
		theObj.setLevel(level_code);
		theObj.setNpcId(StringUtils.toInteger(npcId));
		
		return inquryDao.getRowsMyMeet(theObj);
	}

	/**
	 * === 约见详细信息
	 * @param inq_id
	 * @return
	 */
	public Meet getDetailByIdMeet(String id) {
		// TODO Auto-generated method stub
		return inquryDao.getDetailByIdMeet(id);
	}

	/**
	 * === 约见处理列表
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<Meet> getListMeetHandlePage(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		List<Meet> lists = new ArrayList<Meet>();

		Users auser = new Users(loginName);
		String user_id = userDao.getUserByLoginName(auser).getId();
		//String unit_id = auser.getOfficeId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsMeetHandle( level_code);

		// System.out.println("myHeadSugList->row = " + rowCount);

		PagesUtil<Meet> pagesUtil = new PagesUtil<Meet>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("npcId", user_id);
		map.put("level", level_code);

		lists = inquryDao.getListMeetHandle(map);
		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * === 约见处理记录数 ====
	 * @param level_code
	 * @return
	 */
	private int getRowsMeetHandle(String level_code) {
		// TODO Auto-generated method stub
		Meet theObj = new Meet();
		theObj.setLevel(level_code);
		//theObj.setNpcId(StringUtils.toInteger(npcId));
		
		return inquryDao.getRowsMeetHandle(theObj);
	}

}

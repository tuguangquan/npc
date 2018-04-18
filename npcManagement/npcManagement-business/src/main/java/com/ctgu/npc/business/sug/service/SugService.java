package com.ctgu.npc.business.sug.service;

import com.ctgu.npc.business.basicInfo.mapper.NpcMapper;
import com.ctgu.npc.business.common.utils.DateUtils;
import com.ctgu.npc.business.common.utils.MsgUtils;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.sug.dto.SuggestionDto;
import com.ctgu.npc.business.sug.entity.*;
import com.ctgu.npc.business.sug.mapper.SugMapper;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Transactional(readOnly = false)
public class SugService {

	@Autowired
	private SugMapper sugMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private NpcMapper npcMapper;

	private String firstWriterID;

	/**
	 * ===办理=待签收=交办直接办理
	 * 
	 * @param curPage
	 * @param level_code
	 * @param status
	 * @param type_value
	 * @param loginName
	 * @return
	 */
	public PagesUtil<AssignForm> sugAssignDealList(int curPage,
			String level_code, String status, String type_value,
			String loginName) {
		// TODO Auto-generated method stub
		
		return sugAssignList(curPage,level_code,"1",type_value,loginName);
	}

	/**
	 * ===交办列表===建议议案
	 * 
	 * @param curPage
	 * @param level_code
	 * @param status
	 * @param type_value
	 * @param loginName
	 * @return
	 */
	public PagesUtil<AssignForm> sugAssignList(int curPage, String level_code,
			String status, String type_value, String loginName) {
		// TODO Auto-generated method stub
		List<AssignForm> sugList = new ArrayList<AssignForm>();

		// AssignForm assignForm = new AssignForm();

		Users auser = new Users(loginName);
		String unitID = userMapper.getUserByLoginName(auser).getOfficeId();

		/* 以下是按分页进行查找 */
		// 获取总记录数

		int rowCount, offset, size;
		
		PagesUtil<AssignForm> pagesUtil = new PagesUtil<AssignForm>();
		rowCount = this.getRowsAssignList(level_code, status, type_value,
				unitID);
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		offset = (curPage - 1) * pagesUtil.getSizePage();
		size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		map.put("status", status);
		map.put("type_value", type_value);
		map.put("unitID", unitID);

		sugList = sugMapper.sugAssignList(map);
		pagesUtil.setLists(sugList);

		return pagesUtil;
	}

	/**
	 * ===交办任务记录数===backFlag
	 * 
	 * @param level_code
	 * @param status
	 * @param type_value
	 * @param backFlag
	 * @param unitID
	 * @return
	 */
	private int getRowsAssignListBackFlag(String level_code, String status,
			String type_value, String backFlag, String unitID) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("level_code", level_code);
		map.put("status", status);
		map.put("type_value", type_value);
		map.put("backFlag", backFlag);
		map.put("unitID", unitID);

		return sugMapper.getRowsAssignListBackFlag(map);
	}

	/**
	 * ===交办任务记录数===
	 * 
	 * @param level_code
	 * @param status
	 * @param type_value
	 * @param unitID
	 * @return
	 */
	private int getRowsAssignList(String level_code, String status,
			String type_value, String unitID) {
		// TODO Auto-generated method stub

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("level_code", level_code);
		map.put("status", status);
		map.put("type_value", type_value);
		map.put("unitID", unitID);

		return sugMapper.getRowsAssignList(map);
	}

	/**
	 * ===转交列表==议案建议
	 * 
	 * @param curPage
	 * @param level_code
	 * @param status
	 * @param type_value
	 * @return
	 */
	public PagesUtil<TransferForm> sugTransferList(int curPage,
			String level_code, String status, String type_value) {
		// TODO Auto-generated method stub
		List<TransferForm> sugList = new ArrayList<TransferForm>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsTransferList(level_code, status, type_value);

		// System.out.println("getRowCountByLevelExcellent->row = " + rowCount);

		PagesUtil<TransferForm> pagesUtil = new PagesUtil<TransferForm>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		map.put("status", status);
		map.put("type_value", type_value);

		// System.out.println("myHeadSugList->offset = " + offset + ",->size= "
		// + size);

		sugList = sugMapper.sugTransferList(map);

		pagesUtil.setLists(sugList);

		return pagesUtil;
	}

	/**
	 * ===转交记录数===
	 * 
	 * @param level_code
	 * @param status
	 * @param type_value
	 * @return
	 */
	private int getRowsTransferList(String level_code, String status,
			String type_value) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		map.put("status", status);
		map.put("type_value", type_value);

		return sugMapper.getRowsTransferList(map);
	}

	/**
	 * 立案列表
	 * 
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<Suggestion> sugPutonList(int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsPutonList(level_code);

		// System.out.println("getRowCountByLevelExcellent->row = " + rowCount);

		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		// map.put("type_value", type_value);

		// System.out.println("myHeadSugList->offset = " + offset + ",->size= "
		// + size);

		sugList = sugMapper.getListByMapPuton(map);

		pagesUtil.setLists(sugList);

		return pagesUtil;
	}

	/**
	 * ===立案列表的总记录数
	 * 
	 * @param level_code
	 * @return
	 */
	private int getRowsPutonList(String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);

		return sugMapper.getRowsPutonList(map);
	}

	/**
	 * === 审核列表
	 * 
	 * @param curPage
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	public PagesUtil<Suggestion> sugCheckList(int curPage, String level_code,
			String type_value) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsCheckList(level_code, type_value);

		// System.out.println("getRowCountByLevelExcellent->row = " + rowCount);

		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		map.put("type_value", type_value);

		// System.out.println("myHeadSugList->offset = " + offset + ",->size= "
		// + size);

		sugList = sugMapper.getListByMapCheck(map);

		pagesUtil.setLists(sugList);

		return pagesUtil;

	}

	/**
	 * === 审核列表记录数
	 * 
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	private int getRowsCheckList(String level_code, String type_value) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		map.put("type_value", type_value);

		return sugMapper.getRowsCheckList(map);
	}

	/**
	 * ===详细信息 根据id及sug_type查询建议议案的详细
	 * 
	 * @param id
	 * @param sug_type
	 * @return
	 */
	public Suggestion getSuggestionByIdType(String id, String sug_type) {
		// TODO Auto-generated method stub
		return sugMapper.getSuggestionByIdType(id, sug_type);
	}

	/**
	 * ===代表团的 根据系统级别和代表团id查询suggestion的记录数
	 * 
	 * @param level_code
	 * @param team_id
	 * @param type_value
	 * @return
	 */
	private int getRowsByOfficeIdType(String level_code, String team_id,
			String type_value) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		map.put("team_id", team_id);
		if (!type_value.equals("0")){
			map.put("type_value", type_value);
		}
		return sugMapper.getRowsByOfficeIdType(map);
	}

	/**
	 * === 代表团的 根据代表团id查询本代表团所有的suggestion包含分页信息
	 * 
	 * @param curPage
	 * @param level_code
	 * @param team_id
	 * @param type_value
	 * @return
	 */
	public PagesUtil<Suggestion> sugListOfficePage(int curPage,
			String level_code, String team_id, String type_value) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByOfficeIdType(level_code, team_id,
				type_value);

		// System.out.println("myHeadSugList->row = " + rowCount);

		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		map.put("team_id", team_id);
		if (!type_value.equals("0")){
			map.put("type_value", type_value);
		}
		sugList = sugMapper.getListByOfficeId(map);

		pagesUtil.setLists(sugList);
		return pagesUtil;

	}

	/**
	 * 获取该suggestion的联名集
	 * 
	 * @param ids
	 * @return
	 */
	public List<Users> getSecondWriter(String ids) {
		// TODO Auto-generated method stub

		ids = ids.substring(1, ids.length() - 1).replaceAll("#", ",");

		return sugMapper.getSecondWriter(ids);
	}

	/**
	 * ===我联名的 根据登录名及系统级别查询suggestion集合List(我联名)的记录总数
	 * 
	 * @param type_value
	 * @param loginName
	 * @param level_code
	 * @return
	 */

	private int getRowTotal2ndMap(String loginName, String level_code,
			String type_value) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		String secondWriterIDS = userMapper.getUserByLoginName(auser).getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("secondWriterIDS", secondWriterIDS);
		map.put("level_code", level_code);
		map.put("type_value", type_value);

		return sugMapper.getRowTotal2ndMap(map);
	}

	/**
	 * ===我联名的 根据登录名和系统等级分页查询建议议案suggestion 的List包含分页信息
	 * 
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @param type_value
	 * @param type_value
	 * @return
	 */
	public PagesUtil<Suggestion> mySugListJoinPage(String loginName,
			int curPage, String level_code, String type_value) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);
		String secondWriterIDS = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this
				.getRowTotal2ndMap(loginName, level_code, type_value);
		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("secondWriterIDS", secondWriterIDS);
		map.put("level_code", level_code);
		if (!type_value.equals("0")){
			map.put("type_value", type_value);
		}
		sugList = sugMapper.getListPageByMap2ndType(map);

		pagesUtil.setLists(sugList);

		return pagesUtil;
	}

	/**
	 * ===我领衔的 根据登录名及系统级别查询suggestion的集合记录总数(我领衔)
	 * 
	 * @param loginName
	 * @param level_code
	 * @return
	 */
	private int getRowTotalMap(String loginName, String level_code, String type_value) {
		// TODO Auto-generated method stub

		Users auser = new Users(loginName);
		String firstWriterID = userMapper.getUserByLoginName(auser).getId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstWriterID", firstWriterID);
		map.put("level_code", level_code);
		if (!type_value.equals("0")){
			map.put("type_value", type_value);
		}
		return sugMapper.getRowTotalMap(map);
	}

	/**
	 * 我领衔的 根据登录名和系统等级分页查询我领衔的suggestion 的List包含分页信息
	 * 
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<SuggestionDto> mySugListHeadPage(String loginName,
			int curPage, String level_code,String type_value) {
		// TODO Auto-generated method stub
		List<SuggestionDto> sugList;

		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);
		String firstWriterID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowTotalMap(loginName, level_code,type_value);
		PagesUtil<SuggestionDto> pagesUtil = new PagesUtil<SuggestionDto>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("firstWriterID", firstWriterID);
		map.put("level_code", level_code);
		if (!type_value.equals("0")){
			map.put("type_value", type_value);
		}
		sugList = sugMapper.getListPageByMap(map);

		pagesUtil.setLists(sugList);

		return pagesUtil;
	}// -----mySugListHeadPage END----------------------------------------

	/**
	 * ===优秀的 根据系统级别查询议案建议的总记录数-优秀
	 * 
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	private int getRowCountByLevelExcellentType(String level_code,
			String type_value) {
		// TODO Auto-generated method stub

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		map.put("type_value", type_value);

		return sugMapper.getRowsExcellentMap(map);
	}

	/**
	 * ===优秀的 查看本级议案建议列表包含分页信息
	 * 
	 * @param curPage
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	public PagesUtil<Suggestion> sugListExcellentPage(int curPage,
			String level_code, String type_value) {
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowCountByLevelExcellentType(level_code,
				type_value);

		// System.out.println("getRowCountByLevelExcellent->row = " + rowCount);

		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		map.put("type_value", type_value);

		// System.out.println("myHeadSugList->offset = " + offset + ",->size= "
		// + size);

		sugList = sugMapper.getListByMapExcellentType(map);

		pagesUtil.setLists(sugList);

		return pagesUtil;

	}// -----sugListExcellentPage END ----------------

	/**
	 * ===重点的 根据系统级别查询议案建议的总记录数-重点
	 * 
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	private int getRowsEmphasisMap(String level_code, String type_value) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		map.put("type_value", type_value);

		return sugMapper.getRowsEmphasisMap(map);
	}

	/**
	 * 重点的 查看本级议案建议列表包含分页信息
	 * 
	 * @param curPage
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	public PagesUtil<Suggestion> sugListEmphasisPage(int curPage,
			String level_code, String type_value) {
		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsEmphasisMap(level_code, type_value);

		// System.out.println("myHeadSugList->row = " + rowCount);

		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		map.put("type_value", type_value);
		List<Suggestion>  sugList = sugMapper.getListByLevelMapEmphasis(map);

		pagesUtil.setLists(sugList);

		return pagesUtil;
	}

	private int getRowCountByLevelEmphasis(String level_code) {
		// TODO Auto-generated method stub

		return sugMapper.getRowCountByLevelEmphasis(level_code);
	}

	/**
	 * ===代表们的 根据系统级别查询代表们议案建议的总记录数-代表们
	 * 
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	private int getRowsAllMap(String level_code, String type_value) {
		// TODO Auto-generated method stub

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		if (!type_value.equals("0")){
			map.put("type_value", type_value);
		}
		return sugMapper.getRowsAllMap(map);
	}

	/**
	 * ---代表们 查看本级代表们议案建议列表 包含分页信息
	 * 
	 * @param curPage
	 * @param level_code
	 * @param type_value
	 * @return
	 */
	public PagesUtil<Suggestion> sugListAllPage(int curPage, String level_code,
			String type_value) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsAllMap(level_code, type_value);


		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		if (!type_value.equals("0")){
			map.put("type_value", type_value);
		}
		sugList = sugMapper.getListAllMapType(map);

		pagesUtil.setLists(sugList);

		return pagesUtil;

	}

	/**
	 * 获取记录行数
	 * 
	 * @param loginName
	 * @return
	 */
	public int getRowCount(String loginName) {
		// TODO Auto-generated method stub

		Users auser = new Users(loginName);
		String firstWriterID = userMapper.getUserByLoginName(auser).getId();

		return sugMapper.getRowCount(firstWriterID);
	}

	/**
	 * 将List按分页处理
	 * 
	 * @param pagesUtil
	 * @return
	 */
	public PagesUtil<Suggestion> getPagesByNum(PagesUtil<Suggestion> pagesUtil) {
		// TODO Auto-generated method stub

		int curPage = pagesUtil.getCurPage();
		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);

		List<Suggestion> sugList = sugMapper.getListByMap(map);

		pagesUtil.setLists(sugList);

		return pagesUtil;
	}

	public String getStatus(String status, String type) {
		// TODO Auto-generated method stub
		return sugMapper.getStatus(status, type);
	}

	public String getStates(String status, String sug_type) {
		// TODO Auto-generated method stub
		return sugMapper.getStates(status, sug_type);
	}

	/**
	 * 新增议案建议-我领衔
	 * 
	 * @param sug
	 * @param loginName
	 * @param level_code
	 */
	@Transactional(readOnly = false)
	public void mySugHeadAdd(Suggestion sug, String loginName, String level_code) {
		// TODO Auto-generated method stub
		// 根据登录名获取该用户id
		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);
		String firstWriterID = auser.getId();

		sug.setYear(DateUtils.getYear());
		sug.setSecondary(npcMapper.findPeriod(level_code).getPeriod());
		sug.setSequence(npcMapper.findPeriod(level_code).getTimes());
		sug.setTeamID(npcMapper.getInfo(Integer.parseInt(firstWriterID),
				level_code).getMissionId());
		sug.setCreateTime(DateUtils.getDate());
		sug.setCreateUserID(firstWriterID);
		sug.setFirstWriterID(firstWriterID);
		sug.setLevel(level_code);
		if (StringUtils.isNotEmpty(sug.getSecondWriterIDS())) {
			String str = sug.getSecondWriterIDS().replaceAll(",", "#");
			sug.setSecondWriterIDS("#" + str + "#");
		}
		// 暂存-存为草稿
		sug.setStatus("-2");

		sugMapper.mySugHeadAdd(sug);

	}

	// =====修改前的==========================================================
	/**
	 * 根据suggestion的id获取suggestion的详细
	 * 
	 * @param id
	 * @return
	 */
	public Suggestion getSuggestionById(String id) {
		// TODO Auto-generated method stub
		return sugMapper.getSugById(id);
	}

	/**
	 * 分页查看本级优秀代表议案建议
	 * 
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public List<Suggestion> sugListExcellent(int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowCountByLevelExcellent(level_code);

		// System.out.println("getRowCountByLevelExcellent->row = " + rowCount);

		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		sugList = sugMapper.getListByLevelMapExcellent(map);

		pagesUtil.setLists(sugList);

		return sugList;
	}

	/**
	 * 根据系统级别分页查询重点议案建议
	 * 
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public List<Suggestion> sugListEmphasis(int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowCountByLevelEmphasis(level_code);
		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);


		sugList = sugMapper.getListByLevelMapEmphasis(map);

		pagesUtil.setLists(sugList);

		return sugList;

	}

	/**
	 * 根据代表团id分页查询本代表团所有的suggestion
	 * 
	 * @param curPage
	 * @param level_code
	 * @param team_id
	 * @return
	 */
	public List<Suggestion> sugListOffice(int curPage, String level_code,
			String team_id) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByOfficeId(level_code, team_id);

		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		map.put("team_id", team_id);

		sugList = sugMapper.getListByOfficeId(map);

		pagesUtil.setLists(sugList);
		return sugList;
	}

	/**
	 * 根据系统级别和代表团id查询suggestion的记录数
	 * 
	 * @param level_code
	 * @param team_id
	 * @return
	 */
	private int getRowsByOfficeId(String level_code, String team_id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		map.put("team_id", team_id);

		return sugMapper.getRowsByOfficeId(map);
	}

	/**
	 * 代表们 根据系统级别分页查询本级别所有代表的suggestionList
	 * 
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public List<Suggestion> sugListAll(int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowCountByLevelAll(level_code);

		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);

		sugList = sugMapper.getListByLevelMapAll(map);

		pagesUtil.setLists(sugList);

		return sugList;

	}

	/**
	 * 根据suggestion获取该领衔者所领衔的suggestion集合List
	 * 
	 * @param loginName
	 * @param curPage
	 * @return
	 */
	public List<Suggestion> myHeadSugList(String loginName, int curPage) {
		Users auser = new Users(loginName);
		String firstWriterID = userMapper.getUserByLoginName(auser).getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowCount(loginName);
		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("firstWriterID", firstWriterID);
		List<Suggestion> sugList = sugMapper.getListByIdMap(map);
		pagesUtil.setLists(sugList);
		return sugList;
	}

	/**
	 * 根据登录名及系统级别分页查询suggestion(我领衔的)的集合List
	 * 
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public List<SuggestionDto> mySugListHead(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		List<SuggestionDto> sugList;

		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);
		String firstWriterID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowTotal(loginName, level_code);
		PagesUtil<SuggestionDto> pagesUtil = new PagesUtil<SuggestionDto>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("firstWriterID", firstWriterID);
		map.put("level_code", level_code);

		sugList = sugMapper.getListPageByMap(map);

		pagesUtil.setLists(sugList);

		return sugList;
	}

	/**
	 * 根据登录名及系统级别分页查询suggestion的集合(我联名)
	 * 
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public List<Suggestion> mySugListJoin(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		List<Suggestion> sugList = new ArrayList<Suggestion>();

		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);
		String secondWriterIDS = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowTotal2nd(loginName, level_code);
		PagesUtil<Suggestion> pagesUtil = new PagesUtil<Suggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("secondWriterIDS", secondWriterIDS);
		map.put("level_code", level_code);

		sugList = sugMapper.getListPageByMap2nd(map);

		pagesUtil.setLists(sugList);

		return sugList;

	}

	/**
	 * 根据登录名及系统级别查询suggestion集合List(我联名)的记录总数
	 * 
	 * @param loginName
	 * @param level_code
	 * @return
	 */
	private int getRowTotal2nd(String loginName, String level_code) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		String secondWriterIDS = userMapper.getUserByLoginName(auser).getId();

		return sugMapper.getRowTotal2nd(secondWriterIDS, level_code);
	}

	/**
	 * 根据登录名及系统级别查询suggestion的集合记录总数(我领衔)
	 * 
	 * @param loginName
	 * @param level_code
	 * @return
	 */
	private int getRowTotal(String loginName, String level_code) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		String firstWriterID = userMapper.getUserByLoginName(auser).getId();

		return sugMapper.getRowTotal(firstWriterID, level_code);
	}

	/**
	 * 根据系统级别查询代表们议案建议的总记录数-代表们
	 * 
	 * @param level_code
	 * @return
	 */
	private int getRowCountByLevelAll(String level_code) {
		// TODO Auto-generated method stub
		return sugMapper.getRowCountByLevelAll(level_code);
	}

	/**
	 * 根据系统级别查询议案建议的总记录数-优秀
	 * 
	 * @param level_code
	 * @return
	 */
	private int getRowCountByLevelExcellent(String level_code) {
		// TODO Auto-generated method stub
		return sugMapper.getRowCountByLevelExcellent(level_code);
	}

	/**
	 * 转交信息
	 * 
	 * @param id
	 * @param sug_type
	 * @return
	 */
	public List<TransferForm> getListTransfer(String sugID) {
		// TODO Auto-generated method stub
		return sugMapper.getListTransfer(sugID);
	}

	/**
	 * 交办信息
	 * 
	 * @param sugID
	 * @return
	 */
	public List<AssignForm> getListAssign(String sugID) {
		// TODO Auto-generated method stub
		return sugMapper.getListAssign(sugID);
	}

	/**
	 * 承办信息
	 * 
	 * @param sugID
	 * @return
	 */
	public List<UnitAnswerForm> getListUnitAnswer(String sugID) {
		// TODO Auto-generated method stub
		return sugMapper.getListUnitAnswer(sugID);
	}

	/**
	 * 议案建议处理信息列表
	 * 
	 * @param sugID
	 * @return
	 */
	public SugProcessInfo getSugProcessInfo(String sugID) {
		// TODO Auto-generated method stub
		SugProcessInfo theObj = new SugProcessInfo();

		theObj.setLists01(sugMapper.getListTransfer(sugID));
		theObj.setLists02(sugMapper.getListAssign(sugID));
		theObj.setLists03(sugMapper.getListUnitAnswer(sugID));

		return theObj;
	}

	/**
	 * 议案详细
	 * 
	 * @param sugID
	 * @return
	 */
	public BillsEntity billsDetail(String sugID) {
		// TODO Auto-generated method stub
		Suggestion sug = getSuggestionByIdType(sugID, "2");
		String id = null;
		if (sug != null) {

			// 获取联名人
			if (StringUtils.isNotEmpty(sug.getSecondWriterIDS())) {
				List<Users> list = getSecondWriter(sug.getSecondWriterIDS());
				String str = "";
				for (Users u : list) {
					str += u.getName() + ",";
				}

				sug.setSecondWriter(str.substring(0, str.length() - 1));
			}

			id = sug.getId() + "";
		}

		BillsEntity bills = new BillsEntity();
		bills.setSug(sug);

		List<FilePuton> lists = getListFilePuton(id);
		bills.setPlist(lists);

		return bills;

	}

	/**
	 * ===获取该议案的上传文件列表===
	 * 
	 * @param id
	 * @return
	 */
	private List<FilePuton> getListFilePuton(String sugID) {
		// TODO Auto-generated method stub
		return sugMapper.getListFilePuton(sugID);
	}

	/**
	 * 建议议案评价
	 * @param suggestion
	 * @param loginName
	 * @param level_code
	 */
	@Transactional(readOnly = false)
	public int saveEvaluteSug(Suggestion suggestion, String loginName,
			String level_code) {
		// TODO Auto-generated method stub
		if("不满意".equals(suggestion.getResevaluation())){//如果代表对办理结果不满意，则主办重新办理
			suggestion.setStatus("4");//如果评价不满意则设置为办理状态，重新办理
			
			//获取上次主办单位的已经办理答复的任务，根据这个重新办理
			UnitAnswerForm unitAnswerForm = sugMapper.getLastUnitAnswerForm(suggestion.getId());
			
			//插入一条主办单位任务
			unitAnswerForm.setFlag("1");
			unitAnswerForm.setStatus("2");//待办理
			unitAnswerForm.setBackFlag("1");//退回
			unitAnswerForm.setEvaluate(suggestion.getEvaluate());//不满意理由
			unitAnswerForm.setOperatorDate(DateUtils.getDateTime());
			unitAnswerForm.setCreateDate(DateUtils.getDate());
			sugMapper.insertUnitAnswerForm(unitAnswerForm);
			
			//短信通知
			MsgUtils.sysSend("sug:unitAnswerList:view", unitAnswerForm.getAssignUnitID() + "", "你有一条议案建议代表觉得不满意需要重新办理，理由：" + suggestion.getEvaluate() + "，标题为【" + suggestion.getTitle() + "】");
		}else{
			suggestion.setStatus("6");//如果评价为满意则设置为办结
		}
		return sugMapper.saveEvaluateForm(suggestion);
	}

	/**
	 * 审核
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-8-29 下午3:13:17
	 * @param sug
	 * @param loginName
	 * @param level_code
	 */
	@Transactional(readOnly = false)
	public void saveCheckSug(Suggestion checkSug, String loginName, String level_code) {
		// TODO Auto-generated method stub

		Suggestion sug = sugMapper.getSugById(String.valueOf(checkSug.getId()));
		sug.setCheckRemark(checkSug.getCheckRemark());
		sug.setEvaluate(checkSug.getEvaluate());
		
		
		//如果是审核通过了
		//就往转交表插入一条任务，同时发送短信通知代表
		if("审核通过".equals(sug.getCheckRemark())){
			//自动编号,议案和建议分开编号
			sug.setLevel(level_code);
			Suggestion temp = sugMapper.selectCurrentYearBigCode(sug);
			if(temp != null){
				sug.setCode(temp.getCode()+1);
			}else{
				sug.setCode(1);
			}
			sug.setCheckTime(DateUtils.getDateTime());
			
			Users auser = new Users(loginName);
			String userId = userMapper.getUserByLoginName(auser).getId();
			sug.setCheckUserID(Integer.parseInt(userId));
			
			//sug.setCheckUserID(Integer.parseInt(UserUtils.getUser().getId()));
			
			sug.setStatus("1");//状态设为已审核
			sugMapper.saveCheckForm(sug);
			
			//往转交任务表插入一条记录
			if(sug.getType().equals("1")){
				TransferForm transferForm = new TransferForm();
				transferForm.setStatus("1");//未转交
				transferForm.setBackFlag("0");//正常
				transferForm.setCreateDate(DateUtils.getDate());
				transferForm.setSug(sug);
				sugMapper.insertTransferForm(transferForm);
				//系统短信提示
				MsgUtils.sysSend("sug:transferList:view", "你有一条建议需要转交，标题为【"+sug.getTitle()+"】");
				MsgUtils.sendMsg("你的议案建议已经审核通过，正在开始受理！", sug.getFirstWriterID(), sug.getTel());
			}
		}else if("退回".equals(sug.getCheckRemark())){//如果被退回则短信提示代表进行编辑
			sug.setCheckTime(DateUtils.getDateTime());
			
			Users auser = new Users(loginName);
			String userId = userMapper.getUserByLoginName(auser).getId();
			sug.setCheckUserID(Integer.parseInt(userId));
			//sug.setCheckUserID(Integer.parseInt(UserUtils.getUser().getId()));
			sug.setStatus("0");//状态设为退回
			sugMapper.updateSugCode(sug.getId()+"", null);

			sugMapper.saveCheckForm(sug);
			//删除转交历史
			sugMapper.delTransferList(sug.getId()+"");
			MsgUtils.sendMsg("你有一天议案建议被退回，请登录系统编辑后重新提交！", sug.getFirstWriterID(), sug.getTel());
		}
	}

	public CommunicationForm getCommunicationForm(String id) {
		// TODO Auto-generated method stub
		return sugMapper.getCommunicationForm(id);
	}
	public int deleteSug(String id){
		return sugMapper.deleteSug(id);
	}


	public Suggestion selectSugById(String id){
		return sugMapper.selectSugById(id);
	}

	public void updateSug(Map<String, Object> map){
		sugMapper.updateSug(map);
	}

}

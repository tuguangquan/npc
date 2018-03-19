package com.ctgu.npc.business.perform.service;

import com.ctgu.npc.business.basicInfo.entity.Npc;
import com.ctgu.npc.business.basicInfo.mapper.NpcMapper;
import com.ctgu.npc.business.common.utils.DateUtils;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.Reflections;
import com.ctgu.npc.business.perform.entity.*;
import com.ctgu.npc.business.perform.mapper.PerformMapper;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 履职服务
 * @author 旺旺
 *
 */

@Service
@Transactional(readOnly = true)
public class PerformService {
	
	@Autowired
	PerformMapper performDao;
	
	@Autowired
	UserMapper userDao;
	
	@Autowired
	private NpcMapper npcDao;

	/**
	 * === 我的代表工作
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<JobReport> myJobReportPage(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		List<JobReport> lists = new ArrayList<JobReport>();

		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsMyJobReport(userID, level_code);
		PagesUtil<JobReport> pagesUtil = new PagesUtil<JobReport>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("userID", userID);
		map.put("level_code", level_code);

		lists = performDao.getListmyJobReport(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * === 我的代表工作总记录数
	 * @param userID
	 * @param level_code
	 * @return
	 */
	
	private int getRowsMyJobReport(String userID, String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("level_code", level_code);
		
		return performDao.getRowsMyJobReport(map);
	}



	/**
	 * 我的代表活动-分页列表
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<PerformReport> myPerformActPage(String loginName, int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<PerformReport> lists = new ArrayList<PerformReport>();

		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsMyPerformAct(userID, level_code);
		PagesUtil<PerformReport> pagesUtil = new PagesUtil<PerformReport>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("userID", userID);
		map.put("level_code", level_code);

		lists = performDao.getListMyPerformAct(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * 我的代表活动总记录数
	 * @param userID
	 * @param level_code
	 * @return
	 */
	private int getRowsMyPerformAct(String userID, String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("level_code", level_code);
		
		return performDao.getRowsMyPerformAct(map);
	}

	/**
	 * ===我的代表活动--详细信息
	 * @param id
	 * @return
	 */
	public PerformReport getInfoPerformReport(String id) {
		// TODO Auto-generated method stub
		return performDao.getInfoPerformReport(id);
	}

	/**
	 * 我的述职报告
	 */
	public PagesUtil<WorkReport> myWorkReportPage(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		List<WorkReport> lists = new ArrayList<WorkReport>();

		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsmyWorkReport(userID, level_code);
		PagesUtil<WorkReport> pagesUtil = new PagesUtil<WorkReport>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("userID", userID);
		map.put("level_code", level_code);

		lists = performDao.getListmyWorkReport(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * ===我的履职报告记录数===
	 */
	private int getRowsmyWorkReport(String userID, String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("level_code", level_code);
		
		return performDao.getRowsmyWorkReport(map);
	}

	/**
	 * ===我的履职报告详细
	 * @param id
	 * @return
	 */
	public WorkReport getInfoMyWorkReport(String id) {
		// TODO Auto-generated method stub
		return performDao.getInfoMyWorkReport(id);
	}
	
	/**
	 * === 我的履职统计
	 * @param loginName
	 * @param level_code
	 * @return
	 */
	public List<MyReportEntry> myReportStatisticList(String loginName,
			String level_code) {
		// TODO Auto-generated method stub
		List<MyReportEntry> lists = new ArrayList<MyReportEntry>();

		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();
		String year = DateUtils.getYear();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		/*int rowCount = this.getRowsMyReportStatistic(userID, level_code, year);
		PagesUtil<MyReportEntry> pagesUtil = new PagesUtil<MyReportEntry>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();*/

		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("offset", offset);
		//map.put("size", size);
		map.put("userID", userID);
		map.put("level_code", level_code);
		map.put("year", year);

		lists = performDao.getListMyReportStatistic(map);

		//pagesUtil.setLists(lists);

		return lists;
	}
	

	/**
	 * === 我的履职统计记录数
	 * @param userID
	 * @param level_code
	 * @param year
	 * @return
	 */
	private int getRowsMyReportStatistic(String userID, String level_code,
			String year) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("level_code", level_code);
		map.put("year", year);
		
		return performDao.getRowsMyReportStatistic(map);
	}

	/**
	 * ===代表工作查询
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<JobReport> jobSearchPage(int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<JobReport> lists = new ArrayList<JobReport>();

		/*Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();*/

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsjobSearch( level_code);
		PagesUtil<JobReport> pagesUtil = new PagesUtil<JobReport>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("userID", userID);
		map.put("level_code", level_code);

		lists = performDao.getListjobSearch(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}
	
	/**
	 * ===代表工作查询记录数
	 * @param level_code
	 * @return
	 */
	private int getRowsjobSearch(String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("userID", userID);
		map.put("level_code", level_code);
		
		return performDao.getRowsjobSearch(map);
	}

	/**
	 * 代表工作查询详细===
	 * @param id
	 * @return
	 */
	public JobReport getInfojobSearchDetail(String id) {
		// TODO Auto-generated method stub
		return performDao.getInfojobSearchDetail(id);
	}

	/**
	 * 代表活动查询===
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<PerformReport> actSearchPage(int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<PerformReport> lists = new ArrayList<PerformReport>();

		/*Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();*/

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsPerformReport(level_code);
		PagesUtil<PerformReport> pagesUtil = new PagesUtil<PerformReport>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("userID", userID);
		map.put("level_code", level_code);

		lists = performDao.getListPerformReport(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * ===代表活动查询记录数
	 * @param level_code
	 * @return
	 */
	private int getRowsPerformReport(String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("userID", userID);
		map.put("level_code", level_code);
		
		return performDao.getRowsPerformReport(map);
	}

	/**
	 * 履职报告查询
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<WorkReport> workSearchPage(int curPage, String level_code) {
		// TODO Auto-generated method stub
		List<WorkReport> lists = new ArrayList<WorkReport>();

		/*Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();*/

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsWorkReport(level_code);
		PagesUtil<WorkReport> pagesUtil = new PagesUtil<WorkReport>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("userID", userID);
		map.put("level_code", level_code);

		lists = performDao.getListWorkReport(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * 履职报告查询记录数
	 * @param level_code
	 * @return
	 */
	private int getRowsWorkReport(String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("userID", userID);
		map.put("level_code", level_code);
		
		return performDao.getRowsWorkReport(map);
	}

	/**
	 * 履职报告详细
	 * @param id
	 * @return
	 */
	public WorkReport getInfoworkSearchDetail(String id) {
		// TODO Auto-generated method stub
		return performDao.getInfoworkSearchDetail(id);
	}

	/**
	 * === 提交我的代表工作
	 * @param theObj
	 * @param loginName
	 * @param level_code
	 */
	@Transactional(readOnly = false)
	public void addMyPerformJob(JobReport jobReport, String loginName,
			String level_code) {
		
		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userId = auser.getId();
		
		//System.out.println("userid->" +userId);
		
		jobReport.setSecondary(npcDao.findPeriod(level_code).getPeriod());
		jobReport.setSequence(npcDao.findPeriod(level_code).getTimes());
		jobReport.setTeamID(Integer.parseInt( npcDao.getInfo(Integer.parseInt(userId),
				level_code).getMissionId()));
		jobReport.setUserID(Integer.parseInt(userId));
		jobReport.setYear(DateUtils.getYear());
		jobReport.setCreateUserID(Integer.parseInt(userId));
		jobReport.setCreateDate(DateUtils.getDate());
		jobReport.setLevel(level_code);
		
		List<ActType> actTypeList = performDao.getActTypeList(level_code, "1");
		for(ActType a : actTypeList){
			Reflections.invokeSetter(jobReport, a.getRemark(), a.getScore());
		}
		
		List<JobReport> oneList = performDao.myJobReportList(jobReport);
		if (oneList != null && oneList.size() > 0) {
			jobReport.setId(oneList.get(0).getId());
			performDao.updateJobReport(jobReport);
		} else {
			performDao.insertJobReport(jobReport);

		}
		
		
		
		
	}

	/**
	 * 提交我的代表活动
	 * @param theObj
	 * @param loginName
	 * @param level_code
	 */
	@Transactional(readOnly = false)
	public void addMyPerformAct(PerformReport performReport, String loginName,
			String level_code) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userId = auser.getId();
//		System.out.println("userid->" +userId);
		performReport.setSecondary(npcDao.findPeriod(level_code).getPeriod());
		performReport.setSequence(npcDao.findPeriod(level_code).getTimes());
		
		String misson_id = null;
		List<Npc> alist =  npcDao.getInfoListUseridLevel(Integer.parseInt(userId),level_code);
		if (alist != null && alist.size()>0){
			for (Npc npc : alist) {
				if ("在任届选".equals(npc.getStatus()))
				{
					misson_id = npc.getMissionId();
					System.out.println("misson_id->" + misson_id);
				}
			}
		}else{
			misson_id = "100";
		}
		
		performReport.setTeamID(Integer.parseInt(misson_id));
		performReport.setUserID(Integer.parseInt(userId));
		performReport.setYear(DateUtils.getYear());
		performReport.setCreateUserID(Integer.parseInt(userId));
		performReport.setCreateDate(DateUtils.getDate());
		performReport.setLevel(level_code);
		performReport.setExcellentFlag("2");
		performReport.setPrivateFlag("1");
		
		String type_act = performReport.getType();
		
		ActType actType = performDao.getActTypeByType(type_act,performReport.getLevel());
		
		performReport.setType(actType.getType());
		performReport.setScore(actType.getScore());
		
		/*List<ActType> actTypeList = performDao.getActTypeList(level_code, "1");
		for(ActType a : actTypeList){
			Reflections.invokeSetter(performReport, a.getRemark(), a.getScore());
		}*/
		
		/*List<PerformReport> oneList = performDao.myPerformReportList(performReport);
		if (oneList != null && oneList.size() > 0) {
			performReport.setId(oneList.get(0).getId());
			performDao.updatePerformReport(performReport);
		} else {
			performDao.insertPerformReport(performReport);

		}*/
		performDao.insertPerformReport(performReport);
	}

	/**
	 * 获取登记类型
	 * @param loginName
	 * @param level_code
	 * @return
	 */
	public List<ActType> actTypeList(String loginName, String level_code) {
		// TODO Auto-generated method stub
		return performDao.actTypeList(level_code,"2");
	}

	

	
	
}
	


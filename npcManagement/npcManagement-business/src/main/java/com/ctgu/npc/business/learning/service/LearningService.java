package com.ctgu.npc.business.learning.service;

import com.ctgu.npc.business.basicInfo.mapper.NpcMapper;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.learning.entity.*;
import com.ctgu.npc.business.learning.mapper.LearningMapper;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学习交流
 * @author 旺旺
 *
 */
@Transactional(readOnly = true)
public class LearningService {
	
	@Autowired
	private LearningMapper learningDao;
	
	@Autowired
	private UserMapper userDao;
	
	@Autowired
	private NpcMapper npcDao;
	

	/**
	 * 规章制度分页列表=== 1人大-2政府-3法院-4检察院
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @param type_rule 
	 * @return
	 */
	public PagesUtil<Rule> listPageNpcRule(String loginName, int curPage,
			String level_code, String typeValue) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsNpcRule(userID, level_code,typeValue);
		PagesUtil<Rule> pagesUtil = new PagesUtil<Rule>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("column", typeValue);
		map.put("level", level_code);

		
		List<Rule> lists = learningDao.getListNpcRule(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}


	/**
	 * === 规章制度记录数=1人大-2政府-3法院-4检察院
	 * @param userID
	 * @param type_rule 
	 * @param level_code
	 * @return
	 */
	private int getRowsNpcRule(String userID, String level, String typeValue) {
		// TODO Auto-generated method stub
		
		Rule rule = new Rule();
		rule.setLevel(level);
		rule.setColumn(typeValue);
		
		/*StringBuilder sql = new StringBuilder("");		
		if(StringUtils.isNotEmpty(rule.getTitle())){
			sql.append(" and r.title like '%"+rule.getTitle()+"%' ");
		}		
		sql.append(" and r.level = '"+rule.getLevel()+"' ");*/
		
		return learningDao.getRowsNpcRule(rule);
	}


	/**
	 * ===制度详细
	 * @param id
	 * @return
	 */
	public Rule getInfoRule(String id) {
		// TODO Auto-generated method stub
		return learningDao.getInfoRule(id);
	}


	/**
	 * 履职学习培训资料分页列表
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<Material> listPageMaterial(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsMaterial(level_code);
		PagesUtil<Material> pagesUtil = new PagesUtil<Material>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("column", typeValue);
		map.put("level", level_code);

		
		List<Material> lists = learningDao.getListMaterial(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * 履职学习培训资料记录数
	 * @param level
	 * @return
	 */
	private int getRowsMaterial(String level) {
		// TODO Auto-generated method stub

		Material theObj = new Material();
		theObj.setLevel(level);
		
		return learningDao.getRowsMaterial(theObj);
	}

	/**
	 * 履职学习培训资料详细
	 * @param id
	 * @return
	 */
	public Material getInfoMaterial(String id) {
		// TODO Auto-generated method stub
		return learningDao.getInfoMaterial(id);
	}


	/**
	 * 履职活动培训列表
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<Training> listPageTraining(String loginName, int curPage,
			String level_code) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsTraining(level_code);
		PagesUtil<Training> pagesUtil = new PagesUtil<Training>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("column", typeValue);
		map.put("level", level_code);

		
		List<Training> lists = learningDao.getListTraining(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * 履职活动记录数
	 * @param level
	 * @return
	 */
	private int getRowsTraining(String level) {
		// TODO Auto-generated method stub
		Training theObj = new Training();
		theObj.setLevel(level);
		
		return learningDao.getRowsTraining(theObj);
	}


	/**
	 * 履职培训活动详细
	 * @param id
	 * @return
	 */
	public Training getInfoTraining(String id) {
		// TODO Auto-generated method stub
		return learningDao.getInfoTraining(id);
	}


	/**
	 * 优秀建议议案分页列表
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<ExceSuggestion> listPageExceSuggestion(String loginName,
			int curPage, String level_code) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsExceSuggestion(level_code);
		PagesUtil<ExceSuggestion> pagesUtil = new PagesUtil<ExceSuggestion>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("column", typeValue);
		map.put("level", level_code);

		
		List<ExceSuggestion> lists = learningDao.getListExceSuggestion(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * 优秀建议议案记录数
	 * @param level
	 * @return
	 */
	private int getRowsExceSuggestion(String level) {
		// TODO Auto-generated method stub
		ExceSuggestion theObj = new ExceSuggestion();
		theObj.setLevel(level);
		
		return learningDao.getRowsExceSuggestion(theObj);
	}

	/**
	 * 优秀建议议案详细
	 * @param id
	 * @return
	 */
	public ExceSuggestion getInfoExceSuggestion(String id) {
		// TODO Auto-generated method stub
		return learningDao.getInfoExceSuggestion(id);
	}

	/**
	 * 优秀履职报告分页列表
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public PagesUtil<ExceWorkReport> listPageExceWorkReport(String loginName,
			int curPage, String level_code) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsExceWorkReport(level_code);
		PagesUtil<ExceWorkReport> pagesUtil = new PagesUtil<ExceWorkReport>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("column", typeValue);
		map.put("level", level_code);

		
		List<ExceWorkReport> lists = learningDao.getListExceWorkReport(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}

	/**
	 * 优秀履职报告记录数
	 * @param level
	 * @return
	 */
	private int getRowsExceWorkReport(String level) {
		// TODO Auto-generated method stub
		ExceWorkReport theObj = new ExceWorkReport();
		theObj.setLevel(level);
		
		return learningDao.getRowsExceWorkReport(theObj);
	}

	/**
	 * 优秀履职报告详细
	 * @param id
	 * @return
	 */
	public ExceWorkReport getInfoExceWorkReport(String id) {
		// TODO Auto-generated method stub
		return learningDao.getInfoExceWorkReport(id);
	}

	
	
	/**
	 * 优秀视察调研报告分页列表
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	/*public PagesUtil<ExceReport> listPageExceReport(String loginName,
			int curPage, String level_code) {
		// TODO Auto-generated method stub
		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userID = auser.getId();

		 以下是按分页进行查找 
		// 获取总记录数
		int rowCount = this.getRowsExceReport(level_code);
		PagesUtil<ExceReport> pagesUtil = new PagesUtil<ExceReport>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		//map.put("column", typeValue);
		map.put("level", level_code);

		
		List<ExceReport> lists = learningDao.getListExceReport(map);

		pagesUtil.setLists(lists);

		return pagesUtil;
	}*/

	/**
	 *  优秀视察调研报告记录数
	 * @param level
	 * @return
	 */
	/*private int getRowsExceReport(String level) {
		// TODO Auto-generated method stub
		ExceReport theObj = new ExceReport();
		theObj.setLevel(level);
		
		return learningDao.getRowsExceReport(theObj);
	}*/

	/**
	 *  优秀视察调研报告详细
	 * @param id
	 * @return
	 */
	/*public ExceReport getInfoExceReport(String id) {
		// TODO Auto-generated method stub
		return learningDao.getInfoExceReport(id);
	}*/
	

}

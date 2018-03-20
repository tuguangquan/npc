package com.ctgu.npc.business.inform.service;

import com.ctgu.npc.business.basicInfo.mapper.NpcMapper;
import com.ctgu.npc.business.common.utils.DateUtils;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.inform.entity.*;
import com.ctgu.npc.business.inform.mapper.PaperMapper;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问卷管理
 * @author 旺旺
 *
 */

@Transactional(readOnly = true)
public class PaperService {
	
	@Autowired
	private PaperMapper paperDao;


	@Autowired
	private UserMapper userDao;
	
	@Autowired
	private NpcMapper npcDao;
	
	/**
	 * 根据系统级别查询问卷列表
	 * @param level_code
	 * @param curPage
	 * @return
	 */
	public PagesUtil<Paper> getListPaper(String level_code, int curPage) {
		// TODO Auto-generated method stub
		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsPaper( level_code);
		
		PagesUtil<Paper> pagesUtil = new PagesUtil<Paper>();
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
		map.put("level", level_code);
		
		List<Paper> lists = new ArrayList<Paper>();
		
		lists = paperDao.getListPaper(map);
		
		pagesUtil.setLists(lists);
		return pagesUtil;
	}

	/**
	 * 根据系统级别查询问卷列表记录数
	 * @param level_code
	 * @return
	 */
	private int getRowsPaper(String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("userID", userID);
		map.put("level_code", level_code);
		
		return paperDao.getRowsPaper(map);
	}

	/**
	 * 问卷详细
	 * @param id
	 * @return
	 */
	public Paper getDetailPaper(String id) {
		// TODO Auto-generated method stub
		return paperDao.getDetailPaper(id);
	}

	/**
	 * 添加问卷
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-5-6 上午10:49:10
	 * @param theObj
	 * @param loginName
	 * @param level_code
	 */
	@Transactional(readOnly = false)
	public void addsavePaper(Paper theObj, String loginName, String level_code) {

		Users auser = new Users(loginName);
		auser = userDao.getUserByLoginName(auser);
		String userId = auser.getId();
		
		theObj.setLevel(level_code);
		theObj.setCreateUserId(Integer.parseInt(userId));
		theObj.setCreateTime(DateUtils.getDateTime());
		theObj.setStatus("1");
		paperDao.insertPaper(theObj);
	}

	/**
	 * 根据ID查询paper
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-9 下午4:07:27
	 * @param id
	 * @return
	 */
	public Paper get(String id) {
		// TODO Auto-generated method stub
		return paperDao.getDetailPaper(id);
	}

	/**
	 * 问卷选题答案列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-9 下午4:16:45
	 * @param id
	 * @return
	 */
	public List<PaperResult> findResultList(String id) {
		// TODO Auto-generated method stub
		return paperDao.findResultList(id);
	}

	/**
	 * 问卷题目列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-9 下午4:14:11
	 * @param id
	 * @return
	 */
	public List<PaperQues> findQuestionList(String id) {
		// TODO Auto-generated method stub
		return paperDao.findQuestionList(id);
	}

	/**
	 * 问卷参与总人数
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-9 下午4:21:18
	 * @param id
	 * @return
	 */
	public Integer getJoinTotal(String id) {
		// TODO Auto-generated method stub
		return paperDao.getJoinTotal(id);
	}

	/**
	 * 获取问卷内容及答案列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-10 下午5:09:44
	 * @param id
	 * @return
	 */
	public List<PaperQuesAns> findAnswerList(String id) {
		// TODO Auto-generated method stub
		return paperDao.findAnswerList(id);
	}

	/**
	 * 保存答卷
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-11 上午11:16:33
	 * @param paperQuesAns
	 * @param loginName
	 * @param level_code
	 */
	@Transactional(readOnly = false)
	public void addsaveAnswer(Answer newObj) {
		
		Answer oldObj = paperDao.findAnswerBy3Id(newObj);
		if(oldObj != null){
			newObj.setId(oldObj.getId());
			paperDao.updateSaveAnswer(newObj);
		}else{
			paperDao.addsaveAnswer(newObj);
		}
		
		
	}
	

}

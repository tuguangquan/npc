package com.ctgu.npc.business.inform.service;

import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.inform.entity.*;
import com.ctgu.npc.business.inform.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class TestService {
	
	@Autowired
	public TestMapper testDao;
	

	public PagesUtil<Discussion> getListDiscussion(String level_code,
			int curPage) {
		// TODO Auto-generated method stub
		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsDiscussion( level_code);
		
		PagesUtil<Discussion> pagesUtil = new PagesUtil<Discussion>();
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
		
		List<Discussion> lists = new ArrayList<Discussion>();
		
		lists = testDao.getListDiscussion(map);
		
		pagesUtil.setLists(lists);
		return pagesUtil;
	}

	private int getRowsDiscussion(String level) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("userID", userID);
		map.put("level", level);
		return testDao.getRowsDiscussion(map);
	}

	/**
	 * 根据id查询测评详情
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 上午11:20:40
	 * @param id
	 * @return
	 */
	public Discussion getDetailDiscussion(String id) {
		// TODO Auto-generated method stub
		return testDao.getDetailDiscussion(id);
	}

	/**
	 * 根据id查询该测评的所有测评单位
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 上午11:21:06
	 * @param id
	 * @return
	 */
	public List<DisTest> findListDisTest(String id) {
		// TODO Auto-generated method stub
		return testDao.findListDisTest(id);
	}

	/**
	 * 根据userId,discussion id查询该测评的选择结果
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 上午11:22:02
	 * @param level
	 * @param id
	 * @param loginName
	 * @return
	 */
	public DisTestChoice getDisTestChoice(String id, String userId) {
		// TODO Auto-generated method stub
		DisTestChoice theObj = new DisTestChoice();
		theObj.setDisId(StringUtils.toInteger(id));
		theObj.setNpcId(StringUtils.toInteger(userId));
		
		return theObj;
	}

	public List<DisTestChoice> findListDisTestChoice(DisTestChoice dtc) {
		// TODO Auto-generated method stub
		return testDao.findListDisTestChoice(dtc);
	}


	/**
	 * 查询本人的该次测试的选择
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 上午11:42:50
	 * @param level
	 * @param id
	 * @param loginName
	 * @return
	 */
	public Choice getChoice(String level, String id, String userId) {
		// TODO Auto-generated method stub
		Choice theObj = new Choice();
		theObj.setLevel(level);
		theObj.setDisId(StringUtils.toInteger(id));
		theObj.setNpcId(StringUtils.toInteger(userId));
		return theObj;
	}

	/**
	 * 测评是否提交
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午2:34:24
	 * @param choice
	 * @return
	 */
	public boolean isSubmit(Choice choice) {
		// TODO Auto-generated method stub
		List<Choice> clist = testDao.getListChoice(choice);
		if(clist.size()!=0){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 当前用户是否是已经参与了测评之人
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午2:40:44
	 * @param id
	 * @param level
	 * @param userId
	 * @return
	 */
	public boolean isAnswerUser(String id, String level, String userId) {
		// TODO Auto-generated method stub
		Choice theObj = new Choice();
		theObj.setLevel(level);
		theObj.setDisId(StringUtils.toInteger(id));
		
		List<String> alist = testDao.findUserList(theObj);
		if(alist.contains(userId))
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * Description:查询代表本次测评答题情况 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午4:11:06
	 * @param id
	 * @param userId
	 * @return
	 */
	public List<DisTestChoice> findListDisTestChoiceById(String id,
			String userId) {
		// TODO Auto-generated method stub
		return testDao.findListDisTestChoiceById(id,userId);
	}

	public List<DisResult> findListDisResult(String id) {
		// TODO Auto-generated method stub
		return testDao.findListDisResult(id);
	}

	public Integer getJoinTotal(String id) {
		// TODO Auto-generated method stub
		return  testDao.getJoinTotal(id);
	}
	/**
	 * 保存测评提交
	 * @param choice
	 */
	@Transactional(readOnly = false)
	public void saveSubmitChoice(Choice choice) {
		// TODO Auto-generated method stub
		testDao.saveSubmitChoice(choice);
	}


	
	
	
	
	
}

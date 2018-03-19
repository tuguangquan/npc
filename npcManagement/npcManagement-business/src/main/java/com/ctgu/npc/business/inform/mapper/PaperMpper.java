package com.ctgu.npc.business.inform.mapper;

import com.ctgu.npc.business.inform.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PaperMpper {

	/**
	 * 获取问卷列表  level_code
	 * @param map
	 * @return
	 */
	List<Paper> getListPaper(Map<String, Object> map);

	/**
	 * 获取问卷列表记录数  level_code
	 * @param map
	 * @return
	 */
	int getRowsPaper(Map<String, Object> map);

	/**
	 * 问卷详细
	 * @param id
	 * @return
	 */
	Paper getDetailPaper(@Param("id") String id);

	/**
	 * 添加问卷
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-5-6 上午10:52:52
	 * @param theObj
	 */
	int insertPaper(Paper theObj);

	/**
	 * 问卷题目列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-9 下午4:14:35
	 * @param id
	 * @return
	 */
	List<PaperQues> findQuestionList(@Param("id") String id);

	/**
	 * 问卷选题答案列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-9 下午4:16:06
	 * @param id
	 * @return
	 */
	List<PaperResult> findResultList(@Param("id") String id);

	/**
	 * 问卷参与总人数
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-9 下午4:21:38
	 * @param id
	 * @return
	 */
	Integer getJoinTotal(@Param("id") String id);

	/**
	 * 获取问题内容及答案列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-10 下午5:11:09
	 * @param id
	 * @return
	 */
	List<PaperQuesAns> findAnswerList(@Param("id") String id);

	/**
	 * 保存答卷
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-11 上午11:23:52
	 * @param answer
	 */
	void addsaveAnswer(Answer answer);

	/**
	 * 根据paperID,questionID,userID查询Answer对象
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-11 下午4:05:10
	 * @param answer
	 * @return
	 */
	Answer findAnswerBy3Id(Answer answer);

	/**
	 * 更新Answer
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-11 下午4:10:31
	 * @param answer
	 */
	void updateSaveAnswer(Answer answer);

	//Paper get(String id);
	

}

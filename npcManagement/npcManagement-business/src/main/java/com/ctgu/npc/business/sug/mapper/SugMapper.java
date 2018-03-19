package com.ctgu.npc.business.sug.mapper;


import com.ctgu.npc.business.sug.entity.*;
import com.ctgu.npc.business.sys.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SugMapper {

	/**
	 * 根据suggestion的id查找suggestion
	 * @param id
	 * @return
	 */
	Suggestion getSugById(String id);
	

	/**
	 * 根据id及sug_type查询建议议案的详细-我领衔
	 * @param id
	 * @param sug_type
	 * @return
	 */
	Suggestion getSuggestionByIdType(@Param("id") String id, @Param("sug_type") String sug_type);


	/*
	 * 
	 * 根据suggestion的id集查找联名的UsersList
	 */
	List<Users> getSecondWriter(@Param("ids") String ids);

	/**领衔
	 * 根据sug查找该议案领衔人的所以领衔的suggestion的List
	 * @param sug
	 * @return
	 */
	List<Suggestion> myHeadSugList(Suggestion sug);

	/**
	 * 根据firstWriterID查找我领衔的suggestion的List
	 * @param firstWriterID
	 * @return
	 */
	List<Suggestion> myHeadSugListByFirstId(String firstWriterID);

	int getRowCount(String firstWriterID);

	List<Suggestion> getListByMap(Map<String, Object> map);

	String getStates(@Param("status") String status, @Param("sug_type") String sug_type);

	String getStatus(@Param("status") String status, @Param("type") String type);

	/**领衔人
	 * 根据系统级别及领衔人id查询所有的记录总数
	 * @param firstWriterID
	 * @param level_code
	 * @return
	 */
	int getRowTotal(@Param("firstWriterID") String firstWriterID, @Param("level_code") String level_code);

	/**领衔人
	 * 根据系统级别及领衔人id查询所有的记录总数
	 * @param firstWriterID
	 * @param level_code
	 * @param type_value 
	 * @return 记录总数
	 */
	int getRowTotalMap(Map<String, Object> map);
	
	
	
	/**联名人
	 * 根据系统级别及联名人id查询所有的记录总数
	 * @param writerID2nd 联名人
	 * @param level_code
	 * @return
	 */
	int getRowTotal2nd(@Param("secondWriterIDS") String secondWriterIDS, @Param("level_code") String level_code);
	
	/**
	 * 根据登录名及系统级别联名人id查询所有的记录总数(我联名)
	 * @param type_value
	 * @param secondWriterIDS
	 * @param level_code
	 * @return
	 */
	int getRowTotal2ndMap(Map<String, Object> map);
	
	
	/**领衔人
	 * 根据系统级别及领衔人id分页查询suggestion的List
	 * @param map
	 * @return
	 */
	List<Suggestion> getListPageByMap(Map<String, Object> map);
	
	/**
	 * 根据领衔人id分页查询suggestion的List
	 * @param map
	 * @return
	 */
	List<Suggestion> getListByIdMap(Map<String, Object> map);

	/**
	 * 根据联名人id分页查询suggestion的List
	 * @param map
	 * @return
	 */
	List<Suggestion> getListPageByMap2nd(Map<String, Object> map);
	
	/**
	 * 根据联名人id及类型值分页查询suggestion的List
	 * @param map
	 * @return
	 */
	List<Suggestion> getListPageByMap2ndType(Map<String, Object> map);

	/**优秀
	 * 根据系统级别分页查询代表的优秀议案建议列表suggestionList
	 * @param map
	 * @return
	 */
	List<Suggestion> getListByLevelMapExcellent(Map<String, Object> map);
	
	List<Suggestion> getListByMapExcellentType(Map<String, Object> map);
	
	

	/**重点
	 * 根据系统级别分页查询代表的重点议案建议列表suggestionList
	 * @param map
	 * @return
	 */
	List<Suggestion> getListByLevelMapEmphasis(Map<String, Object> map);
	

	/**代表们
	 * 根据系统级别分页查询本级所有的议案建议列表suggestionList
	 * @param map
	 * @return
	 */
	List<Suggestion> getListByLevelMapAll(Map<String, Object> map);
	
	/**代表们
	 * 根据系统级别分页查询本级所有的议案建议列表suggestionList
	 * @param map type_value
	 * @return
	 */
	List<Suggestion> getListAllMapType(Map<String, Object> map);
	
	/**
	 * 根据系统级别查询总记录数-优秀
	 * @param level_code
	 * @return
	 */
	int getRowCountByLevelExcellent(@Param("level_code") String level_code);

	/**
	 * 根据系统级别查询总记录数-优秀
	 * @param level_code,type_value
	 * @return
	 */
	int getRowsExcellentMap(Map<String, Object> map);
	
	
	
	/**
	 * 根据级别查询总记录数-重点
	 * @param level_code
	 * @return
	 */
	int getRowCountByLevelEmphasis(@Param("level_code") String level_code);
	/**
	 * 根据级别查询总记录数-重点
	 * @param level_code,type_value
	 * @return
	 */
	int getRowsEmphasisMap(Map<String, Object> map);

	/**
	 * 根据系统级别查询本级别所有代表议案总记录数-代表们
	 * @param level_code
	 * @return
	 */
	int getRowCountByLevelAll(@Param("level_code") String level_code);

	/**
	 * 根据系统级别查询本级别所有代表议案总记录数-代表们
	 * @param level_code,type_value
	 * @return
	 */
	int getRowsAllMap(Map<String, Object> map);
	
	
	/**
	 * 根据系统级别和代表团id查询suggestionList列表
	 * @param map
	 * @return
	 */
	List<Suggestion> getListByOfficeId(Map<String, Object> map);

	/**
	 * 根据系统级别和代表团id查询总记录数- 代表团
	 * @param map
	 * @return
	 */
	int getRowsByOfficeId(Map<String, Object> map);
	/**
	 * 根据系统级别和代表团id查询总记录数- 代表团
	 * @param map type_value
	 * @return
	 */
	int getRowsByOfficeIdType(Map<String, Object> map);

	/**
	 * 新增议案建议-我领衔
	 * @param sug
	 */
	public int mySugHeadAdd(Suggestion sug);


	/**
	 * 建议议案的转交信息
	 * @param id
	 * @return
	 */
	List<TransferForm> getListTransfer(@Param("sugID") String sugID);

	/**
	 * 建议议案的交办信息
	 * @param sugID
	 * @return
	 */
	List<AssignForm> getListAssign(@Param("sugID") String sugID);


	/**
	 * 建议议案的承办信息
	 * @param sugID
	 * @return
	 */
	List<UnitAnswerForm> getListUnitAnswer(@Param("sugID") String sugID);

	/**
	 * === 审核列表
	 * @param map
	 * @return
	 */
	List<Suggestion> getListByMapCheck(Map<String, Object> map);

	/**
	 * 审核列表记录数
	 * @param map
	 * @return
	 */
	int getRowsCheckList(Map<String, Object> map);

	/**
	 * 立案列总记录数
	 * @param map
	 * @return
	 */
	int getRowsPutonList(Map<String, Object> map);


	/**
	 * 立案列表
	 * @param map
	 * @return
	 */
	List<Suggestion> getListByMapPuton(Map<String, Object> map);


	/**
	 * 获取立案上传文件列表
	 * @param sugID
	 * @return
	 */
	List<FilePuton> getListFilePuton(@Param("sugID") String sugID);


	/**
	 * 建议议案转交记录数
	 * @param map
	 * @return
	 */
	int getRowsTransferList(Map<String, Object> map);

	/**
	 * 建议议案转交列表
	 * @param map
	 * @return
	 */
	List<TransferForm> sugTransferList(Map<String, Object> map);

	/**
	 * ===交办任务列表记录数===
	 * @param map
	 * @return
	 */
	int getRowsAssignList(Map<String, Object> map);
	/**
	 * ===交办任务列表记录数===
	 * @param map
	 * @return
	 */
	int getRowsAssignListBackFlag(Map<String, Object> map);

	/**
	 * === 交办任务列表 ===
	 * @param map
	 * @return
	 */
	List<AssignForm> sugAssignList(Map<String, Object> map);
	
	/**
	 * === 交办任务列表 ===
	 * @param map
	 * @return
	 */
	List<AssignForm> sugAssignListBackFlag(Map<String, Object> map);


	/**
	 * 获取上次主办单位的已经办理答复的任务
	 * @param id
	 * @return
	 */
	UnitAnswerForm getLastUnitAnswerForm(int id);


	/**
	 * 插入一条主办单位任务
	 * @param unitAnswerForm
	 */
	void insertUnitAnswerForm(UnitAnswerForm unitAnswerForm);


	void insertNote(@Param("senderID") String senderID, @Param("content") String content, @Param("sendTime") String sendTime, @Param("receiverId") String receiverId, @Param("level") String level);

	/**
	 * 保存评价内容
	 * @param suggestion
	 * @return
	 */
	public int saveEvaluateForm(Suggestion suggestion);

	/**
	 *  根据年度级别类型查询
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-8-29 下午3:38:18
	 * @param sug
	 * @return
	 */
	Suggestion selectCurrentYearBigCode(Suggestion sug);

	/**
	 * 保存审核
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-8-29 下午3:38:02
	 * @param sug
	 */
	void saveCheckForm(Suggestion sug);

	/**
	 * 插入转交记录
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-8-29 下午3:39:51
	 * @param transferForm
	 */
	void insertTransferForm(TransferForm transferForm);


	/**
	 * 更新议案建议编号
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-8-29 下午3:52:27
	 * @param id
	 * @param code
	 * @return
	 */
	public int updateSugCode(@Param("id") String id, @Param("code") String code);


	/**
	 * 删除转交历史记录
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-8-29 下午3:55:27
	 * @param sugID
	 * @return
	 */
	public int delTransferList(String sugID);

	/**
	 * 沟通信息
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-27 上午9:37:10
	 * @param id
	 * @return
	 */
	CommunicationForm getCommunicationForm(@Param("id") String id);


	




	


	


	


	




	

	

	

	
	
	



	


		

	

}

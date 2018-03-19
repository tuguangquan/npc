package com.ctgu.npc.business.inform.mapper;


import com.ctgu.npc.business.inform.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TestMapper {

	
	
	/*public List<Discussion> findDiscussionList(Discussion dis);
	
	public int insertDis(Discussion dis);
	
	public int editDis(Discussion dis);
	
	public int delete(@Param("id")String id);
	
	public int updateStatus(Discussion dis);
	
	public int insertTest(@Param("tlist")List<TestUnit> tlist);
	
	public List<DisTest> findTestList(String id);
	
	public List<Discussion> findTestListAll(Discussion dis);
	
	public List<Choice> findIsChoiceList(Choice choice);
	
	public List<DisTestChoice> findChoiceList(DisTestChoice dtc);
	
	public List<String> findUserList(Choice choice);
	
	public int insertChoice(Choice choice);
	
	public List<DisResult> findResultList(String id);
	
	public Integer getJoinTotal(String id);
	
	public List<DisTotal> findDisTotalList(String id);
	
	public List<DisTestChoice> findDetailList(@Param("disId")String disId,@Param("npcId")String npcId);
	
	public List<DisChoice> findRemarkList(String id, @Param("testId")String testId);
	
	public String getUnit(@Param("id")String id, @Param("testId")String testId);
*/
	public List<Discussion> getListDiscussion(Map<String, Object> map);
	
	public Discussion getDetailDiscussion(@Param("id") String id);

	public int getRowsDiscussion(Map<String, Object> map);

	/**
	 * 根据id查询本测评的所有测评单位
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午2:15:05
	 * @param id
	 * @return
	 */
	public List<DisTest> findListDisTest(@Param("id") String id);

	/**
	 * 本人本测评的所有选项的结果
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午2:50:52
	 * @param dtc
	 * @return
	 */
	public List<DisTestChoice> findListDisTestChoice(DisTestChoice dtc);

	/**
	 * 本人本测评在该系统级别的所有选项
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午2:51:45
	 * @param choice
	 * @return
	 */
	public List<Choice> getListChoice(Choice choice);

	/**
	 * 查询该测评的所有用户id
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午2:40:05
	 * @param theObj
	 * @return
	 */
	public List<String> findUserList(Choice theObj);

	/**
	 * 
	 * Description: 查询代表本次测评答题详细
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-21 下午4:12:20
	 * @param id
	 * @param userId
	 * @return
	 */
	public List<DisTestChoice> findListDisTestChoiceById(@Param("disId") String disId,
														 @Param("npcId") String npcId);

	public List<DisResult> findListDisResult(@Param("id") String id);

	public Integer getJoinTotal(@Param("id") String id);

	/**
	 * 
	 * Description:保存提交测试 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-23 上午10:38:21
	 * @param choice
	 */
	public void saveSubmitChoice(Choice choice);
}

package com.ctgu.npc.business.perform.mapper;

import com.ctgu.npc.business.perform.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 履职服务
 * @author 旺旺
 *
 */
public interface PerformMapper {


	/**
	 * == 我的代表工作分页列表
	 * @param map
	 * @return
	 */
	public List<JobReport> getListmyJobReport(Map<String, Object> map);

	/**
	 * 我的代表工作总记录数
	 * @param map
	 * @return
	 */
	public int getRowsMyJobReport(Map<String, Object> map);


	
	/**
	 * 我的代表活动-分页列表
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public List<PerformReport> getListMyPerformAct(Map<String, Object> map);

	/**
	 * 我的代表活动-总记录数
	 * @param loginName
	 * @param curPage
	 * @param level_code
	 * @return
	 */
	public int getRowsMyPerformAct(Map<String, Object> map);

	/**
	 * ===我的代表活动--详细信息
	 * @param id
	 * @return
	 */
	public PerformReport getInfoPerformReport(@Param("id") String id);

	/**
	 * ===我的履职报告
	 * @param map
	 * @return
	 */
	public List<WorkReport> getListmyWorkReport(Map<String, Object> map);

	/**
	 * ===我的履职报告记录数
	 * @param map
	 * @return
	 */
	public int getRowsmyWorkReport(Map<String, Object> map);

	/**
	 * ===我的履职报告详细
	 * @param id
	 * @return
	 */
	public WorkReport getInfoMyWorkReport(@Param("id") String id);
	
	/**
	 * === 我的履职统计
	 * @param map
	 * @return
	 */
	public List<MyReportEntry> getListMyReportStatistic(Map<String, Object> map);

	/**
	 * === 我的履职统计记录数
	 * @param map
	 * @return
	 */
	public int getRowsMyReportStatistic(Map<String, Object> map);

	

	/**
	 * ===代表工作查询
	 * @param map
	 * @return
	 */
	public List<JobReport> getListjobSearch(Map<String, Object> map);

	/**
	 * ===代表工作查询记录数
	 * @param map
	 * @return
	 */
	public int getRowsjobSearch(Map<String, Object> map);

	/**
	 * 代表工作查询详细
	 * @param id
	 * @return
	 */
	public JobReport getInfojobSearchDetail(@Param("id") String id);

	/**
	 * 代表活动查询
	 * @param map
	 * @return
	 */
	public List<PerformReport> getListPerformReport(Map<String, Object> map);

	/**
	 * 代表活动查询记录数
	 * @param map
	 * @return
	 */
	public int getRowsPerformReport(Map<String, Object> map);

	/**
	 * 履职报告查询
	 * @param map
	 * @return
	 */
	public List<WorkReport> getListWorkReport(Map<String, Object> map);

	/**
	 * 履职报告查询记录数
	 * @param map
	 * @return
	 */
	public int getRowsWorkReport(Map<String, Object> map);

	/**
	 * 履职报告查询详细
	 * @param id
	 * @return
	 */
	public WorkReport getInfoworkSearchDetail(@Param("id") String id);

	/**
	 * === 我的代表工作类型列表
	 * @param level_code
	 * @param string
	 * @return
	 */
	public List<ActType> getActTypeList(@Param("level") String Level, @Param("flag") String flag);
	
	/**
	 * === 获取登记活动类型列表
	 * @param actType
	 * @return
	 */
	public List<ActType> actTypeList(@Param("level") String Level, @Param("flag") String flag);

	/**
	 * 登记代表活动类型列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-4-14 上午10:34:04
	 * @param level_code
	 * @return
	 */
	public List<ActType> getListActType(String level_code);
	
	
	/**
	 * === 新增我的代表工作
	 * @param jobReport
	 */
	public void insertJobReport(JobReport jobReport);

	/**
	 * 新增我的代表活动
	 * @param performReport
	 */
	public void insertPerformReport(PerformReport performReport);
	
	/**
	 * === 获取登记类型
	 * @param id
	 * @return
	 */
	public ActType getActTypeByID(@Param("id") String id);
	/**
	 * 查询登记类型===根据type
	 * @param type
	 * @param level 
	 * @return
	 */
	public ActType getActTypeByType(@Param("type") String type, @Param("level") String level);

	/**
	 * 我的代表工作列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-8 上午9:57:05
	 * @param jobReport
	 * @return
	 */
	public List<JobReport> myJobReportList(JobReport jobReport);

	/**
	 * 更新代表工作
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-8 上午10:02:41
	 * @param jobReport
	 */
	public void updateJobReport(JobReport jobReport);

	/**
	 * 我的代表活动列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-8 上午10:57:06
	 * @param performReport
	 * @return
	 */
	public List<PerformReport> myPerformReportList(PerformReport performReport);

	/**
	 * 更新我的代表活动
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-8 上午10:58:07
	 * @param performReport
	 */
	public void updatePerformReport(PerformReport performReport);


	
	
	

}

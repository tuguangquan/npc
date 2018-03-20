package com.ctgu.npc.business.inform.mapper;


import com.ctgu.npc.business.inform.entity.Opinion;
import com.ctgu.npc.business.inform.entity.Report;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface OpinionMapper {


	/**
	 * 根据系统级别分页查询Opinion意见列表
	 */
	 public List<Opinion> getListOpinion(Map<String, Object> map);

	
	/**
	 * 根据系统级别查询Opinion总记录数
	 * @param map
	 * @return
	 */
	public int getRowsByLevelOpinion(Map<String, Object> map);


	/**
	 * 根据opinion的id查询详细
	 */
	public Opinion getDetailByIdOpinion(@Param("opin_id") String opin_id);


	/**
	 * 根据系统级别分页查询Report列表
	 */
	public List<Report> getListReport(Map<String, Object> map);


	/**
	 * 根据系统级别查询Report总记录数
	 * @param map
	 * @return
	 */
	public int getRowsByLevelReport(Map<String, Object> map);


	/**
	 * 根据Report的id查询详细
	 */
	public Report getDetailByIdReport(@Param("repot_id") String repot_id);


	/**
	 * 插入一条填写的意见
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-1 上午9:11:28
	 * @param opinion
	 */
	public void insertOpinion(Opinion opinion);

}

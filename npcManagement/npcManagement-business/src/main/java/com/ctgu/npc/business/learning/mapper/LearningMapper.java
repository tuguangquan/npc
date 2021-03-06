package com.ctgu.npc.business.learning.mapper;

import com.ctgu.npc.business.learning.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LearningMapper {

	/**
	 * ===获取人大规章制度分页列表
	 * @param map
	 * @return
	 */
	public List<Rule> getListNpcRule(Map<String, Object> map);

	/**
	 * === 获取人大规章制度的记录数
	 * @param rule
	 * @return
	 */
	public int getRowsNpcRule(Rule rule);

	/**
	 * 制度详细
	 * @param id
	 * @return
	 */
	public Rule getInfoRule(@Param("id") String id);

	/**
	 * 履职学习培训资料列表
	 * @param map
	 * @return
	 */
	public List<Material> getListMaterial(Map<String, Object> map);

	/**
	 * 履职学习培训资料记录数
	 * @param theObj
	 * @return
	 */
	public int getRowsMaterial(Material theObj);

	/**
	 * 履职学习培训资料详细
	 * @param id
	 * @return
	 */
	public Material getInfoMaterial(@Param("id") String id);

	
	/**
	 * 履职活动培训列表
	 * @param map
	 * @return
	 */
	public List<Training> getListTraining(Map<String, Object> map);
	/**
	 * 履职活动培训记录数
	 * @param theObj
	 * @return
	 */
	public int getRowsTraining(Training theObj);
	/**
	 * 履职活动培训详细
	 * @param id
	 * @return
	 */
	public Training getInfoTraining(@Param("id") String id);

	
	/**
	 * 优秀议案建议列表
	 * @param map
	 * @return
	 */
	public List<ExceSuggestion> getListExceSuggestion(Map<String, Object> map);

	/**
	 * 优秀议案建议记录 
	 * @param theObj
	 * @return
	 */
	public int getRowsExceSuggestion(ExceSuggestion theObj);
	
	/**
	 * 优秀议案建议详细
	 * @param id
	 * @return
	 */
	public ExceSuggestion getInfoExceSuggestion(@Param("id") String id);

	
	/**
	 * 优秀履职报告详细
	 * @param map
	 * @return
	 */
	public List<ExceWorkReport> getListExceWorkReport(Map<String, Object> map);

	/**
	 * 优秀履职报告记录数
	 * @param theObj
	 * @return
	 */
	public int getRowsExceWorkReport(ExceWorkReport theObj);

	/**
	 * 优秀履职报告详细
	 * @param id
	 * @return
	 */
	public ExceWorkReport getInfoExceWorkReport(@Param("id") String id);

	
	/**
	 * 优秀视察调研报告列表
	 * @param map
	 * @return
	 */
	/*List<ExceReport> getListExceReport(Map<String, Object> map);*/

	/**
	 * 优秀视察调研报告记录数
	 * @param theObj
	 * @return
	 */
	/*int getRowsExceReport(ExceReport theObj);*/

	/**
	 * 优秀视察调研报告详细
	 * @param id
	 * @return
	 */
	/*ExceReport getInfoExceReport(@Param("id") String id);*/

}

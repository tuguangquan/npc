package com.ctgu.npc.business.inform.entity;

import java.util.List;

/**
 * 测评结果统计
 * Title:TestResult 
 * Description: 
 * Company: ctgu 
 * @author : youngmien
 * @date  2016-9-21 上午11:05:04
 */
public class DisTestResult {
	
	String Title;
	String description;
	
	Integer joinTotal;//总数
	List<DisResultStastic> list;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getJoinTotal() {
		return joinTotal;
	}
	public void setJoinTotal(Integer joinTotal) {
		this.joinTotal = joinTotal;
	}
	public List<DisResultStastic> getList() {
		return list;
	}
	public void setList(List<DisResultStastic> list) {
		this.list = list;
	}
	
}

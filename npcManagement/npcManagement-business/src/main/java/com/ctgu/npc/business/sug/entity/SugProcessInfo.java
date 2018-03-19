package com.ctgu.npc.business.sug.entity;

import java.util.List;

public class SugProcessInfo {
	
	List<TransferForm> lists01;//转交信息列表
	List<AssignForm> lists02; //交办信息列表
	List<UnitAnswerForm> lists03; //承办信息列表
	
	public List<TransferForm> getLists01() {
		return lists01;
	}
	public void setLists01(List<TransferForm> lists01) {
		this.lists01 = lists01;
	}
	public List<AssignForm> getLists02() {
		return lists02;
	}
	public void setLists02(List<AssignForm> lists02) {
		this.lists02 = lists02;
	}
	public List<UnitAnswerForm> getLists03() {
		return lists03;
	}
	public void setLists03(List<UnitAnswerForm> lists03) {
		this.lists03 = lists03;
	}
	


}

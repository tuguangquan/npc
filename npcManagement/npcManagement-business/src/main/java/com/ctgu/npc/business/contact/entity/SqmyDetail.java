package com.ctgu.npc.business.contact.entity;


import com.ctgu.npc.business.common.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 社情民意详情
 * Title:SqmyDetail 
 * Description: 
 * Company: ctgu 
 * @author : youngmien
 * @date  2017-7-26 上午10:10:54
 */
public class SqmyDetail extends BaseEntity<SqmyDetail> implements Serializable{

	private Sqmy sqmy;
	private LeaveMessage leaveMessage;
	private List<AnswerSqmy> aList;
	
	
	public Sqmy getSqmy() {
		return sqmy;
	}

	public void setSqmy(Sqmy sqmy) {
		this.sqmy = sqmy;
	}

	public LeaveMessage getLeaveMessage() {
		return leaveMessage;
	}

	public void setLeaveMessage(LeaveMessage leaveMessage) {
		this.leaveMessage = leaveMessage;
	}

	public List<AnswerSqmy> getaList() {
		return aList;
	}

	public void setaList(List<AnswerSqmy> aList) {
		this.aList = aList;
	}

	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		
	}
	

}

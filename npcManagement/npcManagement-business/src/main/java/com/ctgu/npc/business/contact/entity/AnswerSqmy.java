package com.ctgu.npc.business.contact.entity;

import com.ctgu.npc.business.common.persistence.BaseEntity;

import java.io.Serializable;

/**
 * 回复社情民意
 * Title:AnswerSqmy 
 * Description: 
 * Company: ctgu 
 * @author : youngmien
 * @date  2017-7-26 上午9:49:10
 */
public class AnswerSqmy extends BaseEntity<AnswerSqmy> implements Serializable{

	private String sqmyID;
	private String assignUnitID;
	private String operatorID;
	private String operatorDate;
	private String status;
	private String backReason;

	private String assignUnit;
	private String operatorName;
	
	public String getSqmyID() {
		return sqmyID;
	}

	public void setSqmyID(String sqmyID) {
		this.sqmyID = sqmyID;
	}

	public String getAssignUnitID() {
		return assignUnitID;
	}

	public void setAssignUnitID(String assignUnitID) {
		this.assignUnitID = assignUnitID;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperatorDate() {
		return operatorDate;
	}

	public void setOperatorDate(String operatorDate) {
		this.operatorDate = operatorDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	public String getAssignUnit() {
		return assignUnit;
	}

	public void setAssignUnit(String assignUnit) {
		this.assignUnit = assignUnit;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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

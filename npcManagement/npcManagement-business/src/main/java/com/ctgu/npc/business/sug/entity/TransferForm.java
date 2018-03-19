package com.ctgu.npc.business.sug.entity;

/**
 * 转交信息
 * @author 旺旺
 *
 */
public class TransferForm {

	private static final long serialVersionUID = 1L;
	private String id;
	private Suggestion sug;
	private String status;
	private String backFlag;
	private Integer unitID;
	private String operatorID;
	private String operatorDate;
	private String createDate;
	private String remark;

	private String unitName;
	private String operatorName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TransferForm(){
		super();
	}
	public TransferForm(String id){
		super();
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public String getOperatorDate() {
		return operatorDate;
	}
	
	
	public String getOperatorID() {
		return operatorID;
	}
	public String getOperatorName() {
		return operatorName;
	}
	

	public String getRemark() {
		return remark;
	}

	public String getStatus() {
		return status;
	}

	public Suggestion getSug() {
		return sug;
	}

	public Integer getUnitID() {
		return unitID;
	}

	public String getUnitName() {
		return unitName;
	}
	

	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setOperatorDate(String operatorDate) {
		this.operatorDate = operatorDate;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSug(Suggestion sug) {
		this.sug = sug;
	}

	public void setUnitID(Integer unitID) {
		this.unitID = unitID;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}

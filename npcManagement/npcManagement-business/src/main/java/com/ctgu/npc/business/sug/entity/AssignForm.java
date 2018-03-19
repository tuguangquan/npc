package com.ctgu.npc.business.sug.entity;
/**
 * 交办信息
 * @author 旺旺
 *
 */
public class AssignForm {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private Suggestion sug;;
	private String status;
	private String backFlag;
	private Integer unitID;
	private String unitID1;
	private String unitID2;
	private Integer operatorID;
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
	public AssignForm(){
		super();
	}
	
	
	public AssignForm(String id){
		super();
		this.id = id;
	}
	
	

	public Suggestion getSug() {
		return sug;
	}

	public void setSug(Suggestion sug) {
		this.sug = sug;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUnitID() {
		return unitID;
	}

	public void setUnitID(Integer unitID) {
		this.unitID = unitID;
	}

	public Integer getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(Integer operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperatorDate() {
		return operatorDate;
	}

	public void setOperatorDate(String operatorDate) {
		this.operatorDate = operatorDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	public String getBackFlag() {
		return backFlag;
	}


	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}


	public String getUnitID1() {
		return unitID1;
	}


	public void setUnitID1(String unitID1) {
		this.unitID1 = unitID1;
	}


	public String getUnitID2() {
		return unitID2;
	}


	public void setUnitID2(String unitID2) {
		this.unitID2 = unitID2;
	}


}

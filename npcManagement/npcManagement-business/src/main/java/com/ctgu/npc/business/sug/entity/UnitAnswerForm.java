package com.ctgu.npc.business.sug.entity;

/**
 * 承办信息
 * @author 旺旺
 *
 */
public class UnitAnswerForm {

	private static final long serialVersionUID = 1L;
	private String id;
	private Suggestion sug;
	private String status;
	private Integer unitID;
	private Integer assignUnitID;
	private Integer operatorID;
	private String operatorDate;
	private String createDate;
	private String remark;
	private String filePath;
	private String picPath;
	private String leader;
	private String job;
	private String tel;
	private String flag;
	private String privateFlag;
	private String evaluate;
	private String communicationID;
	private String backFlag;
	
	
	private String unitName;
	private String assignUnitName;
	private String operatorName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UnitAnswerForm(){
		super();
	}
	
	public UnitAnswerForm(String id){
		super();
		this.id = id;
	}
	
	
	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	public String getAssignUnitName() {
		return assignUnitName;
	}

	public void setAssignUnitName(String assignUnitName) {
		this.assignUnitName = assignUnitName;
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

	public Integer getAssignUnitID() {
		return assignUnitID;
	}

	public void setAssignUnitID(Integer assignUnitID) {
		this.assignUnitID = assignUnitID;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPrivateFlag() {
		return privateFlag;
	}

	public void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
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

	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCommunicationID() {
		return communicationID;
	}

	public void setCommunicationID(String communicationID) {
		this.communicationID = communicationID;
	}

}

package com.ctgu.npc.business.contact.entity;

import com.ctgu.common.persistence.BaseEntity;

import java.io.Serializable;

/**
 * 社情民意== 实体类
 * Title:Sqmy 
 * Description: 
 * Company: ctgu 
 * @author : youngmien
 * @date  2017-7-26 上午8:31:56
 */
public class Sqmy extends BaseEntity<Sqmy> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String content;
	private String firstWriterID;
	private String year;
	private String qzmsgID;
	private LeaveMessage leaveMsg;
	private String manageUnitID;
	private String manageUnit;
	private String createTime;
	private String status;
	private String resevaluation;
	private String evaluate;
	private String level;
	private String fileName;
	private String type;
	private String filePath;
	private String picPath;
	private String leaderName;
	private String tel;
	private String leaderZw;
	private String firstWriter;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFirstWriterID() {
		return firstWriterID;
	}

	public void setFirstWriterID(String firstWriterID) {
		this.firstWriterID = firstWriterID;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQzmsgID() {
		return qzmsgID;
	}

	public void setQzmsgID(String qzmsgID) {
		this.qzmsgID = qzmsgID;
	}

	public LeaveMessage getLeaveMsg() {
		return leaveMsg;
	}

	public void setLeaveMsg(LeaveMessage leaveMsg) {
		this.leaveMsg = leaveMsg;
	}

	public String getManageUnitID() {
		return manageUnitID;
	}

	public void setManageUnitID(String manageUnitID) {
		this.manageUnitID = manageUnitID;
	}

	public String getManageUnit() {
		return manageUnit;
	}

	public void setManageUnit(String manageUnit) {
		this.manageUnit = manageUnit;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResevaluation() {
		return resevaluation;
	}

	public void setResevaluation(String resevaluation) {
		this.resevaluation = resevaluation;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLeaderZw() {
		return leaderZw;
	}

	public void setLeaderZw(String leaderZw) {
		this.leaderZw = leaderZw;
	}

	public String getFirstWriter() {
		return firstWriter;
	}

	public void setFirstWriter(String firstWriter) {
		this.firstWriter = firstWriter;
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

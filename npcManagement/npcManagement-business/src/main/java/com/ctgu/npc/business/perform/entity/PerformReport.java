package com.ctgu.npc.business.perform.entity;

import java.io.Serializable;

/**
 * 我的代表活动
 * @author 旺旺
 *
 */
public class PerformReport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String year;//年度
	private String secondary;
	private String sequence;
	private Integer userID;//代表
	private Integer teamID;//代表团
	private Integer createUserID;//创建人
	private String createDate;
	private String place;//地点
	private String date;//日期
	private String content;//内容
	private String type;//登记类型
	private Integer score;//分数
	private String privateFlag;//是否公开 1是2否
	private String excellentFlag;//是否优秀 1是 2否
	private String level;//系统级别
	
	private String name;
	private String mission;
	private String createUser;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSecondary() {
		return secondary;
	}
	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getTeamID() {
		return teamID;
	}
	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}
	public Integer getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(Integer createUserID) {
		this.createUserID = createUserID;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getPrivateFlag() {
		return privateFlag;
	}
	public void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
	}
	public String getExcellentFlag() {
		return excellentFlag;
	}
	public void setExcellentFlag(String excellentFlag) {
		this.excellentFlag = excellentFlag;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMission() {
		return mission;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@Override
	public String toString() {
		return "PerformReport [userID=" + userID + ", createDate=" + createDate
				+ ", place=" + place + ", content=" + content + ", type="
				+ type + ", level=" + level + ", name=" + name + "]";
	}
	
	

}

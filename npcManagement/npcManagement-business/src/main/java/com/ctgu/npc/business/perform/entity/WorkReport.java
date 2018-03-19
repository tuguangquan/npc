package com.ctgu.npc.business.perform.entity;

/**
 * 我的述职报告
 * @author 旺旺
 *
 */
public class WorkReport {

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;//标题
	private String content;//内容
	private Integer userID;//代表ID
	private Integer teamID;//代表团ID
	private Integer createUserID;//创建者
	private String createDate;//创建日期
	private String privateFlag;//是否公开 1是2否
	private String excellentFlag;//是否优秀 1是2否
	private String year;//年度
	private String level;//系统级别
	
	private String name;//姓名
	private String mission;//代表团
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrivateFlag() {
		return privateFlag;
	}

	public void setPrivateFlag(String privateFlag) {
		this.privateFlag = privateFlag;
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

	public String getExcellentFlag() {
		return excellentFlag;
	}

	public void setExcellentFlag(String excellentFlag) {
		this.excellentFlag = excellentFlag;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


}

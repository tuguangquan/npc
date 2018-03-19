package com.ctgu.npc.business.perform.entity;

import java.io.Serializable;

/**
 * 代表工作
 * @author 旺旺
 *
 */
public class JobReport implements Serializable {
	


	private static final long serialVersionUID = 1L;
	private String id;
	private String year;//年度
	private String secondary;
	private String sequence;
	private Integer userID;//代表
	private Integer teamID;//代表团
	private Integer createUserID;//创建人
	private String createDate;
	private Integer headSugNum;
	private Integer headSugScore;
	private Integer headProNum;
	private Integer headProScore;
	private Integer joinSugNum;
	private Integer joinSugScore;
	private Integer joinProNum;
	private Integer joinProScore;
	private Integer talkNum;
	private Integer talkScore;
	private Integer meetingNum;//会议次数
	private Integer meetingScore;
	
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

	public Integer getHeadSugNum() {
		return headSugNum;
	}

	public void setHeadSugNum(Integer headSugNum) {
		this.headSugNum = headSugNum;
	}

	public Integer getHeadProNum() {
		return headProNum;
	}

	public void setHeadProNum(Integer headProNum) {
		this.headProNum = headProNum;
	}

	public Integer getJoinSugNum() {
		return joinSugNum;
	}

	public void setJoinSugNum(Integer joinSugNum) {
		this.joinSugNum = joinSugNum;
	}

	public Integer getJoinProNum() {
		return joinProNum;
	}

	public void setJoinProNum(Integer joinProNum) {
		this.joinProNum = joinProNum;
	}

	public Integer getTalkNum() {
		return talkNum;
	}

	public void setTalkNum(Integer talkNum) {
		this.talkNum = talkNum;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public Integer getHeadSugScore() {
		return headSugScore;
	}

	public void setHeadSugScore(Integer headSugScore) {
		this.headSugScore = headSugScore;
	}

	public Integer getHeadProScore() {
		return headProScore;
	}

	public void setHeadProScore(Integer headProScore) {
		this.headProScore = headProScore;
	}

	public Integer getJoinSugScore() {
		return joinSugScore;
	}

	public void setJoinSugScore(Integer joinSugScore) {
		this.joinSugScore = joinSugScore;
	}

	public Integer getJoinProScore() {
		return joinProScore;
	}

	public void setJoinProScore(Integer joinProScore) {
		this.joinProScore = joinProScore;
	}

	public Integer getTalkScore() {
		return talkScore;
	}

	public void setTalkScore(Integer talkScore) {
		this.talkScore = talkScore;
	}

	public Integer getMeetingScore() {
		return meetingScore;
	}

	public void setMeetingScore(Integer meetingScore) {
		this.meetingScore = meetingScore;
	}

	public Integer getMeetingNum() {
		return meetingNum;
	}

	public void setMeetingNum(Integer meetingNum) {
		this.meetingNum = meetingNum;
	}

	@Override
	public String toString() {
		return "JobReport [headSugNum=" + headSugNum + ", headSugScore="
				+ headSugScore + ", headProNum=" + headProNum
				+ ", headProScore=" + headProScore + ", joinSugNum="
				+ joinSugNum + ", joinSugScore=" + joinSugScore
				+ ", joinProNum=" + joinProNum + ", joinProScore="
				+ joinProScore + ", talkNum=" + talkNum + ", talkScore="
				+ talkScore + ", meetingNum=" + meetingNum + ", meetingScore="
				+ meetingScore + ", level=" + level + ", name=" + name + "]";
	}


}

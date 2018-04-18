package com.ctgu.npc.business.sug.dto;

public class SuggestionDto {

	private int id;//ID
	private String title;//标题
	private String firstWriterID;//领衔人
	private String secondWriterIDS;//联名人
	private String firstWriter;//领衔人
	private String mission;//代表团
	private String secondWriter;//联名人
	private String status;//-2未审核-1暂存0退回1审核2转交3交办4办理5评价6办结
	private String type;// 1：建议  2：议案
    private String classify;  // 分类

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstWriterID() {
		return firstWriterID;
	}

	public void setFirstWriterID(String firstWriterID) {
		this.firstWriterID = firstWriterID;
	}

	public String getSecondWriterIDS() {
		return secondWriterIDS;
	}

	public void setSecondWriterIDS(String secondWriterIDS) {
		this.secondWriterIDS = secondWriterIDS;
	}

	public String getFirstWriter() {
		return firstWriter;
	}

	public void setFirstWriter(String firstWriter) {
		this.firstWriter = firstWriter;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getSecondWriter() {
		return secondWriter;
	}

	public void setSecondWriter(String secondWriter) {
		this.secondWriter = secondWriter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}
}

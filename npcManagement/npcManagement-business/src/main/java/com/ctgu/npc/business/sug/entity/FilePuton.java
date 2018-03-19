package com.ctgu.npc.business.sug.entity;

/**
 * 议案上传文件
 * @author 旺旺
 *
 */
public class FilePuton {
	private static final long serialVersionUID = 1L;
	private Integer suggestionID;
	private String fileName;
	private String filePath;
	private Integer userID;
	private String createDate;
	
	
	public Integer getSuggestionID() {
		return suggestionID;
	}

	public void setSuggestionID(Integer suggestionID) {
		this.suggestionID = suggestionID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}



}

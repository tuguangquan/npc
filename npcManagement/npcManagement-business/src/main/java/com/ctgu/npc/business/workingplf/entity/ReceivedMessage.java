package com.ctgu.npc.business.workingplf.entity;


import com.ctgu.npc.business.common.persistence.BaseEntity;

import java.io.Serializable;

public class ReceivedMessage extends BaseEntity<ReceivedMessage> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private String id;
	private String title;
    private String content;
    private String name;
    private String isRead;
    private String typeName;
    private Integer typeId;
    private Integer receiverId;
    private String isAttend;
    private String reason;
    private String readTime;
    private String annex;
    private String createTime;
    
    
/*	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}*/
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public String getIsAttend() {
		return isAttend;
	}
	public void setIsAttend(String isAttend) {
		this.isAttend = isAttend;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReadTime() {
		return readTime;
	}
	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}
	public String getAnnex() {
		return annex;
	}
	public void setAnnex(String annex) {
		this.annex = annex;
	}
	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    

}

package com.ctgu.npc.business.wechat.entity;

import java.io.Serializable;
import java.util.Date;


public class WeiXinChannel implements Serializable {
	private static final long serialVersionUID = 1234234L;
	private String opId;	//微信ID
	private String unionId;	//微信ID
	private Date finalLoginDate;	//最后登录时间
	private Date addDate;	//新增时间
	private int status;

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Date getFinalLoginDate() {
		return finalLoginDate;
	}

	public void setFinalLoginDate(Date finalLoginDate) {
		this.finalLoginDate = finalLoginDate;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

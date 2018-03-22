package com.ctgu.npc.business.wechat.entity;

import java.io.Serializable;
import java.util.Date;


public class WeiXinChannel implements Serializable {
	private static final long serialVersionUID = 1234234L;
	public String memberId;	//成员ID
	public String opId;	//微信ID
	public Date finalLoginDate;	//最后登录时间
	public Date addDate;	//新增时间
	public String remark;	//备注
	public String infos;//微信信息JSON

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}
}

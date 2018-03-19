package com.ctgu.npc.business.inform.entity;

import java.io.Serializable;
import java.util.List;

public class PaperStatis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Paper paper;
	private List<PaperQues> qlist;
	private List<PaperResult> rlist;
	private Integer joinTotal;
	
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	public List<PaperQues> getQlist() {
		return qlist;
	}
	public void setQlist(List<PaperQues> qlist) {
		this.qlist = qlist;
	}
	public List<PaperResult> getRlist() {
		return rlist;
	}
	public void setRlist(List<PaperResult> rlist) {
		this.rlist = rlist;
	}
	public Integer getJoinTotal() {
		return joinTotal;
	}
	public void setJoinTotal(Integer joinTotal) {
		this.joinTotal = joinTotal;
	}
	

}

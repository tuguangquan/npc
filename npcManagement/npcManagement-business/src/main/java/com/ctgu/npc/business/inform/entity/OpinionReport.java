package com.ctgu.npc.business.inform.entity;

import java.io.Serializable;

public class OpinionReport implements Serializable{
	
	Opinion opinion;
	Report report;
	public Opinion getOpinion() {
		return opinion;
	}
	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	

}

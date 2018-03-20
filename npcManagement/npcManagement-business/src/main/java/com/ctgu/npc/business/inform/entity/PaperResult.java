package com.ctgu.npc.business.inform.entity;


import com.ctgu.npc.business.common.persistence.BaseEntity;

public class PaperResult extends BaseEntity<PaperResult> {

	private Integer paperId;
	private Integer questionId;
	private String answer;
	private Integer chooseNum;
	
	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getChooseNum() {
		return chooseNum;
	}

	public void setChooseNum(Integer chooseNum) {
		this.chooseNum = chooseNum;
	}

	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		
	}
	

}

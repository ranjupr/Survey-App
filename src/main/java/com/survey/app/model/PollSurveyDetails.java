package com.survey.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class PollSurveyDetails implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pollSurveyDetailsId;

	@Column
	private int pollSurveyId;

	@Column
	private int answerId;

	@Column
	private String answer;

	@Column
	private int totalPollsToAnswer;

	public int getPollSurveyDetailsId() {
		return pollSurveyDetailsId;
	}

	public void setPollSurveyDetailsId(int pollSurveyDetailsId) {
		this.pollSurveyDetailsId = pollSurveyDetailsId;
	}

	public int getPollSurveyId() {
		return pollSurveyId;
	}

	public void setPollSurveyId(int pollSurveyId) {
		this.pollSurveyId = pollSurveyId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getTotalPollsToAnswer() {
		return totalPollsToAnswer;
	}

	public void setTotalPollsToAnswer(int totalPollsToAnswer) {
		this.totalPollsToAnswer = totalPollsToAnswer;
	}

	
	
}

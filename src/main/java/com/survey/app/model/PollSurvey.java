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
public class PollSurvey implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pollSurveyId;

	@Column
	private int NoOfpolls;

	@Column
	private int surveyId;

	@Column
	private int questionId;

	@Column
	private String question;

	public int getPollSurveyId() {
		return pollSurveyId;
	}

	public void setPollSurveyId(int pollSurveyId) {
		this.pollSurveyId = pollSurveyId;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getNoOfpolls() {
		return NoOfpolls;
	}

	public void setNoOfpolls(int noOfpolls) {
		NoOfpolls = noOfpolls;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}

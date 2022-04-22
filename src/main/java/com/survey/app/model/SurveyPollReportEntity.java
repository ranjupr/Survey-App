package com.survey.app.model;

import java.util.List;

public class SurveyPollReportEntity {

	private int surveyId;

	private String surveyName;

	private String surveyDec;

	private int NoOfpolls;

	private List<QuestionsEntity> questionsEntity;

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public String getSurveyDec() {
		return surveyDec;
	}

	public void setSurveyDec(String surveyDec) {
		this.surveyDec = surveyDec;
	}

	public int getNoOfpolls() {
		return NoOfpolls;
	}

	public void setNoOfpolls(int noOfpolls) {
		NoOfpolls = noOfpolls;
	}

	public List<QuestionsEntity> getQuestionsEntity() {
		return questionsEntity;
	}

	public void setQuestionsEntity(List<QuestionsEntity> questionsEntity) {
		this.questionsEntity = questionsEntity;
	}

}

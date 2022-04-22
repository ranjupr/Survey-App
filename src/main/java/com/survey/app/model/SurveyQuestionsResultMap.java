package com.survey.app.model;

import java.util.List;

import javax.persistence.Column;

public class SurveyQuestionsResultMap {

	private int surveyId;

	private String surveyName;

	private String surveyDec;

	private List<Questions> questions;

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

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

}

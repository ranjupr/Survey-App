package com.survey.app.model;

import java.io.Serializable;
import java.util.List;

public class SurveyEntity implements Serializable {

	private Survey survey;
	
	private List<Questions> questions;

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

}

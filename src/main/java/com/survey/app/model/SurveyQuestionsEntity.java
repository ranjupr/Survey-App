package com.survey.app.model;

import java.io.Serializable;
import java.util.List;

public class SurveyQuestionsEntity implements Serializable {

	private Survey survey;

	private List<Questions> question;

	public List<Questions> getQuestion() {
		return question;
	}

	public void setQuestion(List<Questions> question) {
		this.question = question;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

}

package com.survey.app.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class QuestionsEntity implements Serializable {

	private int questionId;

	private String question;

	private List<AnswersEntity> answers;

	private String status;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<AnswersEntity> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswersEntity> answers) {
		this.answers = answers;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
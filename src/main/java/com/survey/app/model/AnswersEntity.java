package com.survey.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AnswersEntity implements Serializable {

	private int answerId;

	private String answers;

	private String status;

	private int totalPollsToAnswer;

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public int getTotalPollsToAnswer() {
		return totalPollsToAnswer;
	}

	public void setTotalPollsToAnswer(int totalPollsToAnswer) {
		this.totalPollsToAnswer = totalPollsToAnswer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
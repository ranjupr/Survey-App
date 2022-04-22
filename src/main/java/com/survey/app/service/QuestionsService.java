package com.survey.app.service;

import java.util.List;

import com.survey.app.model.Questions;

public interface QuestionsService {
	public List<Questions> getAllQuestions();

	public Questions getQuestionsById(int id);

	public void saveOrUpdate(Questions questions);

	public void delete(int id);

	public void update(Questions questions, int questionid);
}
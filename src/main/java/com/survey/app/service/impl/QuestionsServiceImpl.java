package com.survey.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.app.model.Answers;
import com.survey.app.model.Questions;
import com.survey.app.repository.QuestionsRepository;
import com.survey.app.service.QuestionsService;

@Service
public class QuestionsServiceImpl implements QuestionsService {
	@Autowired
	QuestionsRepository questionsRepository;

	@Override
	public List<Questions> getAllQuestions() {
		List<Questions> questions = new ArrayList<Questions>();
		questionsRepository.findAll().forEach(questions1 -> questions.add(questions1));
		return questions;
	}

	@Override
	public Questions getQuestionsById(int id) {
		return questionsRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(Questions questions) {
		questions.getAnswers().forEach(answers -> answers.setQuestion(questions));
		questionsRepository.save(questions);
	}

	@Override
	public void delete(int id) {
		questionsRepository.deleteById(id);
	}

	@Override
	public void update(Questions questions, int questionid) {
		questionsRepository.save(questions);
	}
}
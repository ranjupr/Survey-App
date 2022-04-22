package com.survey.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.app.model.Answers;
import com.survey.app.model.AnswersEntity;
import com.survey.app.model.PollSurvey;
import com.survey.app.model.PollSurveyDetails;
import com.survey.app.model.Questions;
import com.survey.app.model.QuestionsEntity;
import com.survey.app.model.Survey;
import com.survey.app.model.SurveyPollReportEntity;
import com.survey.app.model.SurveyQuestionsResultMap;
import com.survey.app.repository.SurveyQuestionsMapper;
import com.survey.app.repository.SurveyRepository;
import com.survey.app.service.QuestionsService;
import com.survey.app.service.SurveyQuestionsService;
import com.survey.app.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService {
	@Autowired
	SurveyRepository surveyRepository;

	@Autowired
	SurveyQuestionsMapper surveyQuestionsMapper;

	@Autowired
	QuestionsService questionsService;

	@Autowired
	SurveyQuestionsService surveyQuestionsService;

	@Override
	public List<Survey> getAllSurvey() {
		List<Survey> survey = new ArrayList<Survey>();
		surveyRepository.findAll().forEach(survey1 -> survey.add(survey1));
		return survey;
	}

	@Override
	public Survey getSurveyById(int id) {
		return surveyRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(Survey survey) {
		surveyRepository.save(survey);
	}

	@Override
	public void delete(int id) {
		surveyRepository.deleteById(id);
	}

	@Override
	public void update(Survey survey, int surveyId) {
		surveyRepository.save(survey);
	}

	@Override
	public SurveyPollReportEntity getSurveyPollBySurveyId(int surveyId) {

		SurveyPollReportEntity surveyPollReportEntity = new SurveyPollReportEntity();

		Survey survey = getSurveyById(surveyId);
		surveyPollReportEntity.setSurveyId(survey.getSurveyId());
		surveyPollReportEntity.setSurveyName(survey.getSurveyName());
		surveyPollReportEntity.setSurveyDec(survey.getSurveyDec());
		Integer maxNoOfpolls = surveyQuestionsMapper.selectMaxNoOfpolls(survey.getSurveyId());
		int currentPoll = (maxNoOfpolls != null) ? maxNoOfpolls.intValue() : 0;
		surveyPollReportEntity.setNoOfpolls(currentPoll);

		List<QuestionsEntity> questionsEntityList = new ArrayList<QuestionsEntity>();
		List<PollSurvey> pollSurveyList = surveyQuestionsMapper.findPollSurveyBySurveyId(surveyId);
		for (PollSurvey pollSurvey : pollSurveyList) {
			QuestionsEntity questionsEntity = new QuestionsEntity();
			questionsEntity.setQuestionId(pollSurvey.getQuestionId());
			questionsEntity.setQuestion(pollSurvey.getQuestion());
			if (questionsService.getQuestionsById(pollSurvey.getQuestionId()) == null) {
				questionsEntity.setStatus("INACTIVE");
			} else {
				questionsEntity.setStatus("ACTIVE");
			}

			List<AnswersEntity> answersEntityList = new ArrayList<AnswersEntity>();

			List<PollSurveyDetails> pollSurveyDetailsList = surveyQuestionsMapper
					.findPollSurveyDetailsBySurveyId(survey.getSurveyId(), pollSurvey.getQuestionId());

			for (PollSurveyDetails pollSurveyDetails : pollSurveyDetailsList) {
				AnswersEntity answersEntity = new AnswersEntity();
				answersEntity.setAnswerId(pollSurveyDetails.getAnswerId());
				answersEntity.setAnswers(pollSurveyDetails.getAnswer());
				if (surveyQuestionsMapper.checkValidAnswer(pollSurveyDetails.getAnswerId()) == null) {
					answersEntity.setStatus("INACTIVE");
				} else {
					answersEntity.setStatus("ACTIVE");
				}
				Integer totalPollsToAnswer = surveyQuestionsMapper.selectMaxTotalPollsToAnswer(survey.getSurveyId(),
						pollSurvey.getQuestionId(), pollSurveyDetails.getAnswerId());
				int currentTotalPollsToAnswer = (totalPollsToAnswer != null) ? totalPollsToAnswer.intValue() : 0;
				answersEntity.setTotalPollsToAnswer(currentTotalPollsToAnswer);
				answersEntityList.add(answersEntity);
			}
			questionsEntity.setAnswers(answersEntityList);
			questionsEntityList.add(questionsEntity);

		}
		surveyPollReportEntity.setQuestionsEntity(questionsEntityList);

		return surveyPollReportEntity;
	}

	@Override
	public SurveyPollReportEntity getSurveyPollBySurveyIdBasedOnQuestion(int surveyId) {

		SurveyPollReportEntity surveyPollReportEntity = new SurveyPollReportEntity();

		Survey survey = getSurveyById(surveyId);
		surveyPollReportEntity.setSurveyId(survey.getSurveyId());
		surveyPollReportEntity.setSurveyName(survey.getSurveyName());
		surveyPollReportEntity.setSurveyDec(survey.getSurveyDec());
		Integer maxNoOfpolls = surveyQuestionsMapper.selectMaxNoOfpolls(survey.getSurveyId());
		int currentPoll = (maxNoOfpolls != null) ? maxNoOfpolls.intValue() : 0;
		surveyPollReportEntity.setNoOfpolls(currentPoll);

		List<QuestionsEntity> questionsEntityList = new ArrayList<QuestionsEntity>();

		SurveyQuestionsResultMap surveyQuestionsResultMap = surveyQuestionsService
				.getSurveyBySurveyId(survey.getSurveyId());

		for (Questions question : surveyQuestionsResultMap.getQuestions()) {
			QuestionsEntity questionsEntity = new QuestionsEntity();
			questionsEntity.setQuestionId(question.getQuestionId());
			questionsEntity.setQuestion(question.getQuestion());
			if (questionsService.getQuestionsById(question.getQuestionId()) == null) {
				questionsEntity.setStatus("INACTIVE");
			} else {
				questionsEntity.setStatus("ACTIVE");
			}
			List<AnswersEntity> answersEntityList = new ArrayList<AnswersEntity>();

			for (Answers answers : question.getAnswers()) {
				AnswersEntity answersEntity = new AnswersEntity();
				answersEntity.setAnswerId(answers.getAnswerId());
				answersEntity.setAnswers(answers.getAnswers());
				Integer totalPollsToAnswer = surveyQuestionsMapper.selectMaxTotalPollsToAnswer(survey.getSurveyId(),
						question.getQuestionId(), answers.getAnswerId());
				int currentTotalPollsToAnswer = (totalPollsToAnswer != null) ? totalPollsToAnswer.intValue() : 0;
				answersEntity.setTotalPollsToAnswer(currentTotalPollsToAnswer);
				if (surveyQuestionsMapper.checkValidAnswer(answers.getAnswerId()) == null) {
					answersEntity.setStatus("INACTIVE");
				} else {
					answersEntity.setStatus("ACTIVE");
				}
				answersEntityList.add(answersEntity);
			}
			questionsEntity.setAnswers(answersEntityList);
			questionsEntityList.add(questionsEntity);

		}
		surveyPollReportEntity.setQuestionsEntity(questionsEntityList);

		return surveyPollReportEntity;
	}
}
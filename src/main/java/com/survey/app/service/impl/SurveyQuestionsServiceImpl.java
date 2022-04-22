package com.survey.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.survey.app.model.Answers;
import com.survey.app.model.PollSurvey;
import com.survey.app.model.PollSurveyDetails;
import com.survey.app.model.Questions;
import com.survey.app.model.Survey;
import com.survey.app.model.SurveyQuestions;
import com.survey.app.model.SurveyQuestionsEntity;
import com.survey.app.model.SurveyQuestionsResultMap;
import com.survey.app.repository.PollSurveyDetailsRepository;
import com.survey.app.repository.PollSurveyRepository;
import com.survey.app.repository.QuestionsRepository;
import com.survey.app.repository.SurveyQuestionsMapper;
import com.survey.app.repository.SurveyRepository;
import com.survey.app.service.SurveyQuestionsService;
import com.survey.app.service.SurveyService;

@Service
public class SurveyQuestionsServiceImpl implements SurveyQuestionsService {

	@Autowired
	SurveyQuestionsMapper surveyQuestionsMapper;
	@Autowired
	SurveyRepository surveyRepository;
	@Autowired
	QuestionsRepository questionsRepository;

	@Autowired
	SurveyService surveyService;

	@Autowired
	PollSurveyRepository surveyPollRepository;

	@Autowired
	PollSurveyDetailsRepository pollSurveyDetailsRepository;

	@Override
	public List<SurveyQuestionsResultMap> getAllsurveyQuestions() {
		List<SurveyQuestionsResultMap> surveyQuestionsResultMaps = new ArrayList<SurveyQuestionsResultMap>();
		List<Survey> surveyList = surveyService.getAllSurvey();
		for (Survey survey : surveyList) {
			surveyQuestionsResultMaps.add(getSurveyBySurveyId(survey.getSurveyId()));
		}
		return surveyQuestionsResultMaps;
	}

	@Override
	public SurveyQuestions getsurveyQuestionsById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(SurveyQuestionsEntity surveyQuestionsEntity) {

		List<SurveyQuestions> surveyQuestionsList = new ArrayList<SurveyQuestions>();
		for (Questions question : surveyQuestionsEntity.getQuestion()) {
			List<SurveyQuestions> surveyQuestionsCheck = surveyQuestionsMapper.findBySurveyIdAndQuestionId(
					surveyQuestionsEntity.getSurvey().getSurveyId(), question.getQuestionId());
			if (CollectionUtils.isEmpty(surveyQuestionsCheck)) {
				SurveyQuestions surveyQuestions = new SurveyQuestions();
				surveyQuestions.setSurveyId(surveyQuestionsEntity.getSurvey().getSurveyId());
				surveyQuestions.setQuestionId(question.getQuestionId());
				surveyQuestionsList.add(surveyQuestions);
			}
		}

		surveyQuestionsMapper.saveList(surveyQuestionsList);

	}

	@Override
	public SurveyQuestionsResultMap getSurveyBySurveyId(int surveyId) {
		SurveyQuestionsResultMap surveyQuestionsResultMap = new SurveyQuestionsResultMap();

		List<SurveyQuestions> surveyQuestions = surveyQuestionsMapper.findBySurveyId(surveyId);
		Survey survey = surveyRepository.findById(surveyQuestions.get(0).getSurveyId()).get();
		surveyQuestionsResultMap.setSurveyId(survey.getSurveyId());
		surveyQuestionsResultMap.setSurveyName(survey.getSurveyName());
		surveyQuestionsResultMap.setSurveyDec(survey.getSurveyDec());
		List<Questions> questions = new ArrayList<Questions>();
		for (SurveyQuestions surveyQuestion : surveyQuestions) {
			Optional<Questions> questionsOptional = questionsRepository.findById(surveyQuestion.getQuestionId());
			if (questionsOptional.isPresent()) {
				Questions question = questionsOptional.get();
				if (question != null) {
					questions.add(question);
				}
			}
		}
		surveyQuestionsResultMap.setQuestions(questions);

		return surveyQuestionsResultMap;

	}

	@Override
	public void saveSurveyPoll(SurveyQuestionsEntity surveyQuestionsEntity) {

		int pollUpdate = 0;
		Integer maxNoOfpolls = surveyQuestionsMapper
				.selectMaxNoOfpolls(surveyQuestionsEntity.getSurvey().getSurveyId());
		int currentPoll = (maxNoOfpolls != null) ? maxNoOfpolls.intValue() : 0;
		if (currentPoll == 0) {
			pollUpdate = 1;
		} else {
			pollUpdate = currentPoll + 1;

		}
		for (Questions question : surveyQuestionsEntity.getQuestion()) {
			PollSurvey pollSurvey = new PollSurvey();
			pollSurvey.setSurveyId(surveyQuestionsEntity.getSurvey().getSurveyId());
			pollSurvey.setQuestionId(question.getQuestionId());
			pollSurvey.setQuestion(question.getQuestion());
			pollSurvey.setNoOfpolls(pollUpdate);
			surveyPollRepository.save(pollSurvey);

			List<PollSurveyDetails> pollSurveyDetailsList = new ArrayList<PollSurveyDetails>();
			for (Answers answer : question.getAnswers()) {
				PollSurveyDetails pollSurveyDetails = new PollSurveyDetails();
				Integer totalPollsToAnswer = surveyQuestionsMapper.selectMaxTotalPollsToAnswer(
						surveyQuestionsEntity.getSurvey().getSurveyId(), question.getQuestionId(),
						answer.getAnswerId());
				int currentTotalPollsToAnswer = (totalPollsToAnswer != null) ? totalPollsToAnswer.intValue() : 0;
				if (currentTotalPollsToAnswer == 0) {
					pollSurveyDetails.setTotalPollsToAnswer(1);
				} else {
					pollSurveyDetails.setTotalPollsToAnswer(currentTotalPollsToAnswer + 1);
				}
				pollSurveyDetails.setPollSurveyId(pollSurvey.getPollSurveyId());
				pollSurveyDetails.setAnswerId(answer.getAnswerId());
				pollSurveyDetails.setAnswer(answer.getAnswers());
				pollSurveyDetailsList.add(pollSurveyDetails);
			}
			pollSurveyDetailsRepository.saveAll(pollSurveyDetailsList);
		}
	}

}

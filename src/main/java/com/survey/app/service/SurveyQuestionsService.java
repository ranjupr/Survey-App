package com.survey.app.service;

import java.util.List;

import com.survey.app.model.SurveyQuestions;
import com.survey.app.model.SurveyQuestionsEntity;
import com.survey.app.model.SurveyQuestionsResultMap;

public interface SurveyQuestionsService {
	public List<SurveyQuestionsResultMap> getAllsurveyQuestions();

	public SurveyQuestions getsurveyQuestionsById(int id);
	
	public SurveyQuestionsResultMap getSurveyBySurveyId(int surveyId);

	public void saveOrUpdate(SurveyQuestionsEntity surveyQuestionsEntity);
	
	public void saveSurveyPoll(SurveyQuestionsEntity surveyQuestionsEntity);
}

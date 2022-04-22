package com.survey.app.service;

import java.util.List;

import com.survey.app.model.Survey;
import com.survey.app.model.SurveyPollReportEntity;

public interface SurveyService {
	public List<Survey> getAllSurvey();

	public Survey getSurveyById(int id);

	public void saveOrUpdate(Survey survey);

	public void delete(int id);

	public void update(Survey survey, int surveyId);

	public SurveyPollReportEntity getSurveyPollBySurveyId(int surveyId);

	public SurveyPollReportEntity getSurveyPollBySurveyIdBasedOnQuestion(int surveyId);

}

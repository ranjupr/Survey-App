package com.survey.app.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.survey.app.model.PollSurvey;
import com.survey.app.model.PollSurveyDetails;
import com.survey.app.model.SurveyQuestions;

@Mapper
public interface SurveyQuestionsMapper {

	public boolean saveList(List<SurveyQuestions> surveyQuestions);

	public List<SurveyQuestions> findBySurveyId(int surveyId);

	public List<SurveyQuestions> findBySurveyIdAndQuestionId(int surveyId, int questionId);

	public boolean savePollList(List<PollSurvey> pollSurvey);

	public boolean savePollSurveyDetailsList(List<PollSurveyDetails> pollSurveyDetails);

	public Integer selectMaxNoOfpolls(int surveyId);

	public Integer selectMaxTotalPollsToAnswer(int surveyId, int questionId, int answerId);

	public List<PollSurvey> findPollSurveyBySurveyId(int surveyId);

	public List<PollSurveyDetails> findPollSurveyDetailsBySurveyId(int surveyId, int questionId);

	public Integer checkValidAnswer(int answerId);

}

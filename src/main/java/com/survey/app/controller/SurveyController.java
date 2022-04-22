package com.survey.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.app.model.Survey;
import com.survey.app.model.SurveyPollReportEntity;
import com.survey.app.model.SurveyQuestionsEntity;
import com.survey.app.model.SurveyQuestionsResultMap;
import com.survey.app.service.SurveyQuestionsService;
import com.survey.app.service.SurveyService;

@RestController
@RequestMapping("/survey")
public class SurveyController {
	@Autowired
	SurveyService surveyService;

	@Autowired
	SurveyQuestionsService surveyQuestionsService;

	@GetMapping
	private List<Survey> getAllSurvey() {
		return surveyService.getAllSurvey();
	}

	@GetMapping("/{id}")
	private Survey getSurvey(@PathVariable("id") int surveyid) {
		return surveyService.getSurveyById(surveyid);
	}

	@PostMapping
	private Survey saveSurvey(@RequestBody SurveyQuestionsEntity surveyQuestionsEntity) {

		surveyService.saveOrUpdate(surveyQuestionsEntity.getSurvey());
		surveyQuestionsService.saveOrUpdate(surveyQuestionsEntity);

		return surveyService.getSurveyById(surveyQuestionsEntity.getSurvey().getSurveyId());
	}

	@GetMapping("/details/{surveyId}")
	private SurveyQuestionsResultMap getDetailsSurveyById(@PathVariable("surveyId") int surveyId) {
		return surveyQuestionsService.getSurveyBySurveyId(surveyId);
	}

	@GetMapping("/details")
	private List<SurveyQuestionsResultMap> getDetailsSurvey() {
		return surveyQuestionsService.getAllsurveyQuestions();
	}

	@PostMapping("/poll")
	private String saveSurveyPoll(@RequestBody SurveyQuestionsEntity surveyQuestionsEntity) {

		surveyQuestionsService.saveSurveyPoll(surveyQuestionsEntity);

		return "Survey Submitted Successfully";
	}

	@GetMapping("/poll/{surveyId}")
	private SurveyPollReportEntity getSurveyPoll(@PathVariable("surveyId") int surveyId) {

		SurveyPollReportEntity surveyPollReportEntity = surveyService.getSurveyPollBySurveyIdBasedOnQuestion(surveyId);

		return surveyPollReportEntity;
	}

}

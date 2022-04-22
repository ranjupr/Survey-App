package com.survey.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.survey.app.model.PollSurvey;

public interface PollSurveyRepository extends CrudRepository<PollSurvey, Integer> {

}

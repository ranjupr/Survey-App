package com.survey.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.survey.app.model.Survey;

public interface SurveyRepository extends CrudRepository<Survey, Integer> {

}

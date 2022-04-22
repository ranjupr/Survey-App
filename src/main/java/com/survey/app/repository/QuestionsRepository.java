package com.survey.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.survey.app.model.Questions;

public interface QuestionsRepository extends CrudRepository<Questions, Integer> {
}

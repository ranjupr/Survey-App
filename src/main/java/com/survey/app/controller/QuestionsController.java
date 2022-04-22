package com.survey.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.app.model.Questions;
import com.survey.app.service.QuestionsService;

@RestController
@RequestMapping("/questions")
public class QuestionsController {
	@Autowired
	QuestionsService questionsService;

	@GetMapping
	private List<Questions> getAllBooks() {
		return questionsService.getAllQuestions();
	}

	@GetMapping("/{id}")
	private Questions getQuestions(@PathVariable("id") int questionid) {
		return questionsService.getQuestionsById(questionid);
	}

	@DeleteMapping("/{id}")
	private String deleteQuestion(@PathVariable("id") int questionid) {
		questionsService.delete(questionid);
		return "Successfully deleted question " + questionid;
	}

	@PostMapping
	private Questions saveQuestion(@RequestBody Questions questions) {
		questionsService.saveOrUpdate(questions);
		return questionsService.getQuestionsById(questions.getQuestionId());
	}

	@PutMapping
	private Questions update(@RequestBody Questions questions) {
		questionsService.saveOrUpdate(questions);
		return questionsService.getQuestionsById(questions.getQuestionId());
	}
}

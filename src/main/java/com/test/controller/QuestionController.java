package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.HttpResponseDTO;
import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.model.TestSeriesEntity;
import com.test.service.IQuestionService;
import com.test.service.ITestSeriesService;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

	@Autowired
	private IQuestionService questionService;
	@Autowired
	private ITestSeriesService testSeriesService;

	@PostMapping("/save-all/{testSeriesId}")
	public ResponseEntity<HttpResponseDTO<List<QuestionDTO>>> insertAllQuestions(
			@PathVariable Integer testSeriesId,
			@RequestBody List<QuestionDTO> questionDTOs) {

			TestSeriesEntity testSeriesEntity = testSeriesService.getTestSeriesEntity(testSeriesId);
			questionService.saveQuestion(questionDTOs,testSeriesEntity);
		
		return ResponseEntity
				.ok(  HttpResponseDTO.of(HttpStatus.ACCEPTED, "All Questions saved successfully", questionDTOs));
	}


}

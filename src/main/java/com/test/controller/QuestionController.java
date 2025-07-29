package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.HttpResponseDTO;
import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.service.IQuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private IQuestionService questionService;

	@PostMapping("/save-all")
	public ResponseEntity<HttpResponseDTO<List<QuestionDTO>>> insertAllQuestions(
			@RequestBody List<QuestionDTO> questionDTOs) {

		for (QuestionDTO questions : questionDTOs) {
			questionService.saveQuestion(questions);
		}
		return ResponseEntity
				.ok(new HttpResponseDTO<>(HttpStatus.ACCEPTED, "All Questions saved successfully", questionDTOs));
	}

	@GetMapping("/find-all/{testSeriesId}")
	public ResponseEntity<HttpResponseDTO<List<QuestionOptionDTO>>> getAllQuestionEntityById(
			@PathVariable("testSeriesId") Integer testSeriesId) {
		
		List<QuestionOptionDTO> dto = questionService.getAllQuestionEntityById(testSeriesId);
		HttpResponseDTO<List<QuestionOptionDTO>> response = new HttpResponseDTO<>(HttpStatus.ACCEPTED,
				"All Questions fetched successfully", dto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}

}

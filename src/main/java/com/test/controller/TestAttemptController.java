package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.AnswerRequestDTO;
import com.test.dto.ResultDTO;
import com.test.service.ITestAttemptService;



@RestController
@CrossOrigin
public class TestAttemptController {
	
	@Autowired
	private ITestAttemptService attemptService;
	
	
	@PostMapping("/result/{test-series-id}")
	public ResponseEntity<ResultDTO> result(
	        @PathVariable("test-series-id") Integer testSeriesId,
	        @RequestBody List<AnswerRequestDTO> answerRqstDTO) {
	    
	    ResultDTO result = attemptService.result(testSeriesId, answerRqstDTO);

	  

	    return ResponseEntity
	            .status(HttpStatus.OK)   // explicitly setting status code 200
	            .body(result);
	}


}

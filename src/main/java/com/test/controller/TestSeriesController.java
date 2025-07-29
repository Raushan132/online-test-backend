package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.HttpResponseDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.dto.TestSeriesDTO;
import com.test.model.TestSeriesEntity;
import com.test.service.ITestSeriesService;

@RestController
@RequestMapping("/test")
public class TestSeriesController {
	
	
	@Autowired
	private ITestSeriesService iTestSeriesService;
	
	@GetMapping("/series/{test-series-id}")
	public ResponseEntity<HttpResponseDTO<List<QuestionOptionDTO>>> getAllTestSeries(@PathVariable("test-series-id") Integer testSeriesId){
		List<QuestionOptionDTO> allTestSeries = iTestSeriesService.getAllTestSeries(testSeriesId);
		return ResponseEntity.ok(new HttpResponseDTO<>(HttpStatus.OK,"feteched successfully",allTestSeries));
	}
	
	@GetMapping("/series/category/{category}")
	public ResponseEntity<HttpResponseDTO<List<TestSeriesDTO>>> getAllTestSeriesByCategory(@PathVariable("category")String category){
		List<TestSeriesDTO> allTestSeriesByCategory = iTestSeriesService.getAllTestSeriesByCategory(category);
		return ResponseEntity.ok(new HttpResponseDTO<>(HttpStatus.ACCEPTED,"fetched by Category successfully",allTestSeriesByCategory));
	}
	
	
	

}

package com.test.service;

import java.util.List;

import com.test.dto.QuestionDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.dto.TestSeriesDTO;

public interface ITestSeriesService {

	List<QuestionOptionDTO> getAllTestSeries(Integer testSeriesId);

	List<TestSeriesDTO> getAllTestSeriesByCategory(String testCategory);

	public List<TestSeriesDTO> getAllTestSeries();

	List<String> getAllCategories();

	String saveTestSeries(TestSeriesDTO dto);


}

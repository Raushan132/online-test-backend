package com.test.service;

import java.util.List;

import com.test.dto.QuestionOptionDTO;
import com.test.dto.TestSeriesDTO;
import com.test.dto.TestSeriesTitlesDTO;
import com.test.model.TestSeriesEntity;

public interface ITestSeriesService {

	List<QuestionOptionDTO> getAllTestSeries(Integer testSeriesId);

	List<TestSeriesDTO> getAllTestSeriesByCategory(String testCategory);

	List<TestSeriesDTO> getAllTestSeries();

	List<String> getAllCategories();
	List<TestSeriesTitlesDTO> getAllTestSeriesId();
	String saveTestSeries(TestSeriesDTO dto);
	TestSeriesEntity getTestSeriesEntity(Integer TestSeriesId);


}

package com.test.service;

import java.util.List;

import com.test.dto.QuestionOptionDTO;
import com.test.dto.TestSeriesDTO;

public interface ITestSeriesService {
	
	public abstract List<QuestionOptionDTO> getAllTestSeries(Integer testSeriesId);
	
	public abstract List<TestSeriesDTO> getAllTestSeriesByCategory(String testCategory);
	
	
	
	
	

}

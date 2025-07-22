package com.test.service;

import java.util.List;

import com.test.dto.QuestionOptionDTO;

public interface ITestSeriesService {
	
	public List<QuestionOptionDTO> getAllTestSeries(Integer testSeriesId);

}

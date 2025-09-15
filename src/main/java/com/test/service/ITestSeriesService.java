package com.test.service;

import java.util.List;

import com.test.dto.ClassificationDTO;
import com.test.dto.QuestionOptionDTO;
import com.test.dto.TestSeriesDTO;
import com.test.dto.TestSeriesTitlesDTO;
import com.test.model.CategoryEntity;
import com.test.model.TestSeriesEntity;

public interface ITestSeriesService {

	List<QuestionOptionDTO> getAllTestSeries(Integer testSeriesId);

	List<TestSeriesDTO> getAllTestSeriesByCategory(String testCategory);

	List<TestSeriesDTO> getAllTestSeries();
	List<TestSeriesDTO> getAllTestSeriesByPlaylistId(Integer playListId);

	ClassificationDTO getAllClassification();
	List<TestSeriesTitlesDTO> getAllTestSeriesId(Integer playListId);
	String saveTestSeries(TestSeriesDTO dto);
	TestSeriesEntity getTestSeriesEntity(Integer TestSeriesId);


}

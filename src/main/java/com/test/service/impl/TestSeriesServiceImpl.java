package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.QuestionOptionDTO;
import com.test.dto.TestSeriesDTO;
import com.test.model.TestSeriesEntity;
import com.test.repository.TestSeriesRepository;
import com.test.service.IQuestionService;
import com.test.service.ITestSeriesService;

@Service
public class TestSeriesServiceImpl implements ITestSeriesService {

	@Autowired
	private IQuestionService iQuestionService;

	@Autowired
	private TestSeriesRepository testSeriesRepo;
	
	

	@Override
	public List<QuestionOptionDTO> getAllTestSeries(Integer testSeriesId) {
		List<QuestionOptionDTO> list = iQuestionService.getAllQuestionEntityById(testSeriesId);
		System.out.print("----------Test Service Executed-----------");
		System.out.println(list);
		return list;
	}

	@Override
	public List<TestSeriesEntity> getAllTestSeriesByCategory(String testCategory) {
		testCategory.toLowerCase();
		List<TestSeriesEntity> entity = testSeriesRepo.findTestSeriesIdByCategory(testCategory);
			
		return entity;
	}
	
	

}

package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.QuestionsEntity;

public interface QuestionRepository extends JpaRepository<QuestionsEntity, Integer> {
	
	
	List<QuestionsEntity> findAllByTestSeries_TestSeriesId(Integer testSeriesId);



}

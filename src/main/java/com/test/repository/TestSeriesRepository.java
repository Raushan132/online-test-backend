package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.model.TestSeriesEntity;

public interface TestSeriesRepository extends JpaRepository<TestSeriesEntity, Integer> {

	@Query("SELECT t FROM TestSeriesEntity t WHERE t.category =:category")
	List<TestSeriesEntity> findTestSeriesIdByCategory(@Param("category") String category);
	
	@Query("SELECT DISTINCT t.category FROM TestSeriesEntity t")
	 List<String>   findAllCategories();
	
	 List<TestSeriesEntity> findByPlayListId(Integer playListId);
	
	
	
	

}

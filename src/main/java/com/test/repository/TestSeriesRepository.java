package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.TestSeriesEntity;

public interface TestSeriesRepository extends JpaRepository<TestSeriesEntity,Integer>{

}

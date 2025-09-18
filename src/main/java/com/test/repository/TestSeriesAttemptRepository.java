package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.TestSeriesAttemptEntity;

public interface TestSeriesAttemptRepository extends JpaRepository<TestSeriesAttemptEntity,Integer> {

}

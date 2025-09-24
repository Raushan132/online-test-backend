package com.test.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.ScoreEntity;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Integer> {
	
	Optional<ScoreEntity> findByTestSeriesAttempt_AttemptId(Integer attemptId);

}

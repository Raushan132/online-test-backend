package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.PlanEntity;

public interface PlanRepository extends JpaRepository<PlanEntity, Integer> {
	
	List<PlanEntity> findByUserId(Integer userId);

}

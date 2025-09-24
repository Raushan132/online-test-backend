package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.PlanDescription;

public interface PlanDescriptionRepository extends JpaRepository<PlanDescription, Integer> {

}

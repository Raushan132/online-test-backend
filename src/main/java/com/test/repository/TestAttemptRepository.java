package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.TestAttemptEntity;

public interface TestAttemptRepository extends JpaRepository<TestAttemptEntity,Integer> {

}

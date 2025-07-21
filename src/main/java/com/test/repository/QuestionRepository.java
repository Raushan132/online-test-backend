package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.QuestionsEntity;

public interface QuestionRepository extends JpaRepository<QuestionsEntity, Integer> {

}

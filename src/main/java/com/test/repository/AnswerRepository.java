package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.AnswerEntity;

public interface AnswerRepository  extends JpaRepository<AnswerEntity,Integer>{

}

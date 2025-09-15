package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
	
	Optional<SubjectEntity> findBySubjectName(String subjectName);

}

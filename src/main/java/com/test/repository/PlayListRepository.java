package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.PlayListEntity;

public interface PlayListRepository extends JpaRepository<PlayListEntity, Integer> {
	
	List<PlayListEntity> findByUserUserId(Integer userId);
	
	

}

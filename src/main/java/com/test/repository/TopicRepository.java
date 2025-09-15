package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.TopicEntity;

public interface TopicRepository extends JpaRepository<TopicEntity, Integer> {
	
	Optional<TopicEntity> findByTopicName(String topicName);
}

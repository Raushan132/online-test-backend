package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.PlayListEntity;

public interface PlayListRepository extends JpaRepository<PlayListEntity, Integer> {

}

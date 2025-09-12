package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.AccessTokenEntity;

public interface AccessTokenRepository extends JpaRepository<AccessTokenEntity, Integer> {

}

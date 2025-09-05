package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.UserIdAndTokenEntity;

public interface UserIdAndTokenRepository extends JpaRepository<UserIdAndTokenEntity, Integer>{

	 Optional<UserIdAndTokenEntity> findByUserId(Integer userId);
}

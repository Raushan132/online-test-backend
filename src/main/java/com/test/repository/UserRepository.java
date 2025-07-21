package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{

}

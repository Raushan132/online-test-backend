package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	
	Optional<CategoryEntity> findByCategoryName(String categoryName);

}

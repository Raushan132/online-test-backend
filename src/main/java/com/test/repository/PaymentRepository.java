package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

}

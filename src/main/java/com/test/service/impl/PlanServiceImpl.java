package com.test.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.PlanEntity;
import com.test.model.UserEntity;
import com.test.repository.PlanRepository;
import com.test.service.IAuthenticatedUserService;
import com.test.service.IPlanService;

@Service
public class PlanServiceImpl implements IPlanService {
	
	@Autowired
	private IAuthenticatedUserService authService;

	@Autowired
	private PlanRepository planRepository;
	
	@Override
	public void createPlan(PlanEntity plan) {
		// TODO Auto-generated method stub
		
	   UserEntity user=	authService.getCurrentUser();
	   plan.setUserId(user.getUserId());
	   plan.setActive(true);
	   plan.setCreatedAt(LocalDate.now());
	  
	   
	   planRepository.save(plan);
	   

	}

	@Override
	public void getAllPlanByUserId(Integer userId) {
		// TODO Auto-generated method stub
		planRepository.findByUserId(userId);

	}

}

package com.test.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.PlanDescriptionDTO;
import com.test.model.PlanDescription;
import com.test.model.PlanEntity;
import com.test.model.UserEntity;
import com.test.repository.PlanDescriptionRepository;
import com.test.repository.PlanRepository;
import com.test.service.IAuthenticatedUserService;
import com.test.service.IPlanService;

@Service
public class PlanServiceImpl implements IPlanService {
	
	@Autowired
	private IAuthenticatedUserService authService;

	@Autowired
	private PlanRepository planRepository;
	
	@Autowired 
	private PlanDescriptionRepository planDescriptionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
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

	@Override
	public List<PlanDescriptionDTO> getAllPlanDescription() {
		// TODO Auto-generated method stub
		
		
		return planDescriptionRepository.findAll().stream()
			    .map(plan -> new PlanDescriptionDTO(
			        plan.getPlanDescriptionId(),
			        plan.getTitle(),
			        plan.getDescription(),
			        plan.getDuration()
			    ))
			    .toList();
	}

}

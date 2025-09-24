package com.test.service;

import java.util.List;

import com.test.dto.PlanDescriptionDTO;
import com.test.model.PlanDescription;
import com.test.model.PlanEntity;

public interface IPlanService {
	
	void createPlan(PlanEntity plan);
	List<PlanDescriptionDTO> getAllPlanDescription();
	void getAllPlanByUserId(Integer userId);
	

}

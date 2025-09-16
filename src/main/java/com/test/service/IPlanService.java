package com.test.service;

import com.test.model.PlanEntity;

public interface IPlanService {
	
	void createPlan(PlanEntity plan);
	void getAllPlanByUserId(Integer userId);
	

}

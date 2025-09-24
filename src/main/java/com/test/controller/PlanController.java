package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.HttpResponseDTO;
import com.test.dto.PlanDescriptionDTO;
import com.test.model.PlanDescription;
import com.test.service.IPlanService;

@CrossOrigin
@RestController
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
	private IPlanService planService;
		
	@GetMapping("/description")
	public ResponseEntity<HttpResponseDTO<List<PlanDescriptionDTO>>> getPlanDescription(){
		
		return ResponseEntity.ok(new HttpResponseDTO<>(HttpStatus.ACCEPTED,"Fetched the Plan Description",planService.getAllPlanDescription()));
	}
}

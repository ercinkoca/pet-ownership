package com.pet.ownership.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pet.ownership.model.ApiResponse;
import com.pet.ownership.service.PetOwnershipService;

@RestController
public class PetOwnershipController {
	
	@Autowired
	private PetOwnershipService service;
	
	// Controller For Rest Api
	@GetMapping(value = "/match-api",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ApiResponse> makeMatch() {
		return service.match();
	}
	
	@GetMapping(value = "/")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("responseList",service.match());
		return model;
	}
}

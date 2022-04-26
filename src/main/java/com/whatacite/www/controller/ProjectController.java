package com.whatacite.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.whatacite.www.service.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService service;
	
}

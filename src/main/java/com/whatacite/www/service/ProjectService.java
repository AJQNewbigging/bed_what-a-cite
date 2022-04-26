package com.whatacite.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whatacite.www.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository repo;

}

package com.whatacite.www.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whatacite.www.model.Project;
import com.whatacite.www.model.dto.ProjectCreationDTO;
import com.whatacite.www.model.dto.ProjectDTO;
import com.whatacite.www.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository repo;
	
	public List<ProjectDTO> getAll() {
		List<ProjectDTO> dtos = new ArrayList<>();
		
		List<Project> projects = this.repo.findAll();
		
		projects.forEach(p -> dtos.add(new ProjectDTO(p)));
		
		return dtos;
	}
	
	public ProjectDTO save(ProjectCreationDTO dto) {
		Project newProject = new Project(dto);
		
		newProject = this.repo.save(newProject);
		
		return new ProjectDTO(newProject);
	}

}

package com.whatacite.www.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	/**
	 * Retrieve all projects, order by last-updated descending
	 * @return list of ordered DTOs
	 */
	public List<ProjectDTO> getAll() {
		List<ProjectDTO> dtos = new ArrayList<>();
		
		List<Project> projects = this.repo.findAllOrderByLastUpdatedDesc();
		
		projects.forEach(p -> dtos.add(new ProjectDTO(p)));
		
		return dtos;
	}
	
	/**
	 * Retrieve one project by ID
	 * @param id - project ID
	 * @return Optional of ProjectDTO, or empty Optional if no Project found
	 */
	public Optional<ProjectDTO> get(Long id) {
		Optional<Project> project = this.repo.findById(id);
		
		if (project.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(new ProjectDTO(project.get()));
	}
	
	/**
	 * Create new project
	 * @param dto - dto of project to be created
	 * @return ProjectDTO of new Project
	 */
	public ProjectDTO save(ProjectCreationDTO dto) {
		Project newProject = new Project(dto);
		
		newProject = this.repo.save(newProject);
		
		return new ProjectDTO(newProject);
	}
	
	/**
	 * Update existing project
	 * @param dto - project form
	 * @param id - project id
	 * @return new dto from updated project
	 */
	public ProjectDTO save(ProjectCreationDTO dto, Long id) {
		Project newProject = new Project(dto, id);
		
		newProject = this.repo.save(newProject);
		
		return new ProjectDTO(newProject);
	}
	
	/**
	 * Delete a project by ID
	 * @param id - project ID
	 * @return true if successful delete, otherwise false.
	 */
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		
		return this.repo.findById(id).isEmpty();
	}

}

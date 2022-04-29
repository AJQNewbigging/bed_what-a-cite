package com.whatacite.www.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.whatacite.www.model.Citation;
import com.whatacite.www.model.Project;
import com.whatacite.www.model.dto.ProjectCreationDTO;
import com.whatacite.www.model.dto.ProjectDTO;
import com.whatacite.www.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

	@Mock
	private ProjectRepository repo;
	
	@InjectMocks
	private ProjectService service;
	
	private List<Project> projects;
	private List<ProjectDTO> projectDTOs;
	
	private List<Citation> citations;
	
	@BeforeEach
	public void setup() {
		new Project(1L, "Title", "Description", new Date(), new Date(), new HashSet<>());
		projects = List.of(
				new Project(1L, "Title 1", "Description", new Date(), new Date(), new HashSet<>()),
				new Project(2L, "Title 2", "Description", new Date(), new Date(), new HashSet<>()),
				new Project(3L, "Title 3", "Description", new Date(), new Date(), new HashSet<>()));
		projectDTOs = projects.stream().map(p -> new ProjectDTO(p)).toList();
		
		citations = List.of(
				new Citation(1L, "Title 1", "Author 1", Calendar.getInstance(), "Publisher", "doi", "abstract", new Date(), new HashSet<>()),
				new Citation(2L, "Title 2", "Author 2", Calendar.getInstance(), "Publisher", "doi", "abstract", new Date(), new HashSet<>()),
				new Citation(3L, "Title 3", "Author 3", Calendar.getInstance(), "Publisher", "doi", "abstract", new Date(), new HashSet<>()));
	}
	
	@Test
	public void getAllTest() {
		when(repo.findAllByOrderByLastUpdatedDesc()).thenReturn(projects);
		
		List<ProjectDTO> actual = service.getAll();
		
		assertEquals(projectDTOs, actual);
		verify(repo).findAllByOrderByLastUpdatedDesc();
	}
	
	@Test
	public void getTest() {
		Project project = projects.get(0);
		ProjectDTO projectDTO = projectDTOs.get(0);
		Long id = project.getId();
		
		when(repo.findById(id)).thenReturn(Optional.of(project));
		
		Optional<ProjectDTO> actual = service.get(id);
		
		assertEquals(Optional.of(projectDTO), actual);
		verify(repo).findById(id);
	}
	
	@Test
	public void getTestFailure() {
		Long id = 6L;
		when(repo.findById(id)).thenReturn(Optional.empty());
		
		Optional<?> actual = service.get(id);
	
		assertEquals(Optional.empty(), actual);
		verify(repo).findById(id);
	}
	
	@Test
	public void createTest() {
		Project project = projects.get(0);
		ProjectCreationDTO newProjectDTO = new ProjectCreationDTO(project.getTitle(), project.getDescription(), project.getDue());
		ProjectDTO expected = projectDTOs.get(0);
		
		when(repo.save((any(Project.class)))).thenReturn(project);
		
		ProjectDTO actual = service.save(newProjectDTO);
		
		assertEquals(expected, actual);
		verify(repo).save(any(Project.class));
	}
	
	@Test
	public void updateTest() {
		Project project = projects.get(0);
		ProjectCreationDTO newProjectDTO = new ProjectCreationDTO(project.getTitle(), project.getDescription(), project.getDue());
		ProjectDTO expected = projectDTOs.get(0);
		
		when(repo.save((any(Project.class)))).thenReturn(project);
		
		ProjectDTO actual = service.save(newProjectDTO, project.getId());
		
		assertEquals(expected, actual);
		verify(repo).save(any(Project.class));
	}
}
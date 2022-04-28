package com.whatacite.www.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.whatacite.www.model.dto.ProjectCreationDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	private String title;
	
	@Getter
	private String description;
	
	@Getter
	private Date lastUpdated;
	
	@Getter
	private Date due;
	
	@Getter
	@Setter
	@ManyToMany
	@JoinTable(
			name = "project_citation",
			joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "citation_id", referencedColumnName = "id"))
	private Set<Citation> citations;
	
	public Project(ProjectCreationDTO dto) {
		this.title = dto.getTitle();
		this.description = dto.getDescription();
		this.lastUpdated = new Date();
		this.due = dto.getDueDate();
		this.citations = new HashSet<>();
	}
	
	public Project(ProjectCreationDTO dto, Long id) {
		this(dto);
		this.id = id;
	}

}

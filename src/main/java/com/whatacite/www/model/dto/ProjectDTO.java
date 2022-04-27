package com.whatacite.www.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.whatacite.www.model.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
	
	private Long id;
	
	private String title;
	
	private String description;
	
	private Date lastUpdated;
	
	private Date dueDate;
	
	private List<CitationDTO> citations;
	
	public ProjectDTO(Project p) {
		this.id = p.getId();
		this.title = p.getTitle();
		this.description = p.getDescription();
		this.lastUpdated = p.getLastUpdated();
		this.dueDate = p.getDue();
		
		this.citations = new ArrayList<>();
		
		if (!(p.getCitations() == null || p.getCitations().isEmpty())) {
			p.getCitations().forEach(c -> this.citations.add(new CitationDTO(c)));
		}
	}

}

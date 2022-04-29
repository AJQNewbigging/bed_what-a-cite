package com.whatacite.www.model.dto;

import java.text.SimpleDateFormat;
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
	
	private String dueDate;
	
	private List<CitationDTO> citations;
	
	public ProjectDTO(Project p) {
		this.id = p.getId();
		this.title = p.getTitle();
		this.description = p.getDescription();
		this.lastUpdated = p.getLastUpdated();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		this.dueDate = formatter.format(p.getDue());
		
		this.citations = new ArrayList<>();
		
		if (!(p.getCitations() == null || p.getCitations().isEmpty())) {
			p.getCitations().forEach(c -> this.citations.add(new CitationDTO(c)));
		}
	}

}

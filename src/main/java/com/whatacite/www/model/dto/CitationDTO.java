package com.whatacite.www.model.dto;

import com.whatacite.www.model.Citation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitationDTO {

	private Long id;
	
	private String title;
	
	private String citation;
	
	private String abstrac;
	
	public CitationDTO(Citation c) {
		this.id = c.getId();
		this.title = c.getTitle();
		this.citation = c.asCitation();
		this.abstrac = c.getAbstrac();
	}
	
}

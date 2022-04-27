package com.whatacite.www.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitationCreationDTO {

	@NotBlank
	private String title;
	
	@NotBlank
	private String authorLine;
	
	@Min(1699)
	@Max(2999)
	private int yearPublished;
	
	@NotBlank
	private String publisher;
	
	@URL
	private String doi;
	
	@Size(max = 500)
	private String abstrac;
	
}

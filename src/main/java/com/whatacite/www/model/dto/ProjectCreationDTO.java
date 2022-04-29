package com.whatacite.www.model.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreationDTO {

	@NotBlank(message = "Title is a mandatory field")
	private String title;
	
	private String description;
	
	@Future(message = "Due date must be in the future")
	private Date dueDate;
	
}

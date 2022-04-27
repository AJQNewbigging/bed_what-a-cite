package com.whatacite.www.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whatacite.www.model.Citation;
import com.whatacite.www.model.Project;
import com.whatacite.www.model.dto.CitationCreationDTO;
import com.whatacite.www.model.dto.CitationDTO;
import com.whatacite.www.repository.CitationRepository;

@Service
public class CitationService {

	@Autowired
	private CitationRepository repo;
	
	public List<CitationDTO> getAll() {
		List<CitationDTO> dtos = new ArrayList<>();
		
		List<Citation> citations = this.repo.findAll();
		
		citations.forEach(c -> dtos.add(new CitationDTO(c)));
		
		return dtos;
	}
	
	public CitationDTO save(CitationCreationDTO dto, Long projectId) {
		Citation newCitation = new Citation(dto);
		
		Project p = Project.builder().id(projectId).build();
		newCitation.addProject(p);
		newCitation = this.repo.save(newCitation);
		
		return new CitationDTO(newCitation);
	}
	
}

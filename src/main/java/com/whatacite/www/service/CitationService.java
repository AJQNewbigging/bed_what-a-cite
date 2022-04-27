package com.whatacite.www.service;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	private final String[] dullWords = {"in", "the", "and", "have", "has", "do", "but", "with", "to"};
	
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
	
	public List<CitationDTO> getByName(String search) {
		for (String str : dullWords) {
			search.replaceAll("\\s" + str + "\\s", " ");
		}
		String[] keywords = search.split(" ");
		
		List<Citation> citations = this.repo.findAll();
		// Remove citations if their titles contain none of the keywords
		citations.removeIf(
				c -> !Arrays.stream(keywords).anyMatch(k -> c.getTitle().contains(k)));
		
		return citations.stream().map(c -> new CitationDTO(c)).toList();
	}
	
}

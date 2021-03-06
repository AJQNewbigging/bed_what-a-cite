package com.whatacite.www.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whatacite.www.model.Citation;
import com.whatacite.www.model.Project;
import com.whatacite.www.model.dto.CitationCreationDTO;
import com.whatacite.www.model.dto.CitationDTO;
import com.whatacite.www.repository.CitationRepository;
import com.whatacite.www.repository.ProjectRepository;

@Service
public class CitationService {

	@Autowired
	private CitationRepository repo;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	private final String[] dullWords = {"in", "the", "and", "have", "has", "do", "but", "with", "to"};
	
	public List<CitationDTO> getAll() {
		List<CitationDTO> dtos = new ArrayList<>();
		
		List<Citation> citations = this.repo.findAllByOrderByLastUpdatedDesc();
		
		citations.forEach(c -> dtos.add(new CitationDTO(c)));
		
		return dtos;
	}
	
	public CitationDTO save(CitationCreationDTO dto, Long projectId) {
		Citation newCitation = new Citation(dto);
		newCitation = this.repo.save(newCitation);
		
		Project p = projectRepo.getById(projectId);
		p.addCitation(newCitation);
		this.projectRepo.save(p);
		
		return new CitationDTO(newCitation);
	}
	
	public CitationDTO update(CitationCreationDTO dto, Long id) {
		Optional<Citation> opt = this.repo.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		Citation existing = opt.get();
		Citation updates = new Citation(dto, id);
		
		updates.setProject(existing.getProject());
		existing = this.repo.save(updates);
		
		return new CitationDTO(existing);
	}
	
	public List<CitationDTO> getByName(String search) {
		for (String str : dullWords) {
			search = search.replaceAll("\\s" + str + "\\s", " ");
		}
		String[] keywords = search.split(" ");
		
		List<Citation> citations = this.repo.findAll();
		// Remove citations if their titles contain none of the keywords
		citations.removeIf(
				c -> !Arrays.stream(keywords).anyMatch(k -> c.getTitle().contains(k)));
		
		return citations.stream().map(c -> new CitationDTO(c)).toList();
	}
	
	public Optional<CitationDTO> get(Long id) {
		Optional<Citation> citation = this.repo.findById(id);
		
		if (citation.isEmpty() ) {
			return Optional.empty();
		}
		
		return Optional.of(new CitationDTO(citation.get()));
	}
	
	public boolean delete(long id) {
		this.repo.deleteById(id);
		
		return this.repo.findById(id).isEmpty();
	}
	
	public List<CitationDTO> getByProjectId(Long id) {
		Optional<Project> project = this.projectRepo.findById(id);
		if (project.isPresent()) {
			return project.get().getCitations().stream().map(c -> new CitationDTO(c)).toList();
		}
		
		return new ArrayList<>();
	}
	
}

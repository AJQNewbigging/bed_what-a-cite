package com.whatacite.www.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whatacite.www.model.dto.CitationCreationDTO;
import com.whatacite.www.model.dto.CitationDTO;
import com.whatacite.www.service.CitationService;

@RestController
@RequestMapping("/citation")
public class CitationController {

	@Autowired
	private CitationService service;
	
	@GetMapping
	public ResponseEntity<List<CitationDTO>> find() {
		
		List<CitationDTO> dtos = this.service.getAll();
		if (dtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping("/{projectId}")
	public ResponseEntity<CitationDTO> create(@PathVariable("projectId") Long projectId,
			@Valid @RequestBody CitationCreationDTO newDto) {
		 CitationDTO dto = this.service.save(newDto, projectId);
		 
		 return dto.getId() != null ? ResponseEntity.ok(dto) : ResponseEntity.badRequest().build();
	}
	
}

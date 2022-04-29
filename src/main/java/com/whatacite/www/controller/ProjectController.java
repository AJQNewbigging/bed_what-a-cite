package com.whatacite.www.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.whatacite.www.model.dto.ProjectCreationDTO;
import com.whatacite.www.model.dto.ProjectDTO;
import com.whatacite.www.service.ProjectService;

@RestController
@RequestMapping("/project")
@CrossOrigin("*")
public class ProjectController {

	@Autowired
	private ProjectService service;
	
	@GetMapping
	public ResponseEntity<List<ProjectDTO>> findAll() {

		List<ProjectDTO> dtos = this.service.getAll();
		if (dtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectDTO> find(@PathVariable("id") Long id) {
		Optional<ProjectDTO> optDto = this.service.get(id);
		
		if (optDto.isEmpty()) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(optDto.get());
	}
	
	@PostMapping
	public ResponseEntity<ProjectDTO> create(@Valid @RequestBody ProjectCreationDTO newDTO) {
		ProjectDTO dto = this.service.save(newDTO);
		
		return dto.getId() != null ? ResponseEntity.ok(dto) : ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return this.service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProjectDTO> update(@PathVariable("id") Long id,
			@Valid @RequestBody ProjectCreationDTO dto) {
		ProjectDTO updatedDto = this.service.save(dto, id);
		
		return ResponseEntity.ok(updatedDto);
	}
	
	@DeleteMapping("/{projectId}/citation/{citeId}")
	public ResponseEntity<?> deleteCitation(@PathVariable("projectId") Long projectId, @PathVariable("citeId") Long citationId) {
		return this.service.deleteCitation(projectId, citationId) ? ResponseEntity.ok().build() :
			ResponseEntity.notFound().build();
	}
	
	// Validation handler, with guidance from Baeldung
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidation (MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    
	    return errors;
	}
	
}

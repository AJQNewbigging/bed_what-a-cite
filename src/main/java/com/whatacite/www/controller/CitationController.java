package com.whatacite.www.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@GetMapping("/search")
	public ResponseEntity<List<CitationDTO>> findByTitle(@RequestParam("q") String search) {
		List<CitationDTO> dtos = this.service.getByName(search);
		
		return dtos.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CitationDTO> get(@PathVariable("id") Long id) {
		
		Optional<CitationDTO> opt = this.service.get(id);
		
		return opt.isPresent() ? ResponseEntity.ok(opt.get()) : ResponseEntity.notFound().build();	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CitationDTO> update(@Valid @RequestBody CitationCreationDTO dto,
			@PathVariable("id") Long id) {
		CitationDTO updated = this.service.update(dto, id);
		
		return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		return this.service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
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

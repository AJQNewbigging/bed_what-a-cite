package com.whatacite.www.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.whatacite.www.model.dto.CitationCreationDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "citation")
public class Citation {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private String authorLine;
	
	@Temporal(TemporalType.DATE)
	private Calendar published;
	
	@Getter
	@Setter
	private String publisher;
	
	@Getter
	@Setter
	private String doi;
	
	@Getter
	@Setter
	@Column(columnDefinition = "varchar(500)")
	private String abstrac;
	
	@Getter
	@Setter
	private Date lastUpdated;
	
	@Getter
	@Setter
	@ManyToMany(mappedBy = "citations")
	private Set<Project> project;
	
	public Citation(CitationCreationDTO dto) {
		this.project = new HashSet<>();
		this.title = dto.getTitle();
		this.authorLine = dto.getAuthorLine();
		this.setPublished(dto.getYearPublished());
		this.publisher = dto.getPublisher();
		this.doi = dto.getDoi();
		this.abstrac = dto.getAbstrac();
		this.lastUpdated = new Date();
	}
	
	public Citation(CitationCreationDTO dto, Long id) {
		this(dto);
		this.id = id;
		this.lastUpdated = new Date();
	}
	
	public void setPublished(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		this.published = cal;
	}
	
	public int getPublishedYear() {
		return this.published.get(Calendar.YEAR);
	}
	
	public String asCitation() {
		StringBuilder builder = new StringBuilder(
				String.format("%s (%d). %s. %s.",
						authorLine, getPublishedYear(), title, publisher));
		if (!(doi == null || doi.isEmpty())) {
			builder.append(" " + doi);
		}
		return builder.toString();
	}
	
	public void addProject(Project p) {
		this.project.add(p);
	}
	
}

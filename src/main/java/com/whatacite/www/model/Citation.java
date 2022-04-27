package com.whatacite.www.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	@ManyToMany(mappedBy = "citations")
	private List<Project> project;
	
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
		if (!doi.isEmpty()) {
			builder.append(" " + doi);
		}
		return builder.toString();
	}
	
}

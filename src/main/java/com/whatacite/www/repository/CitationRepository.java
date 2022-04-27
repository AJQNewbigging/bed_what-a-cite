package com.whatacite.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whatacite.www.model.Citation;

@Repository
public interface CitationRepository extends JpaRepository<Citation, Long> {

}

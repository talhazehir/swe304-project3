package com.swe212.patentmanagement.repository;

import com.swe212.patentmanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

package com.swe212.patentmanagement.repository;

import com.swe212.patentmanagement.model.Patent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentRepository extends JpaRepository<Patent, Long> {
}

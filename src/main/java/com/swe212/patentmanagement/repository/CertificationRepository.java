package com.swe212.patentmanagement.repository;

import com.swe212.patentmanagement.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    List<Certification> findByAuthorId(Long authorId);
    List<Certification> findByPatentId(Long patentId);
}

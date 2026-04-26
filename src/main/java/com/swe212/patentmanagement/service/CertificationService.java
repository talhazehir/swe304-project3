package com.swe212.patentmanagement.service;

import com.swe212.patentmanagement.dto.CertificationRequest;
import com.swe212.patentmanagement.dto.CertificationResponse;
import com.swe212.patentmanagement.exception.ResourceNotFoundException;
import com.swe212.patentmanagement.model.Author;
import com.swe212.patentmanagement.model.Certification;
import com.swe212.patentmanagement.model.Patent;
import com.swe212.patentmanagement.repository.AuthorRepository;
import com.swe212.patentmanagement.repository.CertificationRepository;
import com.swe212.patentmanagement.repository.PatentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final AuthorRepository authorRepository;
    private final PatentRepository patentRepository;

    public CertificationService(CertificationRepository certificationRepository,
                                AuthorRepository authorRepository,
                                PatentRepository patentRepository) {
        this.certificationRepository = certificationRepository;
        this.authorRepository = authorRepository;
        this.patentRepository = patentRepository;
    }

    @Transactional(readOnly = true)
    public List<CertificationResponse> findAll(Long authorId, Long patentId) {
        List<Certification> certifications;

        if (authorId != null) {
            certifications = certificationRepository.findByAuthorId(authorId);
        } else if (patentId != null) {
            certifications = certificationRepository.findByPatentId(patentId);
        } else {
            certifications = certificationRepository.findAll();
        }

        return certifications.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CertificationResponse findById(Long id) {
        return toResponse(getCertification(id));
    }

    public CertificationResponse create(CertificationRequest request) {
        Certification certification = new Certification();
        applyRequest(certification, request);
        return toResponse(certificationRepository.save(certification));
    }

    public CertificationResponse update(Long id, CertificationRequest request) {
        Certification certification = getCertification(id);
        applyRequest(certification, request);
        return toResponse(certificationRepository.save(certification));
    }

    public void delete(Long id) {
        Certification certification = getCertification(id);
        certificationRepository.delete(certification);
    }

    private void applyRequest(Certification certification, CertificationRequest request) {
        Author author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + request.authorId()));
        Patent patent = patentRepository.findById(request.patentId())
                .orElseThrow(() -> new ResourceNotFoundException("Patent not found: " + request.patentId()));

        certification.setAuthor(author);
        certification.setPatent(patent);
        certification.setIssueDate(request.issueDate());
        certification.setDurationYear(request.durationYear());
    }

    private Certification getCertification(Long id) {
        return certificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certification not found: " + id));
    }

    private CertificationResponse toResponse(Certification certification) {
        return new CertificationResponse(
                certification.getId(),
                certification.getAuthor().getId(),
                certification.getAuthor().getName(),
                certification.getPatent().getId(),
                certification.getPatent().getTitle(),
                certification.getIssueDate(),
                certification.getDurationYear()
        );
    }
}

package com.swe212.patentmanagement.controller;

import com.swe212.patentmanagement.dto.CertificationRequest;
import com.swe212.patentmanagement.dto.CertificationResponse;
import com.swe212.patentmanagement.service.CertificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @GetMapping({"", "/"})
    public List<CertificationResponse> findAll(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long patentId
    ) {
        return certificationService.findAll(authorId, patentId);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public CertificationResponse findById(@PathVariable Long id) {
        return certificationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CertificationResponse create(@Valid @RequestBody CertificationRequest request) {
        return certificationService.create(request);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public CertificationResponse update(@PathVariable Long id, @Valid @RequestBody CertificationRequest request) {
        return certificationService.update(id, request);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        certificationService.delete(id);
    }
}

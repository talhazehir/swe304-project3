package com.swe212.patentmanagement.service;

import com.swe212.patentmanagement.dto.PatentRequest;
import com.swe212.patentmanagement.dto.PatentResponse;
import com.swe212.patentmanagement.exception.ResourceNotFoundException;
import com.swe212.patentmanagement.model.Patent;
import com.swe212.patentmanagement.repository.PatentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatentService {

    private final PatentRepository patentRepository;

    public PatentService(PatentRepository patentRepository) {
        this.patentRepository = patentRepository;
    }

    public List<PatentResponse> findAll() {
        return patentRepository.findAll().stream().map(this::toResponse).toList();
    }

    public PatentResponse findById(Long id) {
        return toResponse(getPatent(id));
    }

    public PatentResponse create(PatentRequest request) {
        Patent patent = new Patent();
        patent.setTitle(request.title());
        patent.setDescription(request.description());
        return toResponse(patentRepository.save(patent));
    }

    public PatentResponse update(Long id, PatentRequest request) {
        Patent patent = getPatent(id);
        patent.setTitle(request.title());
        patent.setDescription(request.description());
        return toResponse(patentRepository.save(patent));
    }

    public void delete(Long id) {
        Patent patent = getPatent(id);
        patentRepository.delete(patent);
    }

    private Patent getPatent(Long id) {
        return patentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patent not found: " + id));
    }

    private PatentResponse toResponse(Patent patent) {
        return new PatentResponse(patent.getId(), patent.getTitle(), patent.getDescription());
    }
}

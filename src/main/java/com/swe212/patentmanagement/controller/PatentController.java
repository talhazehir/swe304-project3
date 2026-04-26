package com.swe212.patentmanagement.controller;

import com.swe212.patentmanagement.dto.PatentRequest;
import com.swe212.patentmanagement.dto.PatentResponse;
import com.swe212.patentmanagement.service.PatentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patents")
public class PatentController {

    private final PatentService patentService;

    public PatentController(PatentService patentService) {
        this.patentService = patentService;
    }

    @GetMapping({"", "/"})
    public List<PatentResponse> findAll() {
        return patentService.findAll();
    }

    @GetMapping({"/{id}", "/{id}/"})
    public PatentResponse findById(@PathVariable Long id) {
        return patentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatentResponse create(@Valid @RequestBody PatentRequest request) {
        return patentService.create(request);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public PatentResponse update(@PathVariable Long id, @Valid @RequestBody PatentRequest request) {
        return patentService.update(id, request);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        patentService.delete(id);
    }
}

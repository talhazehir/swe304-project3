package com.swe212.patentmanagement.controller;

import com.swe212.patentmanagement.dto.AuthorRequest;
import com.swe212.patentmanagement.dto.AuthorResponse;
import com.swe212.patentmanagement.service.AuthorService;
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
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping({"", "/"})
    public List<AuthorResponse> findAll() {
        return authorService.findAll();
    }

    @GetMapping({"/{id}", "/{id}/"})
    public AuthorResponse findById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse create(@Valid @RequestBody AuthorRequest request) {
        return authorService.create(request);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public AuthorResponse update(@PathVariable Long id, @Valid @RequestBody AuthorRequest request) {
        return authorService.update(id, request);
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}

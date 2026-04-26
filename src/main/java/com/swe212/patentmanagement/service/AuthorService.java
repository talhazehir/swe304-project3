package com.swe212.patentmanagement.service;

import com.swe212.patentmanagement.dto.AuthorRequest;
import com.swe212.patentmanagement.dto.AuthorResponse;
import com.swe212.patentmanagement.exception.ResourceNotFoundException;
import com.swe212.patentmanagement.model.Author;
import com.swe212.patentmanagement.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorResponse> findAll() {
        return authorRepository.findAll().stream().map(this::toResponse).toList();
    }

    public AuthorResponse findById(Long id) {
        return toResponse(getAuthor(id));
    }

    public AuthorResponse create(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.name());
        author.setAddress(request.address());
        return toResponse(authorRepository.save(author));
    }

    public AuthorResponse update(Long id, AuthorRequest request) {
        Author author = getAuthor(id);
        author.setName(request.name());
        author.setAddress(request.address());
        return toResponse(authorRepository.save(author));
    }

    public void delete(Long id) {
        Author author = getAuthor(id);
        authorRepository.delete(author);
    }

    private Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + id));
    }

    private AuthorResponse toResponse(Author author) {
        return new AuthorResponse(author.getId(), author.getName(), author.getAddress());
    }
}

package com.swe212.patentmanagement.dto;

public record PatentResponse(
        Long id,
        String title,
        String description
) {
}

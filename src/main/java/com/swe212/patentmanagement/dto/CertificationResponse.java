package com.swe212.patentmanagement.dto;

import java.time.LocalDate;

public record CertificationResponse(
        Long id,
        Long authorId,
        String authorName,
        Long patentId,
        String patentTitle,
        LocalDate issueDate,
        Integer durationYear
) {
}

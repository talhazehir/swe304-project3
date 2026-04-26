package com.swe212.patentmanagement.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CertificationRequest(
        @NotNull Long authorId,
        @NotNull Long patentId,
        @NotNull LocalDate issueDate,
        @NotNull @Min(1) @Max(50) Integer durationYear
) {
}

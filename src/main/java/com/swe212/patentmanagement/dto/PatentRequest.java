package com.swe212.patentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatentRequest(
        @NotBlank @Size(max = 32) String title,
        @NotBlank @Size(max = 64) String description
) {
}

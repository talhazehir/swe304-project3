package com.swe212.patentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorRequest(
        @NotBlank @Size(max = 16) String name,
        @NotBlank @Size(max = 32) String address
) {
}

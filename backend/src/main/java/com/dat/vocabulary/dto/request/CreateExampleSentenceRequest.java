package com.dat.vocabulary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateExampleSentenceRequest(
        @NotBlank
        String sentenceJp,

        @NotBlank
        String sentenceEn,

        @NotBlank
        String sentenceVi,

        @NotBlank
        @Size(max = 255)
        String highlightText
) {
}
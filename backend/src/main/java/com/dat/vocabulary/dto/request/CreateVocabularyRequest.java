package com.dat.vocabulary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateVocabularyRequest(
        @NotBlank
        @Size(max = 255)
        String japanese,

        @Size(max = 255)
        String romaji,

        @NotBlank
        @Size(max = 500)
        String meaningEn,

        @NotBlank
        @Size(max = 500)
        String meaningVi,

        String exampleJp,
        String exampleEn,
        String exampleVi
) {
}
package com.dat.vocabulary.dto.response;

import java.time.LocalDateTime;

public record VocabularyResponse(
        Long id,
        String japanese,
        String romaji,
        String meaningEn,
        String meaningVi,
        String exampleJp,
        String exampleEn,
        String exampleVi,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
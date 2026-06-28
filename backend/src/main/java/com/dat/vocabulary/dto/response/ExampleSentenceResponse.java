package com.dat.vocabulary.dto.response;

import java.time.LocalDateTime;

public record ExampleSentenceResponse(
        Long id,
        String sentenceJp,
        String sentenceEn,
        String sentenceVi,
        String highlightText,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
package com.dat.vocabulary.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record VocabularyResponse(
        Long id,
        String kana,
        String kanji,
        String romaji,
        String meaningEn,
        String meaningVi,
        String jlptLevel,
        String partOfSpeech,
        String note,
        List<ExampleSentenceResponse> exampleSentences,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
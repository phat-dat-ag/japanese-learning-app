package com.dat.vocabulary.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateVocabularyRequest(
        @NotBlank
        @Size(max = 255)
        String kana,

        @Size(max = 255)
        String kanji,

        @Size(max = 255)
        String romaji,

        @NotBlank
        @Size(max = 500)
        String meaningEn,

        @NotBlank
        @Size(max = 500)
        String meaningVi,

        @Size(max = 10)
        String jlptLevel,

        @Size(max = 50)
        String partOfSpeech,

        String note,

        @Valid
        CreateExampleSentenceRequest exampleSentence
) {
}
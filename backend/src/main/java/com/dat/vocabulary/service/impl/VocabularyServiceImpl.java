package com.dat.vocabulary.service.impl;

import com.dat.vocabulary.dto.request.CreateVocabularyRequest;
import com.dat.vocabulary.dto.response.ExampleSentenceResponse;
import com.dat.vocabulary.dto.response.VocabularyResponse;
import com.dat.vocabulary.entity.ExampleSentence;
import com.dat.vocabulary.entity.Vocabulary;
import com.dat.vocabulary.exception.VocabularyNotFoundException;
import com.dat.vocabulary.repository.ExampleSentenceRepository;
import com.dat.vocabulary.repository.VocabularyRepository;
import com.dat.vocabulary.service.VocabularyService;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class VocabularyServiceImpl implements VocabularyService {

    @Inject
    VocabularyRepository vocabularyRepository;

    @Inject
    ExampleSentenceRepository exampleSentenceRepository;

    @WithSession
    public Uni<List<VocabularyResponse>> findAllVocabularies() {
        return vocabularyRepository.listAll()
                .map(vocabularies -> vocabularies.stream()
                        .map(vocabulary -> toVocabularyResponse(vocabulary, List.of()))
                        .toList()
                );
    }

    @WithSession
    public Uni<VocabularyResponse> findVocabularyById(Long vocabularyId) {
        return vocabularyRepository.findById(vocabularyId)
                .onItem().ifNull().failWith(
                        () -> new VocabularyNotFoundException(vocabularyId)
                )
                .chain(vocabulary ->
                        exampleSentenceRepository.findByVocabularyId(vocabulary.id)
                                .map(exampleSentences -> toVocabularyResponse(
                                        vocabulary, exampleSentences
                                ))
                );
    }

    @WithTransaction
    public Uni<VocabularyResponse> createVocabulary(CreateVocabularyRequest request) {
        Vocabulary vocabulary = new Vocabulary();

        vocabulary.kana = request.kana();
        vocabulary.kanji = request.kanji();
        vocabulary.romaji = request.romaji();
        vocabulary.meaningEn = request.meaningEn();
        vocabulary.meaningVi = request.meaningVi();
        vocabulary.jlptLevel = request.jlptLevel();
        vocabulary.partOfSpeech = request.partOfSpeech();
        vocabulary.note = request.note();

        return vocabularyRepository.persist(vocabulary)
                .chain(() -> {
                    if (request.exampleSentence() == null) {
                        return Uni.createFrom().item(List.<ExampleSentence>of());
                    }

                    ExampleSentence exampleSentence = new ExampleSentence();
                    exampleSentence.vocabulary = vocabulary;
                    exampleSentence.sentenceJp = request.exampleSentence().sentenceJp();
                    exampleSentence.sentenceEn = request.exampleSentence().sentenceEn();
                    exampleSentence.sentenceVi = request.exampleSentence().sentenceVi();
                    exampleSentence.highlightText = request.exampleSentence().highlightText();

                    return exampleSentenceRepository.persist(exampleSentence)
                            .replaceWith(List.of(exampleSentence));
                })
                .map(exampleSentences -> toVocabularyResponse(
                        vocabulary, exampleSentences
                ));
    }

    private VocabularyResponse toVocabularyResponse(
            Vocabulary vocabulary, List<ExampleSentence> exampleSentences
    ) {
        return new VocabularyResponse(
                vocabulary.id,
                vocabulary.kana,
                vocabulary.kanji,
                vocabulary.romaji,
                vocabulary.meaningEn,
                vocabulary.meaningVi,
                vocabulary.jlptLevel,
                vocabulary.partOfSpeech,
                vocabulary.note,
                exampleSentences.stream()
                        .map(this::toExampleSentenceResponse)
                        .toList(),
                vocabulary.createdAt,
                vocabulary.updatedAt
        );
    }

    private ExampleSentenceResponse toExampleSentenceResponse(ExampleSentence exampleSentence) {
        return new ExampleSentenceResponse(
                exampleSentence.id,
                exampleSentence.sentenceJp,
                exampleSentence.sentenceEn,
                exampleSentence.sentenceVi,
                exampleSentence.highlightText,
                exampleSentence.createdAt,
                exampleSentence.updatedAt
        );
    }
}
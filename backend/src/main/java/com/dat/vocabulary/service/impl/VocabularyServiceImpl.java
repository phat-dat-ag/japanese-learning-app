package com.dat.vocabulary.service.impl;

import com.dat.vocabulary.dto.request.CreateVocabularyRequest;
import com.dat.vocabulary.dto.response.VocabularyResponse;
import com.dat.vocabulary.entity.Vocabulary;
import com.dat.vocabulary.exception.VocabularyNotFoundException;
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

    @WithSession
    public Uni<List<VocabularyResponse>> findAll() {
        return vocabularyRepository.listAll()
                .map(vocabularies ->
                        vocabularies.stream()
                                .map(this::toResponse)
                                .toList()
                );
    }

    @WithSession
    public Uni<VocabularyResponse> findById(Long vocabularyId) {
        return vocabularyRepository.findById(vocabularyId)
                .onItem().ifNull().failWith(
                        () -> new VocabularyNotFoundException(vocabularyId)
                )
                .map(this::toResponse);
    }

    @WithTransaction
    public Uni<VocabularyResponse> create(CreateVocabularyRequest request) {

        Vocabulary vocabulary = new Vocabulary();

        vocabulary.japanese = request.japanese();
        vocabulary.romaji = request.romaji();
        vocabulary.meaningEn = request.meaningEn();
        vocabulary.meaningVi = request.meaningVi();
        vocabulary.exampleJp = request.exampleJp();
        vocabulary.exampleEn = request.exampleEn();
        vocabulary.exampleVi = request.exampleVi();

        return vocabularyRepository.persist(vocabulary)
                .replaceWith(vocabulary)
                .map(this::toResponse);
    }

    private VocabularyResponse toResponse(Vocabulary vocabulary) {
        return new VocabularyResponse(
                vocabulary.id,
                vocabulary.japanese,
                vocabulary.romaji,
                vocabulary.meaningEn,
                vocabulary.meaningVi,
                vocabulary.exampleJp,
                vocabulary.exampleEn,
                vocabulary.exampleVi,
                vocabulary.createdAt,
                vocabulary.updatedAt
        );
    }
}
package com.dat.vocabulary.service;

import com.dat.vocabulary.dto.request.CreateVocabularyRequest;
import com.dat.vocabulary.dto.response.VocabularyResponse;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface VocabularyService {

    Uni<List<VocabularyResponse>> findAllVocabularies();

    Uni<VocabularyResponse> findVocabularyById(Long vocabularyId);

    Uni<VocabularyResponse> createVocabulary(CreateVocabularyRequest request);
}
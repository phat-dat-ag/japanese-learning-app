package com.dat.vocabulary.exception;

import com.dat.common.exception.ResourceNotFoundException;

public class VocabularyNotFoundException extends ResourceNotFoundException {

    public VocabularyNotFoundException(Long vocabularyId) {
        super("Vocabulary not found with id: " + vocabularyId);
    }
}
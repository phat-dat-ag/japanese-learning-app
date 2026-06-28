package com.dat.vocabulary.repository;

import com.dat.vocabulary.entity.ExampleSentence;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ExampleSentenceRepository implements PanacheRepository<ExampleSentence> {

    public Uni<List<ExampleSentence>> findByVocabularyId(Long vocabularyId) {
        return list("vocabulary.id", vocabularyId);
    }
}

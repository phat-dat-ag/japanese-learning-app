package com.dat.vocabulary.repository;

import com.dat.vocabulary.entity.Vocabulary;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VocabularyRepository implements PanacheRepository<Vocabulary> {
}

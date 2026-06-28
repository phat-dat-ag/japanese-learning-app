package com.dat.vocabulary.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "example_sentences")
public class ExampleSentence extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vocabulary_id", nullable = false)
    public Vocabulary vocabulary;

    @Column(name = "sentence_jp", nullable = false, columnDefinition = "TEXT")
    public String sentenceJp;

    @Column(name = "sentence_en", nullable = false, columnDefinition = "TEXT")
    public String sentenceEn;

    @Column(name = "sentence_vi", nullable = false, columnDefinition = "TEXT")
    public String sentenceVi;

    @Column(name = "highlight_text", nullable = false, length = 255)
    public String highlightText;

    @Column(name = "created_at", insertable = false, updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    public LocalDateTime updatedAt;
}
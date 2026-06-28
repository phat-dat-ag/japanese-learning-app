package com.dat.vocabulary.entity;

import com.dat.common.entity.AuditableEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "example_sentences")
public class ExampleSentence extends AuditableEntity {

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
}
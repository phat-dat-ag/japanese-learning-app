package com.dat.vocabulary.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@Entity
@Table(name = "vocabularies")
public class Vocabulary extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 255)
    public String kana;

    @Column(length = 255)
    public String kanji;

    @Column(length = 255)
    public String romaji;

    @Column(name = "meaning_en", nullable = false, length = 500)
    public String meaningEn;

    @Column(name = "meaning_vi", nullable = false, length = 500)
    public String meaningVi;

    @Column(name = "jlpt_level", length = 10)
    public String jlptLevel;

    @Column(name = "part_of_speech", length = 50)
    public String partOfSpeech;

    @Column(columnDefinition = "TEXT")
    public String note;

    @Column(name = "created_at", insertable = false, updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    public LocalDateTime updatedAt;
}
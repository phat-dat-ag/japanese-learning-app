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
    public String japanese;

    @Column(length = 255)
    public String romaji;

    @Column(name = "meaning_en", nullable = false, length = 500)
    public String meaningEn;

    @Column(name = "meaning_vi", nullable = false, length = 500)
    public String meaningVi;

    @Column(name = "example_jp", columnDefinition = "TEXT")
    public String exampleJp;

    @Column(name = "example_en", columnDefinition = "TEXT")
    public String exampleEn;

    @Column(name = "example_vi", columnDefinition = "TEXT")
    public String exampleVi;

    @Column(name = "created_at", insertable = false, updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    public LocalDateTime updatedAt;
}

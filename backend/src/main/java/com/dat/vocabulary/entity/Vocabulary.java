package com.dat.vocabulary.entity;

import com.dat.common.entity.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "vocabularies")
public class Vocabulary extends AuditableEntity {

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
}
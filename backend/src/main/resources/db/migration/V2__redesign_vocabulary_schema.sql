DROP TABLE IF EXISTS example_sentences;
DROP TABLE IF EXISTS vocabularies;

CREATE TABLE vocabularies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    kana VARCHAR(255) NOT NULL,
    kanji VARCHAR(255),
    romaji VARCHAR(255),

    meaning_en VARCHAR(500) NOT NULL,
    meaning_vi VARCHAR(500) NOT NULL,

    jlpt_level VARCHAR(10),
    part_of_speech VARCHAR(50),

    note TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_vocabularies_kana_kanji UNIQUE (kana, kanji)
);

CREATE TABLE example_sentences (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    vocabulary_id BIGINT NOT NULL,

    sentence_jp TEXT NOT NULL,
    sentence_en TEXT NOT NULL,
    sentence_vi TEXT NOT NULL,

    highlight_text VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_example_sentences_vocabulary
        FOREIGN KEY (vocabulary_id)
        REFERENCES vocabularies(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_vocabularies_kana ON vocabularies(kana);
CREATE INDEX idx_vocabularies_jlpt_level ON vocabularies(jlpt_level);
CREATE INDEX idx_vocabularies_part_of_speech ON vocabularies(part_of_speech);
CREATE INDEX idx_example_sentences_vocabulary_id ON example_sentences(vocabulary_id);
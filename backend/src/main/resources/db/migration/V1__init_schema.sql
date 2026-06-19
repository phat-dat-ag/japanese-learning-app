CREATE TABLE vocabularies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    japanese VARCHAR(255) NOT NULL,
    romaji VARCHAR(255),
    meaning_en VARCHAR(500) NOT NULL,
    meaning_vi VARCHAR(500) NOT NULL,
    example_jp TEXT,
    example_en TEXT,
    example_vi TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
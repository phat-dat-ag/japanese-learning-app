export interface VocabularyListData {
  vocabularies: VocabularySummary[];
}

export interface VocabularyData {
  vocabulary: VocabularyDetail;
}

export interface VocabularyBase {
  id: number;
  kana: string;
  kanji: string | null;
  romaji: string;
  meaningEn: string;
  meaningVi: string;
  jlptLevel: string;
  partOfSpeech: string;
  note: string | null;
}

export interface VocabularySummary extends VocabularyBase {
  exampleSentences: [];
}

export interface VocabularyDetail extends VocabularyBase {
  exampleSentences: ExampleSentence[];
}

export interface ExampleSentence {
  id: number;
  sentenceJp: string;
  sentenceEn: string;
  sentenceVi: string;
  highlightText: string;
}

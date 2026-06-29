import { Component, input, output } from '@angular/core';
import { VocabularyDetail, VocabularySummary } from '../../models/vocabulary.model';

@Component({
  selector: 'app-flash-card',
  imports: [],
  templateUrl: './flash-card.html',
  styleUrl: './flash-card.css',
})
export class FlashCard {
  vocabulary = input.required<VocabularySummary>();
  detail = input<VocabularyDetail | null>(null);
  flipped = input(false);
  flip = output<void>();

  highlightedSentence(sentence: string, keyword: string): string {
    if (!keyword) return sentence;

    return sentence.replace(
      keyword,
      `<mark class="rounded bg-yellow-200 px-1 font-semibold">${keyword}</mark>`,
    );
  }
}

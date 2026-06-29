import { Component, DestroyRef, computed, inject, OnInit, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

import { VocabularyService } from '../../services/vocabulary.service';
import { VocabularyDetail, VocabularySummary } from '../../models/vocabulary.model';
import { FlashCard } from '../../components/flash-card/flash-card';

@Component({
  selector: 'app-vocabulary-study',
  imports: [FlashCard],
  templateUrl: './vocabulary-study.html',
  styleUrl: './vocabulary-study.css',
})
export class VocabularyStudy implements OnInit {
  private readonly vocabularyService = inject(VocabularyService);
  private readonly destroyRef = inject(DestroyRef);

  vocabularies = signal<VocabularySummary[]>([]);
  currentIndex = signal(0);
  currentDetail = signal<VocabularyDetail | null>(null);
  flipped = signal(false);

  loading = signal(false);
  detailLoading = signal(false);
  errorMessage = signal<string | null>(null);

  currentVocabulary = computed(() => this.vocabularies()[this.currentIndex()] ?? null);
  total = computed(() => this.vocabularies().length);

  ngOnInit(): void {
    this.loadVocabularies();
  }

  flipCard(): void {
    if (!this.flipped()) {
      this.loadCurrentDetail();
    }

    this.flipped.update((value) => !value);
  }

  previousCard(): void {
    if (this.currentIndex() === 0) return;

    this.currentIndex.update((index) => index - 1);
    this.resetCard();
  }

  nextCard(): void {
    if (this.currentIndex() >= this.total() - 1) return;

    this.currentIndex.update((index) => index + 1);
    this.resetCard();
  }

  private loadVocabularies(): void {
    this.loading.set(true);
    this.errorMessage.set(null);

    this.vocabularyService
      .getAllVocabularies()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (vocabularies) => {
          this.vocabularies.set(vocabularies);
          this.loading.set(false);
        },
        error: () => {
          this.errorMessage.set('Cannot load vocabularies.');
          this.loading.set(false);
        },
      });
  }

  private loadCurrentDetail(): void {
    const vocabulary = this.currentVocabulary();

    if (!vocabulary) return;
    if (this.currentDetail()?.id === vocabulary.id) return;

    this.detailLoading.set(true);

    this.vocabularyService
      .getVocabularyById(vocabulary.id)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (detail) => {
          this.currentDetail.set(detail);
          this.detailLoading.set(false);
        },
        error: () => {
          this.errorMessage.set('Cannot load vocabulary detail.');
          this.detailLoading.set(false);
        },
      });
  }

  private resetCard(): void {
    this.flipped.set(false);
    this.currentDetail.set(null);
    this.errorMessage.set(null);
  }
}

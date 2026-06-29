import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ApiService } from '../../../core/services/api.service';
import { ApiResponse } from '../../../shared/models/api-response.model';

import {
  VocabularyData,
  VocabularyDetail,
  VocabularyListData,
  VocabularySummary,
} from '../models/vocabulary.model';

@Injectable({
  providedIn: 'root',
})
export class VocabularyService {
  private readonly api = inject(ApiService);
  private readonly path = '/vocabularies';

  getAllVocabularies(): Observable<VocabularySummary[]> {
    return this.api
      .get<ApiResponse<VocabularyListData>>(this.path)
      .pipe(map((r) => r.data.vocabularies));
  }

  getVocabularyById(id: number): Observable<VocabularyDetail> {
    return this.api
      .get<ApiResponse<VocabularyData>>(`${this.path}/${id}`)
      .pipe(map((r) => r.data.vocabulary));
  }
}

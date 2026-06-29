import { Routes } from '@angular/router';
import { LearnerLayout } from './layouts/learner-layout/learner-layout';
import { VocabularyStudy } from './features/vocabulary/pages/vocabulary-study/vocabulary-study';

export const routes: Routes = [
  {
    path: '',
    component: LearnerLayout,
    children: [
      {
        path: '',
        redirectTo: 'vocabularies',
        pathMatch: 'full',
      },
      {
        path: 'vocabularies',
        component: VocabularyStudy,
      },
    ],
  },
];

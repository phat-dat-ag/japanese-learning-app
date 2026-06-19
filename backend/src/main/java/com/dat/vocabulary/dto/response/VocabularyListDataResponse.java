package com.dat.vocabulary.dto.response;

import java.util.List;

public record VocabularyListDataResponse(
        List<VocabularyResponse> vocabularies
) {
}

package com.dat.vocabulary.resource;

import com.dat.common.dto.ApiResponse;
import com.dat.common.resource.BaseResource;
import com.dat.vocabulary.dto.request.CreateVocabularyRequest;
import com.dat.vocabulary.dto.response.VocabularyDataResponse;
import com.dat.vocabulary.dto.response.VocabularyListDataResponse;
import com.dat.vocabulary.service.VocabularyService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/vocabularies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VocabularyResource extends BaseResource {

    @Inject
    VocabularyService vocabularyService;

    @GET
    public Uni<ApiResponse<VocabularyListDataResponse>> findAll() {
        return vocabularyService.findAll()
                .map(vocabularies -> ok(new VocabularyListDataResponse(vocabularies)));
    }

    @GET
    @Path("/{id}")
    public Uni<ApiResponse<VocabularyDataResponse>> findById(@PathParam("id") Long vocabularyId) {
        return vocabularyService.findById(vocabularyId)
                .map(vocabulary -> ok(new VocabularyDataResponse(vocabulary)));
    }

    @POST
    public Uni<Response> create(@Valid CreateVocabularyRequest request) {
        return vocabularyService.create(request)
                .map(vocabulary -> created(
                        "Vocabulary created successfully",
                        new VocabularyDataResponse(vocabulary)
                ));
    }
}
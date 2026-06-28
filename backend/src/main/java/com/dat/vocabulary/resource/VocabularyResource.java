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
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/vocabularies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VocabularyResource extends BaseResource {

    @Inject
    VocabularyService vocabularyService;

    @GET
    @Operation(
            summary = "Get all vocabularies",
            description = "Retrieve the complete list of Japanese vocabularies"
    )
    public Uni<ApiResponse<VocabularyListDataResponse>> findAllVocabularies() {
        return vocabularyService.findAllVocabularies()
                .map(vocabularies -> ok(
                        new VocabularyListDataResponse(vocabularies)
                ));
    }

    @GET
    @Operation(
            summary = "Get vocabulary by ID",
            description = "Retrieve vocabulary details using the provided vocabulary ID"
    )
    @Path("/{id}")
    public Uni<ApiResponse<VocabularyDataResponse>> findVocabularyById(
            @PathParam("id") Long vocabularyId
    ) {
        return vocabularyService.findVocabularyById(vocabularyId)
                .map(vocabulary -> ok(new VocabularyDataResponse(vocabulary)));
    }

    @POST
    @Operation(
            summary = "Create a new vocabulary",
            description = "Create and save a new Japanese vocabulary entry"
    )
    public Uni<Response> createVocabulary(@Valid CreateVocabularyRequest request) {
        return vocabularyService.createVocabulary(request)
                .map(vocabulary -> created(
                        "Vocabulary created successfully",
                        new VocabularyDataResponse(vocabulary)
                ));
    }
}
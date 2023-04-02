package com.domain.driver.designer.infrastructure.api.controllers;

import com.domain.driver.designer.application.genre.create.CreateGenreCommand;
import com.domain.driver.designer.application.genre.create.CreateGenreUseCase;
import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.infrastructure.api.GenreAPI;
import com.domain.driver.designer.infrastructure.genre.models.CreateGenreRequest;
import com.domain.driver.designer.infrastructure.genre.models.GenreResponse;
import com.domain.driver.designer.infrastructure.genre.models.ListGenreResponse;
import com.domain.driver.designer.infrastructure.genre.models.UpdateGenreRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class GenreController implements GenreAPI {

    private final CreateGenreUseCase createGenreUseCase;

    public GenreController(final CreateGenreUseCase createGenreUseCase) {
        this.createGenreUseCase = Objects.requireNonNull(createGenreUseCase);
    }

    @Override
    public ResponseEntity<?> create(final CreateGenreRequest input) {
        final var aCommand = CreateGenreCommand.with(
                input.name(),
                input.active(),
                input.categories()
        );

        final var output = this.createGenreUseCase.execute(aCommand);

        return ResponseEntity
                .created(URI.create("/genres" + output.id()))
                .body(output);
    }

    @Override
    public ResponseEntity<Pagination<ListGenreResponse>> listGenre(final String search,
                                                                   final int page,
                                                                   final int perPage,
                                                                   final String sort,
                                                                   final String direction) {
        return null;
    }

    @Override
    public ResponseEntity<GenreResponse> getById(final String anid) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateById(final String adId, final UpdateGenreRequest input) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(final String anId) {
        return null;
    }

}

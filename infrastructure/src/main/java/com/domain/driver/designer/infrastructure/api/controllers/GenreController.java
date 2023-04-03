package com.domain.driver.designer.infrastructure.api.controllers;

import com.domain.driver.designer.application.genre.create.CreateGenreCommand;
import com.domain.driver.designer.application.genre.create.CreateGenreUseCase;
import com.domain.driver.designer.application.genre.delete.DeleteGenreUseCase;
import com.domain.driver.designer.application.genre.retrieve.get.GetGenreByIdUseCase;
import com.domain.driver.designer.application.genre.retrieve.list.ListGenreUseCase;
import com.domain.driver.designer.application.genre.update.UpdateGenreCommand;
import com.domain.driver.designer.application.genre.update.UpdateGenreUseCase;
import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.pagination.SearchQuery;
import com.domain.driver.designer.infrastructure.api.GenreAPI;
import com.domain.driver.designer.infrastructure.genre.models.CreateGenreRequest;
import com.domain.driver.designer.infrastructure.genre.models.GenreResponse;
import com.domain.driver.designer.infrastructure.genre.models.ListGenreResponse;
import com.domain.driver.designer.infrastructure.genre.models.UpdateGenreRequest;
import com.domain.driver.designer.infrastructure.genre.presenters.GenreApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class GenreController implements GenreAPI {

    private final CreateGenreUseCase createGenreUseCase;
    private final GetGenreByIdUseCase getGenreByIdUseCase;
    private final UpdateGenreUseCase updateGenreUseCase;
    private final DeleteGenreUseCase deleteGenreUseCase;
    private final ListGenreUseCase listGenreUseCase;

    public GenreController(final CreateGenreUseCase createGenreUseCase,
                           final GetGenreByIdUseCase getGenreByIdUseCase,
                           final UpdateGenreUseCase updateGenreUseCase,
                           final DeleteGenreUseCase deleteGenreUseCase,
                           final ListGenreUseCase listGenreUseCase) {
        this.createGenreUseCase = Objects.requireNonNull(createGenreUseCase);
        this.getGenreByIdUseCase = Objects.requireNonNull(getGenreByIdUseCase);
        this.updateGenreUseCase = Objects.requireNonNull(updateGenreUseCase);
        this.deleteGenreUseCase = Objects.requireNonNull(deleteGenreUseCase);
        this.listGenreUseCase = Objects.requireNonNull(listGenreUseCase);
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
                .created(URI.create("/genres/" + output.id()))
                .body(output);
    }

    @Override
    public ResponseEntity<Pagination<ListGenreResponse>> listGenre(final String search,
                                                                   final int page,
                                                                   final int perPage,
                                                                   final String sort,
                                                                   final String direction) {
        final var listOutput = this.listGenreUseCase.execute(SearchQuery.with(page, perPage, search, sort, direction))
                .map(GenreApiPresenter::present);

        return ResponseEntity.ok(listOutput);
    }

    @Override
    public ResponseEntity<GenreResponse> getById(final String anid) {
        final var output = this.getGenreByIdUseCase.execute(anid);

        return ResponseEntity.ok(GenreApiPresenter.present(output));
    }

    @Override
    public ResponseEntity<?> updateById(final String adId, final UpdateGenreRequest input) {
        final var aCommand = UpdateGenreCommand.with(
                adId,
                input.name(),
                input.active(),
                input.categories()
        );

        final var output = this.updateGenreUseCase.execute(aCommand);

        return ResponseEntity.ok(output);
    }

    @Override
    public ResponseEntity<?> deleteById(final String anId) {
        this.deleteGenreUseCase.execute(anId);

        return ResponseEntity.noContent().build();
    }

}

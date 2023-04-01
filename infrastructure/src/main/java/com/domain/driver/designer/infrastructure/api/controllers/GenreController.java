package com.domain.driver.designer.infrastructure.api.controllers;

import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.infrastructure.api.GenreAPI;
import com.domain.driver.designer.infrastructure.genre.models.CreateGenreRequest;
import com.domain.driver.designer.infrastructure.genre.models.GenreResponse;
import com.domain.driver.designer.infrastructure.genre.models.ListGenreResponse;
import com.domain.driver.designer.infrastructure.genre.models.UpdateGenreRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController implements GenreAPI {

    @Override
    public ResponseEntity<?> create(final CreateGenreRequest input) {
        return null;
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

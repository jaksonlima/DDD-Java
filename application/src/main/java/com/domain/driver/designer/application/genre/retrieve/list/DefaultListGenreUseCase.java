package com.domain.driver.designer.application.genre.retrieve.list;

import com.domain.driver.designer.domain.genre.GenreGateway;
import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListGenreUseCase extends ListGenreUseCase {

    private final GenreGateway genreGateway;

    public DefaultListGenreUseCase(final GenreGateway genreGateway) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Override
    public Pagination<ListGenreOutput> execute(final SearchQuery aQuery) {
        return genreGateway.findAll(aQuery)
                .map(ListGenreOutput::from);
    }

}

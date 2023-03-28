package com.domain.driver.designer.application.genre.retrieve.get;

import com.domain.driver.designer.domain.exceptions.NotFoundException;
import com.domain.driver.designer.domain.genre.Genre;
import com.domain.driver.designer.domain.genre.GenreGateway;
import com.domain.driver.designer.domain.genre.GenreID;

import java.util.Objects;

public class DefaultGetGenreByIdUseCase extends GetGenreByIdUseCase {

    private final GenreGateway genreGateway;

    public DefaultGetGenreByIdUseCase(final GenreGateway genreGateway) {
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Override
    public GetGenreOutput execute(final String anId) {
        final var aGenreId = GenreID.from(anId);
        return this.genreGateway
                .findById(aGenreId)
                .map(GetGenreOutput::from)
                .orElseThrow(() -> NotFoundException.with(Genre.class, aGenreId));
    }

}

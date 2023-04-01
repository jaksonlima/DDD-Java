package com.domain.driver.designer.infrastructure.genre.presenters;

import com.domain.driver.designer.application.genre.retrieve.get.GetGenreOutput;
import com.domain.driver.designer.application.genre.retrieve.list.ListGenreOutput;
import com.domain.driver.designer.infrastructure.genre.models.GenreResponse;
import com.domain.driver.designer.infrastructure.genre.models.ListGenreResponse;

public interface GenreApiPresenter {

    static GenreResponse present(final GetGenreOutput output) {
        return new GenreResponse(
                output.id(),
                output.name(),
                output.categories(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static ListGenreResponse present(final ListGenreOutput output) {
        return new ListGenreResponse(
                output.id(),
                output.name(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }

}

package com.domain.driver.designer.application.genre.retrieve.list;

import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.genre.Genre;

import java.time.Instant;
import java.util.List;

public record ListGenreOutput(
        String id,
        String name,
        boolean isActive,
        List<String> categories,
        Instant createdAt,
        Instant deletedAt
) {

    public static ListGenreOutput from(final Genre aGenre) {
        return new ListGenreOutput(
                aGenre.getId().getValue(),
                aGenre.getName(),
                aGenre.isActive(),
                aGenre.getCategories().stream()
                        .map(CategoryID::getValue)
                        .toList(),
                aGenre.getCreatedAt(),
                aGenre.getDeletedAt()
        );
    }

}

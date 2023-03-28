package com.domain.driver.designer.application.genre.retrieve.get;

import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.genre.Genre;

import java.time.Instant;
import java.util.List;

public record GetGenreOutput(
        String id,
        String name,
        boolean isActive,
        List<String> categories,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static GetGenreOutput from(final Genre aGenre) {
        return new GetGenreOutput(
                aGenre.getId().getValue(),
                aGenre.getName(),
                aGenre.isActive(),
                aGenre.getCategories().stream()
                        .map(CategoryID::getValue)
                        .toList(),
                aGenre.getCreatedAt(),
                aGenre.getUpdatedAt(),
                aGenre.getDeletedAt()
        );
    }

}

package com.domain.driver.designer.domain.genre;

import com.domain.driver.designer.domain.AggregateRoot;
import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Genre extends AggregateRoot<GenreID> {

    private String name;

    private boolean active;

    private List<CategoryID> categories;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    private Genre(final GenreID genreID,
                  final String name,
                  final boolean active,
                  final List<CategoryID> categories,
                  final Instant createdAt,
                  final Instant updatedAt,
                  final Instant deletedAt) {
        super(genreID);
        this.name = Objects.requireNonNull(name);
        this.active = active;
        this.categories = Objects.requireNonNull(categories);
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
        this.deletedAt = Objects.requireNonNull(deletedAt);
    }

    public static Genre newGenre(final String aName,
                                 final boolean isActive) {
        final var anId = GenreID.unique();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;

        return new Genre(anId, aName, isActive, new ArrayList<>(), now, now, deletedAt);
    }

    public static Genre with(final Genre aGenre) {
        return new Genre(
                aGenre.id,
                aGenre.name,
                aGenre.active,
                aGenre.categories,
                aGenre.createdAt,
                aGenre.updatedAt,
                aGenre.deletedAt
        );
    }

    public static Genre with(final GenreID anId,
                             final String aName,
                             final boolean isActive,
                             final List<CategoryID> categories,
                             final Instant aCreatedAt,
                             final Instant aUpdatedAt,
                             final Instant aDeletedAt) {
        return new Genre(anId, aName, isActive, categories, aCreatedAt, aUpdatedAt, aDeletedAt);
    }

    @Override
    protected void validate(final ValidationHandler handler) {
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public List<CategoryID> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

}

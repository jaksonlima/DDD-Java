package com.domain.driver.designer.domain.genre;

import com.domain.driver.designer.domain.AggregateRoot;
import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.exceptions.NotificationException;
import com.domain.driver.designer.domain.utils.InstantUtils;
import com.domain.driver.designer.domain.validation.ValidationHandler;
import com.domain.driver.designer.domain.validation.handler.Notification;

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
        this.name = name;
        this.active = active;
        this.categories = Objects.requireNonNull(categories);
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
        this.deletedAt = deletedAt;

        this.selfValidate();
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
        new GenreValidator(this, handler).validate();
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

    public void deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }
        this.active = false;
        this.updatedAt = InstantUtils.now();
    }

    public void activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
    }

    public Genre update(final String aName, final boolean isActive, final List<CategoryID> categories) {
        this.name = aName;
        this.categories = new ArrayList<>(categories != null ? categories : Collections.emptyList());
        this.updatedAt = InstantUtils.now();

        if (isActive) {
            activate();
        } else {
            deactivate();
        }

        this.selfValidate();

        return this;
    }

    public void addCategory(final CategoryID aCategoryId) {
        if (aCategoryId == null) return;

        final var newCategories = new ArrayList<>(getCategories() != null ? getCategories() : Collections.emptyList());
        newCategories.add(aCategoryId);

        this.categories = newCategories;
        this.updatedAt = InstantUtils.now();
    }

    public void removeCategory(final CategoryID aCategoryId) {
        if (aCategoryId == null) return;

        this.categories.remove(aCategoryId);
        this.updatedAt = InstantUtils.now();
    }

    public Genre addCategories(final List<CategoryID> aCategories) {
        if (aCategories == null || aCategories.isEmpty()) return this;

        aCategories.forEach(this::addCategory);

        return this;
    }

    private void selfValidate() {
        final var aNotification = Notification.create();
        validate(aNotification);

        if (aNotification.hasError()) {
            throw new NotificationException("Failed to create Aggregate Genre", aNotification);
        }
    }

}

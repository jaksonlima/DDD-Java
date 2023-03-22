package com.domain.driver.designer.domain.category;

import com.domain.driver.designer.domain.AggregateRoot;
import com.domain.driver.designer.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Category extends AggregateRoot<CategoryID> implements Cloneable {

    private String name;

    private String description;

    private boolean isActive;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;

    private Category(final CategoryID anId,
                     final String aName,
                     final String ADescription,
                     final boolean isActive,
                     final Instant aCreatedAt,
                     final Instant aUpdatedAt,
                     final Instant aDeletedAt) {
        super(anId);
        this.name = aName;
        this.description = ADescription;
        this.isActive = isActive;
        this.createdAt = Objects.requireNonNull(aCreatedAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(aUpdatedAt, "'updatedAt' should not be null");
        this.deletedAt = aDeletedAt;
    }

    public static Category with(final CategoryID aId,
                                final String name,
                                final String description,
                                final boolean active,
                                final Instant createdAt,
                                final Instant updatedAt,
                                final Instant deletedAt) {

        return new Category(aId,
                name,
                description,
                active,
                createdAt,
                updatedAt,
                deletedAt);
    }

    public static Category newCategory(final String name,
                                       final String description,
                                       final boolean isActive) {
        final var now = Instant.now();
        final var deleted = isActive ? null : now;

        return with(
                CategoryID.unique(),
                name,
                description,
                isActive,
                now,
                now,
                deleted
        );
    }

    public Category activate() {
        this.deletedAt = null;
        this.isActive = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.isActive = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category update(
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = aName;
        this.description = aDescription;
        this.updatedAt = Instant.now();
        return this;
    }

    @Override
    public void validate(final ValidationHandler aHandler) {
        new CategoryValidator(this, aHandler).validate();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
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

    @Override
    public Category clone() {
        try {
            return (Category) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}

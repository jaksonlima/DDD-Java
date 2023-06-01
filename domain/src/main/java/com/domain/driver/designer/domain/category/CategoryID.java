package com.domain.driver.designer.domain.category;

import com.domain.driver.designer.domain.Identifier;
import com.domain.driver.designer.domain.utils.IdUtils;

import java.util.Objects;
import java.util.UUID;

import static com.domain.driver.designer.domain.utils.IdUtils.uuid;

public class CategoryID extends Identifier {

    private final String value;

    private CategoryID(final String value) {
        Objects.requireNonNull(value, "value cannot be null");

        this.value = value.toLowerCase();
    }

    public static CategoryID unique() {
        return from(uuid());
    }

    public static CategoryID from(final String anId) {
        return new CategoryID(anId);
    }

    public static CategoryID from(final UUID andId) {
        return new CategoryID(andId.toString());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryID that = (CategoryID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}

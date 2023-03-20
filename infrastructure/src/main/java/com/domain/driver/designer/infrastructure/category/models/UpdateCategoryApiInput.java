package com.domain.driver.designer.infrastructure.category.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCategoryApiInput(
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("is_active") Boolean active
) {
}
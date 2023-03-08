package com.domain.driver.designer.application.category.create;

public record CreateCategoryCommand(
        String aName,
        String aDescription,
        boolean isActive
) {

    public static CreateCategoryCommand with(
            final String aName,
            final String aDescription,
            final boolean isActive
    ) {
        return new CreateCategoryCommand(aName, aDescription, isActive);
    }

}

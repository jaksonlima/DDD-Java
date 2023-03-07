package com.domain.driver.designer.domain.category;

import com.domain.driver.designer.domain.validation.Errors;
import com.domain.driver.designer.domain.validation.ValidationHandler;
import com.domain.driver.designer.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final int NAME_MAX_LENGTH = 255;
    private final int NAME_MIN_LENGTH = 3;

    private final Category category;

    public CategoryValidator(final Category anCategory,
                             final ValidationHandler anHandler) {
        super(anHandler);
        this.category = anCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();

        if (name == null) {
            this.validationHandler().append(new Errors("'name'' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Errors("'name'' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Errors("'name'' must be between 3 and 255 characters"));
        }
    }

}

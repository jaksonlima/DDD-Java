package com.domain.driver.designer.domain.genre;

import com.domain.driver.designer.domain.validation.Errors;
import com.domain.driver.designer.domain.validation.ValidationHandler;
import com.domain.driver.designer.domain.validation.Validator;

public class GenreValidator extends Validator {

    private final int NAME_MAX_LENGTH = 255;
    private final int NAME_MIN_LENGTH = 1;

    private final Genre genre;

    protected GenreValidator(final Genre aGenre, final ValidationHandler anHandler) {
        super(anHandler);
        this.genre = aGenre;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.genre.getName();

        if (name == null) {
            this.validationHandler().append(new Errors("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Errors("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Errors("'name' must be between 1 and 255 characters"));
        }
    }

}

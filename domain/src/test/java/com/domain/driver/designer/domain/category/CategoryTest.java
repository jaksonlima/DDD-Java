package com.domain.driver.designer.domain.category;

import com.domain.driver.designer.domain.exceptions.DomainException;
import com.domain.driver.designer.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        // given
        final var expectedName = "Filme";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = true;

        // when
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedisActive);

        // then
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedisActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAnInValidNullName_whenCallNewCategory_thenShouldReceiveError() {
        // given
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name'' should not be null";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = true;

        // when
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedisActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenAnInValidEmptyName_whenCallNewCategory_thenShouldReceiveError() {
        // given
        final String expectedName = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name'' should not be empty";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = true;

        // when
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedisActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenAnInValidNameLengthLessThan3_whenCallNewCategory_thenShouldReceiveError() {
        // given
        final String expectedName = "fi ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name'' must be between 3 and 255 characters";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = true;

        // when
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedisActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenAnInValidNameLengthLessMoreThan255_whenCallNewCategory_thenShouldReciveError() {
        // given
        final String expectedName = """
                Todavia, o fenômeno da Internet nos obriga à análise do retorno esperado a longo prazo. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que a mobilidade dos capitais internacionais não pode mais se dissociar das diretrizes de desenvolvimento.
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name'' must be between 3 and 255 characters";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = true;

        // when
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedisActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenAValidEmptyDescription_whenCallNewCategory_thenShouldReceiveError() {
        // given
        final var expectedName = "Filme";
        final var expectedDescription = " ";
        final var expectedisActive = true;

        // when
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedisActive);

        // then
        Assertions.assertDoesNotThrow(() -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedisActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidFalseIsActive_whenCallNewCategory_thenShouldReceiveError() {
        // given
        final var expectedName = "Filme";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = false;

        // when
        final var actualCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedisActive);

        // then
        Assertions.assertDoesNotThrow(() -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedisActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidActiveCategory_whenCallDeactivated_ThenReturnCategoryInactivated() {
        // given
        final var expectedName = "Filme";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = false;

        // when
        final var aCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                true);

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        // then
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.deactivate();

        Assertions.assertDoesNotThrow(() -> {
            aCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedisActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidInactiveCategory_whenCallActivate_ThenReturnCategoryActivated() {
        // given
        final var expectedName = "Filme";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = true;

        // when
        final var aCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                false);

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        // then
        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.activate();

        Assertions.assertDoesNotThrow(() -> {
            aCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedisActive, actualCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
        // given
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = true;

        //when
        final var aCategory = Category.newCategory(
                "Film",
                "A categoria",
                expectedisActive);

        //then
        Assertions.assertDoesNotThrow(() -> {
            aCategory.validate(new ThrowsValidationHandler());
        });

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedisActive);

        Assertions.assertDoesNotThrow(() -> {
            actualCategory.validate(new ThrowsValidationHandler());
        });

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedisActive, actualCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidCategory_whenCallUpdateToInactivate_thenReturnCategoryUpdated() {
        // given
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = false;

        //when
        final var aCategory = Category.newCategory(
                "Film",
                "A categoria",
                true);

        //then
        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedisActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
        // given
        final String expectedName = null;
        final var expectedDescription = "A categoria mas assistida";
        final var expectedisActive = true;

        //when
        final var aCategory = Category.newCategory(
                "Filmes",
                "A categoria",
                expectedisActive);

        //then
        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedisActive);

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

}
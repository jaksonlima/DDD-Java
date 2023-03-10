package com.domain.driver.designer.application.category.update;

import com.domain.driver.designer.domain.category.Category;
import com.domain.driver.designer.domain.category.CategoryGateway;
import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(categoryGateway);
    }

    @Test
    void givenAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId() {
        final var aCategory = Category.newCategory(
                "film",
                null,
                true
        );

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mas assitida";
        final var expectedIsActive = true;

        final var expectedId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedIsActive);

        when(categoryGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));

        when(categoryGateway.update(Mockito.any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, Mockito.times(1)).findById(Mockito.eq(expectedId));
        Mockito.verify(categoryGateway, Mockito.times(1))
                .update(Mockito.argThat(aUpdateCategory ->
                        Objects.equals(expectedName, aUpdateCategory.getName())
                                && Objects.equals(expectedDescription, aUpdateCategory.getDescription())
                                && Objects.equals(expectedIsActive, aUpdateCategory.isActive())
                                && Objects.equals(expectedId, aUpdateCategory.getId())
                                && Objects.equals(aCategory.getCreatedAt(), aUpdateCategory.getCreatedAt())
                                && aCategory.getUpdatedAt().isBefore(aUpdateCategory.getUpdatedAt())
                                && Objects.isNull(aCategory.getDeletedAt())));
    }

    @Test
    void givenAInvalidName_whenCallsUpdateCategory_thenShouldReturnDomainException() {
        final var aCategory = Category.newCategory(
                "film",
                null,
                true
        );

        final String expectedName = null;
        final var expectedDescription = "A categoria mas assitida";
        final var expectedIsActive = true;
        final var expectedId = aCategory.getId();
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = UpdateCategoryCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedIsActive);

        when(categoryGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));

        final var actualOutput = useCase.execute(aCommand).getLeft();

        Assertions.assertTrue(actualOutput.hasError());
        Assertions.assertTrue(actualOutput.firstError().isPresent());
        Assertions.assertEquals(expectedErrorCount, actualOutput.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualOutput.firstError().get().message());

        Mockito.verify(categoryGateway, Mockito.times(0)).update(Mockito.any());
    }

    @Test
    void givenAValidInactiveCommand_whenCallsUpdateCategory_shouldReturnInactiveCategoryId() {
        final var aCategory = Category.newCategory(
                "Filmes",
                null,
                true
        );

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mas assitida";
        final var expectedIsActive = false;
        final var expectedId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedIsActive);

        when(categoryGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));

        when(categoryGateway.update(Mockito.any()))
                .thenAnswer(returnsFirstArg());

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, Mockito.times(1)).findById(Mockito.eq(expectedId));
        Mockito.verify(categoryGateway, Mockito.times(1))
                .update(Mockito.argThat(aUpdateCategory ->
                        Objects.equals(expectedName, aUpdateCategory.getName())
                                && Objects.equals(expectedDescription, aUpdateCategory.getDescription())
                                && Objects.equals(expectedIsActive, aUpdateCategory.isActive())
                                && Objects.equals(expectedId, aUpdateCategory.getId())
                                && Objects.equals(aCategory.getCreatedAt(), aUpdateCategory.getCreatedAt())
                                && aCategory.getUpdatedAt().isBefore(aUpdateCategory.getUpdatedAt())
                                && Objects.nonNull(aUpdateCategory.getDeletedAt())));
    }

    @Test
    void givenAInvalidName_whenCallsGatewayThrowsRandomException_shouldReturnAException() {
        final var aCategory = Category.newCategory(
                "film",
                null,
                true
        );

        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mas assitida";
        final var expectedIsActive = true;
        final var expectedId = aCategory.getId();
        final var expectedErrorMessage = "Gateway error";

        final var aCommand = UpdateCategoryCommand.with(
                expectedId.getValue(),
                expectedName,
                expectedDescription,
                expectedIsActive);

        when(categoryGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));

        when(categoryGateway.update(Mockito.any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualOutput = useCase.execute(aCommand).getLeft();

        Assertions.assertTrue(actualOutput.hasError());
        Assertions.assertTrue(actualOutput.firstError().isPresent());
        Assertions.assertEquals(expectedErrorMessage, actualOutput.firstError().get().message());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .update(Mockito.argThat(aUpdateCategory ->
                        Objects.equals(expectedName, aUpdateCategory.getName())
                                && Objects.equals(expectedDescription, aUpdateCategory.getDescription())
                                && Objects.equals(expectedIsActive, aUpdateCategory.isActive())
                                && Objects.equals(expectedId, aUpdateCategory.getId())
                                && Objects.equals(aCategory.getCreatedAt(), aUpdateCategory.getCreatedAt())
                                && aCategory.getUpdatedAt().isBefore(aUpdateCategory.getUpdatedAt())
                                && Objects.isNull(aCategory.getDeletedAt())));
    }

    @Test
    void givenAValidWithInvalidIDCommand_whenCallsUpdateCategory_shouldNotFoundException() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mas assitida";
        final var expectedIsActive = false;
        final var expectedId = "123";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "Category with ID 123 was not found";

        final var aCommand = UpdateCategoryCommand.with(
                expectedId,
                expectedName,
                expectedDescription,
                expectedIsActive);

        when(categoryGateway.findById(Mockito.eq(CategoryID.from(expectedId))))
                .thenReturn(Optional.empty());

        final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .findById(Mockito.eq(CategoryID.from(expectedId)));

        Mockito.verify(categoryGateway, Mockito.times(0))
                .update(Mockito.any());
    }

}

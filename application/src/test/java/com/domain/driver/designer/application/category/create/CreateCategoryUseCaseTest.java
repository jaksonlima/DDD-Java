package com.domain.driver.designer.application.category.create;

import com.domain.driver.designer.domain.category.CategoryGateway;
import com.domain.driver.designer.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mas assitida";
        final var expectedIsActive = true;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any()))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        final var actualOutPut = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutPut);
        Assertions.assertNotNull(actualOutPut.id());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(aCategory ->
                        Objects.equals(expectedName, aCategory.getName())
                                && Objects.equals(expectedDescription, aCategory.getDescription())
                                && Objects.equals(expectedIsActive, aCategory.isActive())
                                && Objects.nonNull(aCategory.getCreatedAt())
                                && Objects.nonNull(aCategory.getUpdatedAt())
                                && Objects.isNull(aCategory.getDeletedAt())));
    }

    @Test
    void givenAInvalidName_whenCallsCreateCategory_thenshouldReturnDomainException() {
        final String expectedName = null;
        final var expectedDescription = "A categoria mas assitida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name'' should not be null";

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(categoryGateway, Mockito.times(0))
                .create(Mockito.any());
    }

    @Test
    void givenAValidCommandWithInactiveCategory_whenCallsCreateCategory_shouldReturnInactiveCategoryId() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mas assitida";
        final var expectedIsActive = false;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any()))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());

        final var actualOutPut = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutPut);
        Assertions.assertNotNull(actualOutPut.id());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(aCategory ->
                        Objects.equals(expectedName, aCategory.getName())
                                && Objects.equals(expectedDescription, aCategory.getDescription())
                                && Objects.equals(expectedIsActive, aCategory.isActive())
                                && Objects.nonNull(aCategory.getCreatedAt())
                                && Objects.nonNull(aCategory.getUpdatedAt())
                                && Objects.nonNull(aCategory.getDeletedAt())));
    }

    @Test
    void givenAValidCommand_whenGatewayThrowsRandomExeption_shouldReturnAException() {
        final String expectedName = "Filmes";
        final var expectedDescription = "A categoria mas assitida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway error";

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(aCategory ->
                        Objects.equals(expectedName, aCategory.getName())
                                && Objects.equals(expectedDescription, aCategory.getDescription())
                                && Objects.equals(expectedIsActive, aCategory.isActive())
                                && Objects.nonNull(aCategory.getCreatedAt())
                                && Objects.nonNull(aCategory.getUpdatedAt())
                                && Objects.isNull(aCategory.getDeletedAt())));
    }

}

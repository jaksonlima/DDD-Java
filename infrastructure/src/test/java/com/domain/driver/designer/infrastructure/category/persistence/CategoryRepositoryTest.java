package com.domain.driver.designer.infrastructure.category.persistence;


import com.domain.driver.designer.domain.category.Category;
import com.domain.driver.designer.infrastructure.MySQLGatewayTest;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

@MySQLGatewayTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void givenAInvalidName_whenCallsSave_shouldReturnError() {
        final var expectedPropertyName = "name";
        final var expectedMessage = "not-null property references a null or transient value : com.domain.driver.designer.infrastructure.category.persistence.CategoryJpaEntity.name";

        final var aCategory = Category.newCategory(
                "Filmes",
                "Categoria mas assistida",
                true
        );

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setName(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> categoryRepository.save(anEntity));
        final var actualCausa = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCausa.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCausa.getMessage());
    }

    @Test
    void givenAInvalidCreatedAt_whenCallsSave_shouldReturnError() {
        final var expectedPropertyName = "createdAt";
        final var expectedMessage = "not-null property references a null or transient value : com.domain.driver.designer.infrastructure.category.persistence.CategoryJpaEntity.createdAt";

        final var aCategory = Category.newCategory(
                "Filmes",
                "Categoria mas assistida",
                true
        );

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setCreatedAt(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> categoryRepository.save(anEntity));
        final var actualCausa = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCausa.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCausa.getMessage());
    }

    @Test
    void givenAInvalidUpdatedAt_whenCallsSave_shouldReturnError() {
        final var expectedPropertyName = "updatedAt";
        final var expectedMessage = "not-null property references a null or transient value : com.domain.driver.designer.infrastructure.category.persistence.CategoryJpaEntity.updatedAt";

        final var aCategory = Category.newCategory(
                "Filmes",
                "Categoria mas assistida",
                true
        );

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setUpdatedAt(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class, () -> categoryRepository.save(anEntity));
        final var actualCausa = Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCausa.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualCausa.getMessage());
    }

}

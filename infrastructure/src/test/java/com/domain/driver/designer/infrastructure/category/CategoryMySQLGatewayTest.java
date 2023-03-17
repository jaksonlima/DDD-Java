package com.domain.driver.designer.infrastructure.category;

import com.domain.driver.designer.domain.category.Category;
import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.category.CategorySearchQuery;
import com.domain.driver.designer.MySQLGatewayTest;
import com.domain.driver.designer.infrastructure.category.persistence.CategoryJpaEntity;
import com.domain.driver.designer.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MySQLGatewayTest
public class CategoryMySQLGatewayTest {

    @Autowired
    private CategoryMySQLGateway categoryGateway;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void givenAValidCategory_whenCallsCreate_shouldReturnANewCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "Categoria mas assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(
                expectedName,
                expectedDescription,
                expectedIsActive
        );

        Assertions.assertEquals(0, categoryRepository.count());

        final var actualCategory = categoryGateway.create(aCategory);

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.getUpdatedAt());
        Assertions.assertEquals(aCategory.getDeletedAt(), actualCategory.getDeletedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var actualEntityOption = categoryRepository.findById(aCategory.getId().getValue());

        Assertions.assertTrue(actualEntityOption.isPresent());

        final var actualEntity = actualEntityOption.get();

        Assertions.assertEquals(aCategory.getId().getValue(), actualEntity.getId());
        Assertions.assertEquals(expectedName, actualEntity.getName());
        Assertions.assertEquals(expectedDescription, actualEntity.getDescription());
        Assertions.assertEquals(expectedIsActive, actualEntity.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualEntity.getCreatedAt());
        Assertions.assertEquals(aCategory.getUpdatedAt(), actualEntity.getUpdatedAt());
        Assertions.assertEquals(aCategory.getDeletedAt(), actualEntity.getDeletedAt());
        Assertions.assertNull(actualEntity.getDeletedAt());
    }

    @Test
    void givenAValidCategory_whenCallsUpdate_shouldReturnACategoryUpdated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "Categoria mas assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory("Filmes", null, false);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        final var actualInvalidEntity = categoryRepository.findById(aCategory.getId().getValue()).get();

        Assertions.assertEquals("Filmes", actualInvalidEntity.getName());
        Assertions.assertNull(actualInvalidEntity.getDescription());
        Assertions.assertFalse(actualInvalidEntity.isActive());
        Assertions.assertEquals(1, categoryRepository.count());

        final var aUpdatedCategory = aCategory.clone().update(
                expectedName,
                expectedDescription,
                expectedIsActive);

        final var actualCategory = categoryGateway.update(aUpdatedCategory);

        Assertions.assertEquals(1, categoryRepository.count());

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertTrue(aCategory.getUpdatedAt().isBefore(actualCategory.getUpdatedAt()));
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var actualEntity = categoryRepository.findById(aCategory.getId().getValue()).get();

        Assertions.assertEquals(1, categoryRepository.count());

        Assertions.assertEquals(aCategory.getId().getValue(), actualEntity.getId());
        Assertions.assertEquals(expectedName, actualEntity.getName());
        Assertions.assertEquals(expectedDescription, actualEntity.getDescription());
        Assertions.assertEquals(expectedIsActive, actualEntity.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualEntity.getCreatedAt());
        Assertions.assertTrue(aCategory.getUpdatedAt().isBefore(actualEntity.getUpdatedAt()));
        Assertions.assertNull(actualEntity.getDeletedAt());
    }

    @Test
    void givenAPrePersistedCategoryAndValidCategoryId_whenTryDeleteIt_shouldDeleteCategory() {
        final var aCategory = Category.newCategory("Filme", null, true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        Assertions.assertEquals(1, categoryRepository.count());

        categoryGateway.deleteById(aCategory.getId());

        Assertions.assertEquals(0, categoryRepository.count());
    }

    @Test
    void givenInvalidCategoryId_whenTryDeleteIt_shouldDeleteCategory() {
        Assertions.assertEquals(0, categoryRepository.count());

        categoryGateway.deleteById(CategoryID.from("id_invalid"));

        Assertions.assertEquals(0, categoryRepository.count());
    }

    @Test
    void givenAPrePersistedCategoryAndValidCategoryId_whenCallsFindById_shouldReturnCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "Categoria mas assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        final var actualCategory = categoryGateway.findById(aCategory.getId()).get();

        Assertions.assertEquals(1, categoryRepository.count());

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        Assertions.assertEquals(aCategory.getUpdatedAt(), actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidCategoryCategoryIdNotStored_whenCallsFindById_shouldReturnEmpty() {
        Assertions.assertEquals(0, categoryRepository.count());

        final var actualCategory = categoryGateway.findById(CategoryID.from("empty"));

        Assertions.assertTrue(actualCategory.isEmpty());
    }

    @Test
    void givenPrePersistedCategories_whenCallsFindAll_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 3;

        final var filmes = Category.newCategory("Filmes", "null", true);
        final var series = Category.newCategory("Séries", "null", true);
        final var documentarios = Category.newCategory("Documentários", "null", true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(filmes),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentarios)
        ));

        Assertions.assertEquals(3, categoryRepository.count());

        final var aQuery = new CategorySearchQuery(0, 1, "", "name", "asc");

        final var actualResult = categoryGateway.findAll(aQuery);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(documentarios.getId(), actualResult.items().get(0).getId());
    }

    @Test
    void givenEmptyCategoriesTable_whenCallsFindAll_shouldReturnEmptyPage() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 0;

        Assertions.assertEquals(0, categoryRepository.count());

        final var aQuery = new CategorySearchQuery(0, 1, "", "name", "asc");

        final var actualResult = categoryGateway.findAll(aQuery);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(0, actualResult.items().size());
    }

    @Test
    void givenFollowPagination_whenCallsFindAllWithPage1_shouldReturnPaginated() {
        var expectedPage = 0;
        var expectedPerPage = 1;
        var expectedTotal = 3;

        final var filmes = Category.newCategory("Filmes", null, true);
        final var series = Category.newCategory("Séries", null, true);
        final var documentarios = Category.newCategory("Documentários", null, true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(filmes),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentarios)
        ));

        Assertions.assertEquals(3, categoryRepository.count());

        // Page 0
        var aQuery = new CategorySearchQuery(0, 1, "", "name", "asc");

        var actualResult = categoryGateway.findAll(aQuery);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(documentarios.getId(), actualResult.items().get(0).getId());

        // Page 1
        aQuery = new CategorySearchQuery(1, 1, "", "name", "asc");
        actualResult = categoryGateway.findAll(aQuery);
        expectedPage = 1;

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(filmes.getId(), actualResult.items().get(0).getId());

        // Page 2
        aQuery = new CategorySearchQuery(2, 1, "", "name", "asc");
        actualResult = categoryGateway.findAll(aQuery);
        expectedPage = 2;

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(expectedTotal, actualResult.total());
        Assertions.assertEquals(series.getId(), actualResult.items().get(0).getId());
    }

    @Test
    void givenPrePersistedCategoriesAndDocAsTerms_whenCallsFindAll_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 3;

        final var filmes = Category.newCategory("Filmes", null, true);
        final var series = Category.newCategory("Séries", null, true);
        final var documentarios = Category.newCategory("Documentários", null, true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(filmes),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentarios)
        ));

        Assertions.assertEquals(expectedTotal, categoryRepository.count());

        final var aQuery = new CategorySearchQuery(0, 1, "doc", "name", "asc");

        final var actualResult = categoryGateway.findAll(aQuery);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(1, actualResult.total());
        Assertions.assertEquals(documentarios.getId(), actualResult.items().get(0).getId());
    }

    @Test
    void givenPrePersistedCategoriesAndDocAsTerms_whenCallsFindAllAndTermsMathcsCategoryName_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 3;

        final var filmes = Category.newCategory("Filmes", "null", true);
        final var series = Category.newCategory("Séries", "null", true);
        final var documentarios = Category.newCategory("Documentários", "null", true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(filmes),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentarios)
        ));

        Assertions.assertEquals(expectedTotal, categoryRepository.count());

        final var aQuery = new CategorySearchQuery(0, 1, "Fil", "name", "asc");

        final var actualResult = categoryGateway.findAll(aQuery);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(1, actualResult.total());
        Assertions.assertEquals(filmes.getId(), actualResult.items().get(0).getId());
    }

    @Test
    void givenPrePersistedCategoriesMaisAssistidasAsTerms_whenCallsFindAllAndTermsMathcsCategoryDescription_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 3;

        final var filmes = Category.newCategory("Filmes", "A Categoria mas assistida", true);
        final var series = Category.newCategory("Séries", "Uma Categoria assitida", true);
        final var documentarios = Category.newCategory("Documentários", "A categoria menos assistida", true);

        Assertions.assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(filmes),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentarios)
        ));

        Assertions.assertEquals(expectedTotal, categoryRepository.count());

        final var aQuery = new CategorySearchQuery(0, 1, "MAS ASSISTIDA", "name", "asc");

        final var actualResult = categoryGateway.findAll(aQuery);

        Assertions.assertEquals(expectedPage, actualResult.currentPage());
        Assertions.assertEquals(expectedPerPage, actualResult.perPage());
        Assertions.assertEquals(1, actualResult.total());
        Assertions.assertEquals(filmes.getId(), actualResult.items().get(0).getId());
    }

}

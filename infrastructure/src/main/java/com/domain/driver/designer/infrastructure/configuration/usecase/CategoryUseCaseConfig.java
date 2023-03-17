package com.domain.driver.designer.infrastructure.configuration.usecase;

import com.domain.driver.designer.application.category.create.CreateCategoryUseCase;
import com.domain.driver.designer.application.category.create.DefaultCreateCategoryUseCase;
import com.domain.driver.designer.application.category.delete.DefaultDeleteCategoryUseCase;
import com.domain.driver.designer.application.category.delete.DeleteCategoryUseCase;
import com.domain.driver.designer.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import com.domain.driver.designer.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.domain.driver.designer.application.category.retrieve.list.DefaultListCategoryUseCase;
import com.domain.driver.designer.application.category.retrieve.list.ListCategoryUseCase;
import com.domain.driver.designer.application.category.update.DefaultUpdateCategoryUseCase;
import com.domain.driver.designer.application.category.update.UpdateCategoryUseCase;
import com.domain.driver.designer.infrastructure.category.CategoryMySQLGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryUseCaseConfig {

    private final CategoryMySQLGateway categoryGateway;

    public CategoryUseCaseConfig(final CategoryMySQLGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoryUseCase listCategoryUseCase() {
        return new DefaultListCategoryUseCase(categoryGateway);
    }

}

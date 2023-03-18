package com.domain.driver.designer.infrastructure.api.controllers;

import com.domain.driver.designer.application.category.create.CreateCategoryCommand;
import com.domain.driver.designer.application.category.create.CreateCategoryOutput;
import com.domain.driver.designer.application.category.create.CreateCategoryUseCase;
import com.domain.driver.designer.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.validation.handler.Notification;
import com.domain.driver.designer.infrastructure.api.CategoryAPI;
import com.domain.driver.designer.infrastructure.category.models.CategoryApiOutput;
import com.domain.driver.designer.infrastructure.category.models.CreateCategoryApiInput;
import com.domain.driver.designer.infrastructure.category.presenters.CategoryApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase,
                              final GetCategoryByIdUseCase getCategoryByIdUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryApiInput aInput) {
        final var aCommand = CreateCategoryCommand.with(
                aInput.name(),
                aInput.description(),
                aInput.active() != null ? aInput.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<?> listCategory(final String search,
                                      final int page,
                                      final int perPage,
                                      final String sort,
                                      final String direction) {
        return null;
    }

    @Override
    public ResponseEntity<CategoryApiOutput> getById(final String anId) {
        return ResponseEntity.ok()
                .body(CategoryApiPresenter.present(this.getCategoryByIdUseCase.execute(anId)));
    }

}

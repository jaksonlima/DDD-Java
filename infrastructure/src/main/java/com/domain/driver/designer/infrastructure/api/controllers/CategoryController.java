package com.domain.driver.designer.infrastructure.api.controllers;

import com.domain.driver.designer.application.category.create.CreateCategoryCommand;
import com.domain.driver.designer.application.category.create.CreateCategoryOutput;
import com.domain.driver.designer.application.category.create.CreateCategoryUseCase;
import com.domain.driver.designer.application.category.delete.DeleteCategoryUseCase;
import com.domain.driver.designer.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.domain.driver.designer.application.category.update.UpdateCategoryCommand;
import com.domain.driver.designer.application.category.update.UpdateCategoryOutput;
import com.domain.driver.designer.application.category.update.UpdateCategoryUseCase;
import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.validation.handler.Notification;
import com.domain.driver.designer.infrastructure.api.CategoryAPI;
import com.domain.driver.designer.infrastructure.category.models.CategoryApiOutput;
import com.domain.driver.designer.infrastructure.category.models.CreateCategoryApiInput;
import com.domain.driver.designer.infrastructure.category.models.UpdateCategoryApiInput;
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

    private final UpdateCategoryUseCase updateCategoryUseCase;

    private final DeleteCategoryUseCase deleteCategoryUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase,
                              final GetCategoryByIdUseCase getCategoryByIdUseCase,
                              final UpdateCategoryUseCase updateCategoryUseCase,
                              final DeleteCategoryUseCase deleteCategoryUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
        this.deleteCategoryUseCase = Objects.requireNonNull(deleteCategoryUseCase);
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

    @Override
    public ResponseEntity<?> updateById(final String id,
                                        final UpdateCategoryApiInput aInput) {
        final var aCommand = UpdateCategoryCommand.with(
                id,
                aInput.name(),
                aInput.description(),
                aInput.active() != null ? aInput.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateCategoryOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> deleteById(String anId) {
        this.deleteCategoryUseCase.execute(anId);

        return ResponseEntity.noContent().build();
    }

}

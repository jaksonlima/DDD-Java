package com.domain.driver.designer.application.category.retrieve.list;

import com.domain.driver.designer.domain.category.CategoryGateway;
import com.domain.driver.designer.domain.pagination.SearchQuery;
import com.domain.driver.designer.domain.pagination.Pagination;

import java.util.Objects;

public class DefaultListCategoryUseCase extends ListCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultListCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<ListCategoryOutput> execute(final SearchQuery aQuery) {
        return categoryGateway.findAll(aQuery)
                .map(ListCategoryOutput::from);
    }

}

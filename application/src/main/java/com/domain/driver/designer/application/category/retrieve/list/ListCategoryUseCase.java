package com.domain.driver.designer.application.category.retrieve.list;

import com.domain.driver.designer.application.UseCase;
import com.domain.driver.designer.application.category.retrieve.get.CategoryOutput;
import com.domain.driver.designer.domain.category.CategorySearchQuery;
import com.domain.driver.designer.domain.pagination.Pagination;

public abstract class ListCategoryUseCase extends UseCase<CategorySearchQuery, Pagination<ListCategoryOutput>> {
}

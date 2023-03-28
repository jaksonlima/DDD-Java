package com.domain.driver.designer.application.genre.retrieve.list;

import com.domain.driver.designer.application.UseCase;
import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.pagination.SearchQuery;

public abstract class ListGenreUseCase extends UseCase<SearchQuery, Pagination<ListGenreOutput>> {
}

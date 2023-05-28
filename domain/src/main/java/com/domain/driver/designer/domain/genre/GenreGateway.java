package com.domain.driver.designer.domain.genre;

import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.pagination.SearchQuery;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenreGateway {

    Genre create(Genre aGenre);

    void deleteById(GenreID anId);

    Optional<Genre> findById(GenreID anId);

    Genre update(Genre aGenre);

    Pagination<Genre> findAll(SearchQuery aQuery);

    List<GenreID> existsByIds(Collection<GenreID> ids);
}

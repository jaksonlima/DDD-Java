package com.domain.driver.designer.infrastructure.api.controllers;

import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.infrastructure.api.CategoryAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryAPI {

    @Override
    public ResponseEntity<?> createCategory() {
        return null;
    }

    @Override
    public Pagination<?> listCategory(final String search,
                                      final int page,
                                      final int perPage,
                                      final String sort,
                                      final String direction) {
        return null;
    }

}

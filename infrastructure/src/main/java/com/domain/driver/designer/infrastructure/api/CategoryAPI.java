package com.domain.driver.designer.infrastructure.api;

import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.infrastructure.category.models.CategoryApiOutput;
import com.domain.driver.designer.infrastructure.category.models.CreateCategoryApiInput;
import com.domain.driver.designer.infrastructure.category.models.UpdateCategoryApiInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("categories")
@Tag(name = "Categories")
public interface CategoryAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A Validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createCategory(@RequestBody CreateCategoryApiInput input);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all categories pagineted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<?> listCategory(
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String direction
    );

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a category by it's identifier")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Category was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            }
    )
    ResponseEntity<CategoryApiOutput> getById(@PathVariable(value = "id") String id);

    @PutMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a category by it's identifier")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Category update successfully"),
                    @ApiResponse(responseCode = "404", description = "Category was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            }
    )
    ResponseEntity<?> updateById(@PathVariable(value = "id") String id, @RequestBody UpdateCategoryApiInput input);

    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Delete a category by it's identifier")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Category was not found"),
                    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
            }
    )
    ResponseEntity<?> deleteById(@PathVariable(value = "id") String id);

}

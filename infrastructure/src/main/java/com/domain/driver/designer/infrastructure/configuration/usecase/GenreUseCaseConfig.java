package com.domain.driver.designer.infrastructure.configuration.usecase;

import com.domain.driver.designer.application.genre.create.CreateGenreUseCase;
import com.domain.driver.designer.application.genre.create.DefaultCreateGenreUseCase;
import com.domain.driver.designer.application.genre.delete.DefaultDeleteGenreUseCase;
import com.domain.driver.designer.application.genre.delete.DeleteGenreUseCase;
import com.domain.driver.designer.application.genre.retrieve.get.DefaultGetGenreByIdUseCase;
import com.domain.driver.designer.application.genre.retrieve.get.GetGenreByIdUseCase;
import com.domain.driver.designer.application.genre.retrieve.list.DefaultListGenreUseCase;
import com.domain.driver.designer.application.genre.retrieve.list.ListGenreUseCase;
import com.domain.driver.designer.application.genre.update.DefaultUpdateGenreUseCase;
import com.domain.driver.designer.application.genre.update.UpdateGenreUseCase;
import com.domain.driver.designer.domain.category.CategoryGateway;
import com.domain.driver.designer.domain.genre.GenreGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class GenreUseCaseConfig {

    private final CategoryGateway categoryGateway;
    private final GenreGateway genreGateway;

    public GenreUseCaseConfig(final CategoryGateway categoryGateway, final GenreGateway genreGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.genreGateway = Objects.requireNonNull(genreGateway);
    }

    @Bean
    public CreateGenreUseCase createGenreUseCase() {
        return new DefaultCreateGenreUseCase(categoryGateway, genreGateway);
    }

    @Bean
    public UpdateGenreUseCase updateGenreUseCase() {
        return new DefaultUpdateGenreUseCase(categoryGateway, genreGateway);
    }

    @Bean
    public DeleteGenreUseCase deleteGenreUseCase() {
        return new DefaultDeleteGenreUseCase(genreGateway);
    }

    @Bean
    public GetGenreByIdUseCase getGenreByIdUseCase() {
        return new DefaultGetGenreByIdUseCase(genreGateway);
    }

    @Bean
    public ListGenreUseCase getGenreListUseCase() {
        return new DefaultListGenreUseCase(genreGateway);
    }

}

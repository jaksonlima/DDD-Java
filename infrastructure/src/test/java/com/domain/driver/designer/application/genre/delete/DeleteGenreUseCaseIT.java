package com.domain.driver.designer.application.genre.delete;

import com.domain.driver.designer.IntegrationTest;
import com.domain.driver.designer.domain.genre.Genre;
import com.domain.driver.designer.domain.genre.GenreGateway;
import com.domain.driver.designer.domain.genre.GenreID;
import com.domain.driver.designer.infrastructure.genre.persistence.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class DeleteGenreUseCaseIT {

    @Autowired
    private DeleteGenreUseCase useCase;

    @Autowired
    private GenreGateway genreGateway;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void givenAValidGenreId_whenCallsDeleteGenre_shouldDeleteGenre() {
        // given
        final var aGenre = genreGateway.create(Genre.newGenre("Ação", true));

        final var expectedId = aGenre.getId();

        Assertions.assertEquals(1, genreRepository.count());

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        // when
        Assertions.assertEquals(0, genreRepository.count());
    }

    @Test
    public void givenAnInvalidGenreId_whenCallsDeleteGenre_shouldBeOk() {
        // given
        genreGateway.create(Genre.newGenre("Ação", true));

        final var expectedId = GenreID.from("123");

        Assertions.assertEquals(1, genreRepository.count());

        // when
        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        // when
        Assertions.assertEquals(1, genreRepository.count());
    }

}

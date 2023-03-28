package com.domain.driver.designer.infrastructure.genre;

import com.domain.driver.designer.MySQLGatewayTest;
import com.domain.driver.designer.infrastructure.category.CategoryMySQLGateway;
import com.domain.driver.designer.infrastructure.genre.persistence.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@MySQLGatewayTest
public class GenreMySQLGatewayTest {

    @Autowired
    private CategoryMySQLGateway categoryGateway;

    @Autowired
    private GenreMySQLGateway genreGateway;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void testDependences() {
        Objects.requireNonNull(categoryGateway);
        Objects.requireNonNull(genreGateway);
        Objects.requireNonNull(genreRepository);
    }

}

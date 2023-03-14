package com.domain.driver.designer.infrastructure.category;

import com.domain.driver.designer.application.category.create.CreateCategoryUseCase;
import com.domain.driver.designer.application.category.create.DefaultCreateCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryConfigurationBean {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(final CategoryMySQLGateway categoryMySQLGateway) {
        Objects.requireNonNull(categoryMySQLGateway);

        return new DefaultCreateCategoryUseCase(categoryMySQLGateway);
    }

}

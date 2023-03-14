package com.domain.driver.designer.infrastructure.category.persistence;

import com.domain.driver.designer.infrastructure.category.persistence.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryJpaEntity, String> {
}

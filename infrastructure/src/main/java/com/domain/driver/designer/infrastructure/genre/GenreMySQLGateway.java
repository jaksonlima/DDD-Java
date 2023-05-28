package com.domain.driver.designer.infrastructure.genre;

import com.domain.driver.designer.domain.genre.Genre;
import com.domain.driver.designer.domain.genre.GenreGateway;
import com.domain.driver.designer.domain.genre.GenreID;
import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.pagination.SearchQuery;
import com.domain.driver.designer.infrastructure.genre.persistence.GenreJpaEntity;
import com.domain.driver.designer.infrastructure.genre.persistence.GenreRepository;
import com.domain.driver.designer.infrastructure.utils.SpecificationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GenreMySQLGateway implements GenreGateway {

    private final GenreRepository genreRepository;

    public GenreMySQLGateway(final GenreRepository genreRepository) {
        this.genreRepository = Objects.requireNonNull(genreRepository);
    }

    @Override
    public Genre create(final Genre aGenre) {
        return save(aGenre);
    }

    @Override
    public void deleteById(final GenreID anId) {
        final var anIdValue = anId.getValue();

        if (this.genreRepository.existsById(anIdValue)) {
            this.genreRepository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<Genre> findById(final GenreID anId) {
        return this.genreRepository.findById(anId.getValue())
                .map(GenreJpaEntity::toAggregate);
    }

    @Override
    public Genre update(final Genre aGenre) {
        return save(aGenre);
    }

    @Override
    public Pagination<Genre> findAll(final SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        final var specification = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.genreRepository.findAll(specification, page);

        return Pagination.with(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(GenreJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<GenreID> existsByIds(final Collection<GenreID> ids) {
        return ids.stream()
                .filter(it -> this.genreRepository.existsById(it.getValue()))
                .toList();
    }

    private Genre save(final Genre aGenre) {
        return this.genreRepository.save(GenreJpaEntity.from(aGenre))
                .toAggregate();
    }

    private Specification<GenreJpaEntity> assembleSpecification(final String aTerms) {
        return SpecificationUtils.like("name", aTerms);
    }

}

package com.domain.driver.designer.infrastructure.video;

import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.video.*;
import com.domain.driver.designer.infrastructure.video.persistence.VideoJpaEntity;
import com.domain.driver.designer.infrastructure.video.persistence.VideoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

public class DefaultVideoGateway implements VideoGateway {

    private final VideoRepository videoRepository;

    public DefaultVideoGateway(final VideoRepository videoRepository) {
        this.videoRepository = Objects.requireNonNull(videoRepository);
    }

    @Override
    @Transactional
    public Video create(final Video aVideo) {
        return this.videoRepository.save(VideoJpaEntity.from(aVideo))
                .toAggregate();
    }

    @Override
    public void deleteById(final VideoID anId) {
        final var anVideoID = anId.getValue();
        if (this.videoRepository.existsById(anVideoID)) {
            this.videoRepository.deleteById(anVideoID);
        }
    }

    @Override
    public Optional<Video> findById(VideoID anId) {
        return Optional.empty();
    }

    @Override
    public Video update(Video aVideo) {
        return null;
    }

    @Override
    public Pagination<VideoPreview> findAll(VideoSearchQuery aQuery) {
        return null;
    }
}

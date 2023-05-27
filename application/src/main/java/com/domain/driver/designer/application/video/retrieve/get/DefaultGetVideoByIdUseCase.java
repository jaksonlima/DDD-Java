package com.domain.driver.designer.application.video.retrieve.get;

import com.domain.driver.designer.domain.exceptions.NotFoundException;
import com.domain.driver.designer.domain.video.Video;
import com.domain.driver.designer.domain.video.VideoGateway;
import com.domain.driver.designer.domain.video.VideoID;

import java.util.Objects;

public class DefaultGetVideoByIdUseCase extends GetVideoByIdUseCase {

    private final VideoGateway videoGateway;

    public DefaultGetVideoByIdUseCase(final VideoGateway videoGateway) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Override
    public VideoOutput execute(final String anIn) {
        final var aVideoId = VideoID.from(anIn);
        return this.videoGateway.findById(aVideoId)
                .map(VideoOutput::from)
                .orElseThrow(() -> NotFoundException.with(Video.class, aVideoId));
    }
}
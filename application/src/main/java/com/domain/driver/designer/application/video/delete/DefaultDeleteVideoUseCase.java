package com.domain.driver.designer.application.video.delete;

import com.domain.driver.designer.domain.video.VideoGateway;
import com.domain.driver.designer.domain.video.VideoID;

import java.util.Objects;

public class DefaultDeleteVideoUseCase extends DeleteVideoUseCase {

    private final VideoGateway videoGateway;

    public DefaultDeleteVideoUseCase(final VideoGateway videoGateway) {
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Override
    public void execute(final String anIn) {
        this.videoGateway.deleteById(VideoID.from(anIn));
    }

}

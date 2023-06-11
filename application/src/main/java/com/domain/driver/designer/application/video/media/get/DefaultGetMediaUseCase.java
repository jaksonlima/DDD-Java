package com.domain.driver.designer.application.video.media.get;

import com.domain.driver.designer.domain.exceptions.NotFoundException;
import com.domain.driver.designer.domain.validation.Errors;
import com.domain.driver.designer.domain.video.MediaResourceGateway;
import com.domain.driver.designer.domain.video.VideoID;
import com.domain.driver.designer.domain.video.VideoMediaType;

import java.util.Objects;

public class DefaultGetMediaUseCase extends GetMediaUseCase {

    private final MediaResourceGateway mediaResourceGateway;

    public DefaultGetMediaUseCase(final MediaResourceGateway mediaResourceGateway) {
        this.mediaResourceGateway = Objects.requireNonNull(mediaResourceGateway);
    }

    @Override
    public MediaOutput execute(final GetMediaCommand aCmd) {
        final var anId = VideoID.from(aCmd.videoId());
        final var aType = VideoMediaType.of(aCmd.mediaType())
                .orElseThrow(() -> typeNotFound(aCmd.mediaType()));

        final var aResource =
                this.mediaResourceGateway.getResource(anId, aType)
                        .orElseThrow(() -> notFound(aCmd.videoId(), aCmd.mediaType()));

        return MediaOutput.with(aResource);
    }

    private NotFoundException notFound(final String anId, final String aType) {
        return NotFoundException.with(new Errors("Resource %s not found for video %s".formatted(aType, anId)));
    }

    private NotFoundException typeNotFound(final String aType) {
        return NotFoundException.with(new Errors("Media type %s doesn't exists".formatted(aType)));
    }
}

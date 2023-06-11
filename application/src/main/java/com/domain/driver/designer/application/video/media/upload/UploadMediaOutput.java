package com.domain.driver.designer.application.video.media.upload;

import com.domain.driver.designer.domain.video.Video;
import com.domain.driver.designer.domain.video.VideoMediaType;

public record UploadMediaOutput(
        String videoId,
        VideoMediaType mediaType
) {

    public static UploadMediaOutput with(final Video aVideo, final VideoMediaType aType) {
        return new UploadMediaOutput(aVideo.getId().getValue(), aType);
    }
}
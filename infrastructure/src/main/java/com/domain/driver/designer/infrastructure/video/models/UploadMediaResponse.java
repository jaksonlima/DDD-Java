package com.domain.driver.designer.infrastructure.video.models;

import com.domain.driver.designer.domain.video.VideoMediaType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UploadMediaResponse(
        @JsonProperty("video_id") String videoId,
        @JsonProperty("media_type") VideoMediaType mediaType
) {
}
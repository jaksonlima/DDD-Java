package com.domain.driver.designer.application.video.retrieve.list;

import com.domain.driver.designer.application.UseCase;
import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.video.VideoSearchQuery;

public abstract class ListVideosUseCase
        extends UseCase<VideoSearchQuery, Pagination<VideoListOutput>> {
}
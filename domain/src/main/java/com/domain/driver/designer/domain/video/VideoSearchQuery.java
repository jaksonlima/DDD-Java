package com.domain.driver.designer.domain.video;

import com.domain.driver.designer.domain.castmember.CastMemberID;
import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.genre.GenreID;

import java.util.Set;

public record VideoSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction,
        Set<CastMemberID> castMembers,
        Set<CategoryID> categories,
        Set<GenreID> genres
) {
}
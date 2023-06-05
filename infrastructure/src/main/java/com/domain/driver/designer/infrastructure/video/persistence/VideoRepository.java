package com.domain.driver.designer.infrastructure.video.persistence;

import com.domain.driver.designer.domain.castmember.CastMemberID;
import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.genre.GenreID;
import com.domain.driver.designer.domain.video.VideoPreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface VideoRepository extends JpaRepository<VideoJpaEntity, String> {

    @Query("""
            select com.domain.driver.designer.domain.video.VideoPreview(
                v.id as id,
                v.title as title,
                v.description as description,
                v.createAt as createAt,
                v.updatedAt as updatedAt
            )
            from Video v
                join v.castMember members
                join v.categories categories
                join v.genres genres
            where
                ( :terms is null or UPPER(v.title) like :terms )
            and
                ( :castMembers is null or members.id.castMemberId in :castMembers )
            and
                ( :categories is null or categories.id.categoryId in :categories )
            and
                ( :genres is null or genres.id.genreId in :genres )
            """)
    Page<VideoPreview> findAll(
            @Param("terms") String terms,
            @Param("castMembers") Set<String> castMembers,
            @Param("categories") Set<String> categories,
            @Param("genres") Set<String> genres,
            PageRequest page
    );

}

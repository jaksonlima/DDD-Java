package com.domain.driver.designer.application.castmember.retrieve.list;

import com.domain.driver.designer.application.UseCase;
import com.domain.driver.designer.domain.pagination.Pagination;
import com.domain.driver.designer.domain.pagination.SearchQuery;

public sealed abstract class ListCastMembersUseCase
        extends UseCase<SearchQuery, Pagination<CastMemberListOutput>>
        permits DefaultListCastMembersUseCase {
}
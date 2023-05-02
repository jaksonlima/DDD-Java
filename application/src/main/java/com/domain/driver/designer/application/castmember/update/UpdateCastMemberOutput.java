package com.domain.driver.designer.application.castmember.update;

import com.domain.driver.designer.domain.castmember.CastMember;

public record UpdateCastMemberOutput(
        String id
) {

    public static UpdateCastMemberOutput with(final CastMember aCastMember) {
        return new UpdateCastMemberOutput(aCastMember.getId().getValue());
    }

}

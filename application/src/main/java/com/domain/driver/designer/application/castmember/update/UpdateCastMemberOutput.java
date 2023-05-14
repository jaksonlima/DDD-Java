package com.domain.driver.designer.application.castmember.update;

import com.domain.driver.designer.domain.castmember.CastMember;
import com.domain.driver.designer.domain.castmember.CastMemberID;

public record UpdateCastMemberOutput(
        String id
) {

    public static UpdateCastMemberOutput with(final CastMember aCastMember) {
        return new UpdateCastMemberOutput(aCastMember.getId().getValue());
    }

    public static UpdateCastMemberOutput from(final CastMemberID anId) {
        return new UpdateCastMemberOutput(anId.getValue());
    }

}

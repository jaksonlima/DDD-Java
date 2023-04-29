package com.domain.driver.designer.application.castmember.create;

import com.domain.driver.designer.domain.castmember.CastMember;
import com.domain.driver.designer.domain.castmember.CastMemberID;

public record CreateCastMemberOutput(
        String id
) {

    public static CreateCastMemberOutput from(final CastMemberID anId) {
        return new CreateCastMemberOutput(anId.getValue());
    }

    public static CreateCastMemberOutput from(final CastMember aMember) {
        return from(aMember.getId());
    }
}

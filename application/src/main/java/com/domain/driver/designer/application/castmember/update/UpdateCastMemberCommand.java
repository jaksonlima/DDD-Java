package com.domain.driver.designer.application.castmember.update;

import com.domain.driver.designer.domain.castmember.CastMemberType;

public record UpdateCastMemberCommand(
        String id,
        String name,
        CastMemberType type
) {

    public static UpdateCastMemberCommand with(
            final String aId,
            final String aName,
            final CastMemberType aType
    ) {
        return new UpdateCastMemberCommand(aId, aName, aType);
    }

}

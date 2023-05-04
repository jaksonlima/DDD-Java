package com.domain.driver.designer.application.castmember.delete;

import com.domain.driver.designer.domain.castmember.CastMemberGateway;
import com.domain.driver.designer.domain.castmember.CastMemberID;

import java.util.Objects;

public final class DefaultDeleteCastMemberUseCase extends DeleteCastMemberUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultDeleteCastMemberUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public void execute(final String anIn) {
        castMemberGateway.deleteById(CastMemberID.from(anIn));
    }

}

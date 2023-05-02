package com.domain.driver.designer.application.castmember.update;

import com.domain.driver.designer.domain.Identifier;
import com.domain.driver.designer.domain.castmember.CastMember;
import com.domain.driver.designer.domain.castmember.CastMemberGateway;
import com.domain.driver.designer.domain.castmember.CastMemberID;
import com.domain.driver.designer.domain.exceptions.NotFoundException;
import com.domain.driver.designer.domain.exceptions.NotificationException;
import com.domain.driver.designer.domain.validation.handler.Notification;

import java.util.Objects;
import java.util.function.Supplier;

public final class DefaultUpdateCastMemberUseCase extends UpdateCastMemberUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultUpdateCastMemberUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public UpdateCastMemberOutput execute(final UpdateCastMemberCommand aCommand) {
        final var anId = CastMemberID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aType = aCommand.type();

        final var aMember = this.castMemberGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.validate(() -> aMember.update(aName, aType));

        if (notification.hasError()) {
            notify(anId, notification);
        }

        return UpdateCastMemberOutput.with(this.castMemberGateway.update(aMember));
    }

    private static void notify(final Identifier anId, final Notification notification) {
        throw new NotificationException("Could not update Aggregate CastMember %s".formatted(anId), notification);
    }

    private static Supplier<NotFoundException> notFound(CastMemberID anId) {
        return () -> NotFoundException.with(CastMember.class, anId);
    }

}

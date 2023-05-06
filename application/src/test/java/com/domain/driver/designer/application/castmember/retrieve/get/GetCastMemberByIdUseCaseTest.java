package com.domain.driver.designer.application.castmember.retrieve.get;

import com.domain.driver.designer.application.Fixture;
import com.domain.driver.designer.application.UseCaseTest;
import com.domain.driver.designer.domain.castmember.CastMember;
import com.domain.driver.designer.domain.castmember.CastMemberGateway;
import com.domain.driver.designer.domain.castmember.CastMemberID;
import com.domain.driver.designer.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetCastMemberByIdUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetCastMemberByIdUseCase useCase;

    @Mock
    private CastMemberGateway castMemberGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(castMemberGateway);
    }

    @Test
    public void givenAValid_whenCallsGetCastMember_shouldReturnIt() {
        final var expectedName = Fixture.name();
        final var expectedType = Fixture.CastMembers.type();

        final var aMember = CastMember.newMember(expectedName, expectedType);

        final var expectedId = aMember.getId();

        when(castMemberGateway.findById(any()))
                .thenReturn(Optional.of(aMember));

        final var actualOutput = useCase.execute(expectedId.getValue());

        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(expectedId.getValue(), actualOutput.id());
        Assertions.assertEquals(expectedName, actualOutput.name());
        Assertions.assertEquals(expectedType, actualOutput.type());
        Assertions.assertEquals(aMember.getCreatedAt(), actualOutput.createdAt());
        Assertions.assertEquals(aMember.getUpdatedAt(), actualOutput.updatedAt());

        verify(castMemberGateway).findById(eq(expectedId));
    }

    @Test
    public void givenAInvalid_whenCallsGetCastMemberAndDoesNotExists_shouldReturnNotFoundException() {
        final var expectedId = CastMemberID.from("132");
        final var expectedErroMessage = "CastMember with ID 132 was not found";

        when(castMemberGateway.findById(any()))
                .thenReturn(Optional.empty());

        final var actualOutput = Assertions.assertThrows(NotFoundException.class, () -> useCase.execute(expectedId.getValue()));

        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(expectedErroMessage, actualOutput.getMessage());

        verify(castMemberGateway).findById(eq(expectedId));
    }

}

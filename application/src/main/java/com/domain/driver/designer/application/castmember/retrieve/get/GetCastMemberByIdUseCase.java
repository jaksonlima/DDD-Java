package com.domain.driver.designer.application.castmember.retrieve.get;

import com.domain.driver.designer.application.UseCase;

public sealed abstract class GetCastMemberByIdUseCase extends UseCase<String, CastMemberOutput> permits DefaultGetCastMemberByIdUseCase {
}

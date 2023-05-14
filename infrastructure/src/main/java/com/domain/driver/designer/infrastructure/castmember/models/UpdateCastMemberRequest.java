package com.domain.driver.designer.infrastructure.castmember.models;

import com.domain.driver.designer.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}

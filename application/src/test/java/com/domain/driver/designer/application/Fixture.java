package com.domain.driver.designer.application;

import com.domain.driver.designer.domain.castmember.CastMemberType;
import com.github.javafaker.Faker;

public final class Fixture {

    private static final Faker FAKER = Faker.instance();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static final class CastMembers {
        public static CastMemberType type() {
            return FAKER.options()
                    .option(CastMemberType.ACTOR, CastMemberType.DIRECTOR);
        }
    }
}

package com.patrix.timeregistration.business;

import org.springframework.context.annotation.Profile;

/**
 * Created by Peter on 2014-04-01.
 */
@Profile(value = { "test"})
public interface TestService {
    /**
     * Creates test data.
     */
    long createTestData();

    /**
     * Deletes test data.
     */
    long deleteTestData();
}

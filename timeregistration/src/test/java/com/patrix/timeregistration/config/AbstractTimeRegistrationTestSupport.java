package com.patrix.timeregistration.config;

import com.patrix.util.config.UtilConfiguration;
import org.springframework.test.context.ContextConfiguration;

import com.patrix.persistence.config.AbstractPersistenceTestSupport;

@ContextConfiguration(classes = {TimeRegistrationConfiguration.class, UtilConfiguration.class})
public abstract class AbstractTimeRegistrationTestSupport extends AbstractPersistenceTestSupport {

}

package com.patrix.persistence.config;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestJPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ActiveProfiles(profiles = { "test" })
public abstract class AbstractPersistenceTestSupport {

}

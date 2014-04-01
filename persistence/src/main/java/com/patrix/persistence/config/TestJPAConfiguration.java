package com.patrix.persistence.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.patrix.persistence.repository.CaseRepository;
import com.patrix.persistence.repository.FastLaneCaseReferenceRepository;
import com.patrix.persistence.repository.FastLaneCaseRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.patrix.persistence.repository.TimeLogRepository;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@EnableJpaRepositories(basePackages = "com.patrix.persistence.repository",
        includeFilters = @Filter(value = {
                FastLaneCaseRepository.class,
                FastLaneCaseReferenceRepository.class,
                TimeLogRepository.class,
                CaseRepository.class
        }, type = FilterType.ASSIGNABLE_TYPE))
@Import(RepositoryRestMvcConfiguration.class)
@EnableTransactionManagement
public class TestJPAConfiguration extends AbstractJPAConfiguration {

    @Override
    public DataSource dataSource() throws SQLException {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }
}

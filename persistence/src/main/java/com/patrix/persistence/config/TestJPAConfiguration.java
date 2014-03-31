package com.patrix.persistence.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.patrix.persistence.repository.CaseReferenceRepository;
import com.patrix.persistence.repository.CaseRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.patrix.persistence.repository.TimeLogRepository;

@Configuration
@EnableJpaRepositories(basePackages = "com.patrix.persistence.repository",
includeFilters = @ComponentScan.Filter(value = {TimeLogRepository.class, CaseRepository.class, CaseReferenceRepository.class}, type = FilterType.ASSIGNABLE_TYPE))
@Import(RepositoryRestMvcConfiguration.class)
@EnableTransactionManagement
public class TestJPAConfiguration extends AbstractJPAConfiguration {

	@Override
	public DataSource dataSource() throws SQLException {
		final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.H2).build();
	}
}

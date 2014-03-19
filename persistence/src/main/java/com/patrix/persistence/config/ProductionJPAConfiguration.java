package com.patrix.persistence.config;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.patrix.persistence.repository.TimeLogRepository;

@Configuration
@EnableJpaRepositories(basePackages = "com.patrix.persistence.repository",
includeFilters = @ComponentScan.Filter(value = {TimeLogRepository.class}, type = FilterType.ASSIGNABLE_TYPE))
@EnableTransactionManagement
public class ProductionJPAConfiguration extends AbstractJPAConfiguration {

	@Override
	public DataSource dataSource() throws SQLException {
		final JndiTemplate jndi = new JndiTemplate();
		try {
			return (DataSource) jndi.lookup("java:jboss/datasources/PatriciaDB");
		} catch (NamingException e) {
			throw new RuntimeException("Unable to lookup JNDI data source", e);
		}
	}
}

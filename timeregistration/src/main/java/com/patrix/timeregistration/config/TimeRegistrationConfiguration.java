package com.patrix.timeregistration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.patrix.timeregistration.business.impl", "com.patrix.timeregistration.api.rest"  })
@PropertySources(value = { 
		@PropertySource(value = "classpath:timeregistration.properties"),
		@PropertySource(value = "file://${app.config.dir:${user.home}}/.timeregistration.properties", ignoreResourceNotFound = true)
})
public class TimeRegistrationConfiguration {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}

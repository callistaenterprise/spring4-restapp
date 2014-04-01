package com.patrix.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by peter on 2014-03-17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.patrix.web.api" })
@PropertySource(value = {"classpath:sample-webapp.properties"}, ignoreResourceNotFound = true)
public class WebConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

package com.patrix.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by peter on 2014-03-17.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.patrix.web.api" })
public class WebConfiguration {
}

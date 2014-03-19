package com.patrix.util.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by peter on 2014-03-18.
 */
@Configuration
@ComponentScan(basePackages = { "com.patrix.util.impl" })
public class UtilConfiguration {
}

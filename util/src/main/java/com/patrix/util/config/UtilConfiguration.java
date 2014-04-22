package com.patrix.util.config;

import com.patrix.util.UtilService;
import com.patrix.util.impl.TestUserServiceImpl;
import com.patrix.util.impl.UtilServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by peter on 2014-03-18.
 */
@Configuration
public class UtilConfiguration {

    @Bean
    public UtilService utilService() {
        return new UtilServiceImpl();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new TestUserServiceImpl();
    }
}

package com.patrix.web.config;

import com.patrix.persistence.config.ProductionJPAConfiguration;
import com.patrix.persistence.config.TestJPAConfiguration;

import com.patrix.timeregistration.config.TimeRegistrationConfiguration;
import com.patrix.util.config.UtilConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.*;

/**
 * Created by peter on 2014-03-17.
 */
@Slf4j
public class SampleWebApplicationInitializer implements WebApplicationInitializer {

    private static List<String> activeProfiles;

    protected static boolean isProfileActive(final String profile) {
        return activeProfiles.contains(profile);
    }

    protected Class<?>[] getRootConfigurationClasses(boolean test) {
        if (test) {
            log.info("Load test configuration");
            return new Class<?>[] { SecurityConfiguration.class, TestJPAConfiguration.class, UtilConfiguration.class};
        } else {
            log.info("Load production configuration");
            return new Class<?>[] { SecurityConfiguration.class, ProductionJPAConfiguration.class, UtilConfiguration.class };
        }
    }

    protected WebApplicationContext createRootContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

        activeProfiles = Arrays.asList(rootContext.getEnvironment().getActiveProfiles());

        rootContext.register(getRootConfigurationClasses(isProfileActive("test")));
        rootContext.refresh();

        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        return rootContext;
    }

    protected void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(WebConfiguration.class, TimeRegistrationConfiguration.class);
        mvcContext.setParent(rootContext);

        final ServletRegistration.Dynamic appServlet = servletContext.addServlet(
                "rest-api", new DispatcherServlet(mvcContext));
        appServlet.setLoadOnStartup(1);

        final Set<String> mappingConflicts = appServlet.addMapping("/");

        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                log.error("Mapping conflict: " + s);
            }
            throw new IllegalStateException("'rest-api' cannot be mapped to '/'");
        }
    }

    protected void configureSpringSecurity(ServletContext servletContext, WebApplicationContext rootContext) {
        FilterRegistration.Dynamic springSecurity = servletContext.addFilter("springSecurityFilterChain",
                new DelegatingFilterProxy("springSecurityFilterChain", rootContext));
        springSecurity.addMappingForUrlPatterns(null, true, "/*");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("WebApplication Starting up using: {}", getClass().getName());
        WebApplicationContext rootContext = createRootContext(servletContext);
        configureSpringMvc(servletContext, rootContext);
        configureSpringSecurity(servletContext, rootContext);
    }
}

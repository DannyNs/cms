package com.dannyns.cms.backend.configurations;

import com.dannyns.cms.backend.authentication.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ws.rs.ApplicationPath;
import java.util.Optional;

@Configuration
@ApplicationPath("/rest")
public class JerseyConfiguration extends ResourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyConfiguration.class);
    private static final String BASE_PACKAGE = "com.dannyns.cms.backend.business.services";

    public JerseyConfiguration() {
        getResourcesScanner()
                .findCandidateComponents(BASE_PACKAGE)
                .stream()
                .map(BeanDefinition::getBeanClassName)
                .map(this::getClassFromName)
                .forEach(optional -> optional.ifPresent(this::register));

        register(CustomUserDetailsService.class);
    }

    private Optional<Class<?>> getClassFromName(String name) {
        Optional<Class<?>> result = Optional.empty();

        try {
            return  Optional.of(Class.forName(name));
        } catch (ClassNotFoundException e) {
            LOGGER.error("Unable to load class", e);
        }

        return result;
    }

    private ClassPathScanningCandidateComponentProvider getResourcesScanner() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Service.class));
        return scanner;
    }

    @Component
    public class HibernateAwareObjectMapper extends ObjectMapper {
        public HibernateAwareObjectMapper() {
            registerModule(new Hibernate4Module());
        }
    }
}

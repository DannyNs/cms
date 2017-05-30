package com.dannyns.cms.backend.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class ThymeleafConfiguration {

    @Bean
    public SpringSecurityDialect springSecurityDialtect() {
        return new SpringSecurityDialect();
    }
}
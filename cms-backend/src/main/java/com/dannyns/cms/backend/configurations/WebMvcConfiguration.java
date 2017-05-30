package com.dannyns.cms.backend.configurations;

import com.dannyns.cms.backend.interceptors.ThymeleafLayoutInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    private static final String UPLOAD_PATH = String.join("", System.getProperty("user.dir"), "/upload");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new ThymeleafLayoutInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);

        registry.addViewController("/admin").setViewName("redirect:/admin/");
        registry.addViewController("/admin/").setViewName("forward:/admin/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        File uploadDirectory = new File(UPLOAD_PATH);

        if (!uploadDirectory.exists()) uploadDirectory.mkdir();

        registry.addResourceHandler("/upload/**")
                .addResourceLocations(String.join("","file:/", UPLOAD_PATH));
    }
}

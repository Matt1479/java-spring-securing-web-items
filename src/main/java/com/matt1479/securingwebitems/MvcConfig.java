package com.matt1479.securingwebitems;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// MvcConfig: maps simple/static views

@Configuration 
public class MvcConfig implements WebMvcConfigurer {
    
    @Override 
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        // Handled by ItemWebController since it needs to load the items dynamically
        // registry.addViewController("/items").setViewName("items");
        registry.addViewController("/login").setViewName("login");
    }

}

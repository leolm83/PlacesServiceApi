package com.leolm.placeservice.config;

import com.github.slugify.Slugify;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    Slugify slugfy(){
        return Slugify.builder().build();
    }
}

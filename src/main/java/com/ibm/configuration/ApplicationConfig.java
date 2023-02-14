package com.ibm.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private ModelMapper modelMapper;

    public ApplicationConfig() {
        modelMapper = new ModelMapper();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return this.modelMapper;
    }
}

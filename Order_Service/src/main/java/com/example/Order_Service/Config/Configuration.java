package com.example.Order_Service.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

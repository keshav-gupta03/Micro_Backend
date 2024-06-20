package com.example.userservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;



@Configuration
public class AppConfiguration {
	
	
	
//	@Bean
//	public Docket SwaggerApi() { 
//	       return new Docket(DocumentationType.SWAGGER_2) 
//	               .select() 
//	               .apis(RequestHandlerSelectors.basePackage("com.example.User_Service.Controller")) 
//	               .paths(PathSelectors.any()) 
//	               .build(); 
//	   }
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("User Service API")
                .version("1.0")
                .description("User Service API Documentation"));
    }
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	

}

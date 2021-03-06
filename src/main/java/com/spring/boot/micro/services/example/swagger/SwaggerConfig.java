package com.spring.boot.micro.services.example.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class SwaggerConfig  {
	
	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;
	
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Example MicroServices API",
                "Version 0.1",
                "Terms",
                "xyzz@abcc.com",
                "XYZ Inc","http://www.xyz-abc.com"
        );
        return apiInfo;
    }
	
}

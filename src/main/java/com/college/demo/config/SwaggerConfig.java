package com.college.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
         // .apis(RequestHandlerSelectors.any()) /// this will expose all the REST end points (that includes spring boot in-built REST end points)
          .apis(RequestHandlerSelectors.basePackage("com.college.demo")) 
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());                                           
    }
	
	 private ApiInfo apiInfo() {
	        
		 return new ApiInfoBuilder()
            .title("Student Management API Swagger Documentation")
            .description("This API can be used to add, update, delete and fetch students from library")
            .version("1.0.0")
            .license("Apache 2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
            .contact(new Contact("Student API", "https://github.com/kumaran-is", "kumaran.isk@gmail.com"))
            .build();
	   }

}


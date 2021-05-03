package com.javierlobo.dummy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

@Bean
public Docket api() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.javierlobo.dummy.controllers"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(metaInfo());

    return docket;
   }

	// @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	
//	    registry.addResourceHandler("/webjars/**")
//	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
    private ApiInfo metaInfo() {

    	Contact contacto = new Contact(
    			"Javier Lobo", 
    			"www.linkedin.com/in/javixulobo", 
    			"franciscojavier.lobo@gmail.com");
    	
    	ApiInfo apiInfo = new ApiInfo(
                "Spring Boot Swagger Dummy API",
                "Api de ejemplo, administra una lista de elementos mediante un CRUD básico.",
                "1.0",
                "Terms of Service",
                contacto.getName() + " (Email: " + contacto.getEmail() + ") (Url: " + contacto.getUrl() +")",
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );
        return apiInfo;
    }
}
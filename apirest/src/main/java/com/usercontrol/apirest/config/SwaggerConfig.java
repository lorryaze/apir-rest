package com.usercontrol.apirest.config;

import java.util.List;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//Docker é uma classe externa da aplicação o bean faz o import pro nosso projeto
	@Bean
    public Docket userApi() {
         Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.usercontrol.apirest"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
         return docket;
    }
	
	Contact contact = new Contact( 
			"Lorrany Azevedo",
            "https://github.com/lorryaze", 
            "lorrany90@gmail.com"
			);
	
	List<VendorExtension> vendorExtensions = new ArrayList<>();
    
	private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo("User Control API Rest", 
        		"Api para cadastro de clientes", 
        		"1.0", 
        		"Terms of Service", 
        		contact, 
        		"Apache 2.0", 
        		"http://www.apache.org/licenses/LICENSE-2.0",
        		vendorExtensions
        		);

        return apiInfo;
    }

}

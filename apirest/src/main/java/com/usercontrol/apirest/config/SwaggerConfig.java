package com.usercontrol.apirest.config;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

//import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
	private static final String CLIENT_ID = "admin";

	private static final String CLIENT_SECRET = "123";

	private static final String AUTH_SERVER = null;

	//Docket é uma classe externa da aplicação o bean faz o import pro nosso projeto
	@Bean
    public Docket userApi() {
         Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.usercontrol.apirest"))
                .paths(PathSelectors.ant("/api/*"))
                .build()
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()));
                //.apiInfo(metaInfo());
         return docket;
    }
	//start
	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
	        .clientId(CLIENT_ID)
	        .clientSecret(CLIENT_SECRET)
	        .scopeSeparator(" ")
	        .useBasicAuthenticationWithAccessCodeGrant(true)
	        .build();
	}
	private SecurityScheme securityScheme() {
	    GrantType grantType = new AuthorizationCodeGrantBuilder()
	        .tokenEndpoint(new TokenEndpoint("8080" + "/token", "oauthtoken"))
	        .tokenRequestEndpoint(new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_SECRET))
	        .build();
	 
	    SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
	        .grantTypes(Arrays.asList(grantType))
	        .scopes(Arrays.asList(scopes()))
	        .build();
	    return oauth;
	}
	private AuthorizationScope[] scopes() {
	    AuthorizationScope[] scopes = { 
	      new AuthorizationScope("read", "for read operations"), 
	      new AuthorizationScope("write", "for write operations"), 
	      new AuthorizationScope("foo", "Access foo API") };
	    return scopes;
	}
	private SecurityContext securityContext() {
	    return SecurityContext.builder()
	      .securityReferences(
	        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
	      .forPaths(PathSelectors.regex("/api.*"))
	      .build();
	}
	
	Contact contact = new Contact( "Lorrany Azevedo", "https://github.com/lorryaze", "lorrany90@gmail.com");
	
	List<VendorExtension> vendorExtensions = new ArrayList<>();
    
	private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo("User Control API Rest", "Api para cadastro de clientes", "1.0", "Terms of Service", contact, 
        		"Apache 2.0", 
        		"http://www.apache.org/licenses/LICENSE-2.0",
        		vendorExtensions
        		);

        return apiInfo;
    }

}

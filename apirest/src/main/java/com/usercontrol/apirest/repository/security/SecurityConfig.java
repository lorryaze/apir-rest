package com.usercontrol.apirest.repository.security;

import javax.validation.OverridesAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
//import com.usercontrol.apirest.models.User;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		//.httpBasic()
		.formLogin()
		.and().csrf().disable();
		
	}
	
	//O noop formata a senha no padrão storage format e evita erros como 401 isso 
	//serve para releases mais antigas do spring security
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/*@Bean
	public UserDetailsService userDetailsService() {
		
		@SuppressWarnings("deprecation")
		User.UserBuilder users  = User.withDefaultPasswordEncoder();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(users.username("user").password("123").roles("USER").build());
		manager.createUser(users.username("admin").password("123").roles("USER", "ADMIN").build());
		return manager;
		
		/*inMemoryAuthentication()
		.withUser("user").password("{noop}user123").roles("USER")
		.and().withUser("admin").password("{noop}admin123").roles("ADMIN");
	}*/

}

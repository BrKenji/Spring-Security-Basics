package com.security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // TODO: later on
		.authorizeHttpRequests()
		.antMatchers("/", "index", "/css/*", "/js/*")// whitelist certain urls
		.permitAll()
		.antMatchers("/api/**").hasRole(ApplicationUserRole.EMPLOYEE.name()) // only accesable by admins
		.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermission.EMPLOYEE_WRITE.getPermission())
		.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermission.EMPLOYEE_WRITE.getPermission())
		.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermission.EMPLOYEE_WRITE.getPermission())
		.antMatchers("/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.SUB_ADMIN.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}

	@Override
	@Bean
	// Retreat users from database
	protected UserDetailsService userDetailsService() {
		UserDetails amyUser = User.builder()
				.username("amySosa")
				.password(passwordEncoder.encode("4321"))
				//.roles(ApplicationUserRole.ADMIN.name())
				.authorities(ApplicationUserRole.ADMIN.getGrantedAuthority())
				.build();
		
		UserDetails jonaUser = User.builder()
				.username("jonaS")
				.password(passwordEncoder.encode("1234"))
				//.roles(ApplicationUserRole.EMPLOYEE.name())
				.authorities(ApplicationUserRole.EMPLOYEE.getGrantedAuthority())
				.build();
		
		UserDetails dinaUser = User.builder()
				.username("dina")
				.password(passwordEncoder.encode("birds"))
				//.roles(ApplicationUserRole.SUB_ADMIN.name())
				.authorities(ApplicationUserRole.SUB_ADMIN.getGrantedAuthority())
				.build();
		
		return new InMemoryUserDetailsManager(amyUser, jonaUser, dinaUser);
	}	
	
}

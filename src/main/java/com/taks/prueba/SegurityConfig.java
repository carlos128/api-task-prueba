package com.taks.prueba;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.google.common.collect.ImmutableList;
import com.taks.prueba.segurity.JWTAuthenticationFilter;
import com.taks.prueba.segurity.JWTAuthorizationFilter;
import static com.taks.prueba.utilitis.Constants.SIGNUP;


import static com.taks.prueba.utilitis.Constants.LOGIN;

@Configuration
@EnableWebSecurity
public class SegurityConfig  extends  WebSecurityConfigurerAdapter {
	
	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public SegurityConfig(UserDetailsService userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		 this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*@Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }*/

	
	@Override
	protected  void  configure( HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.cors().and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST,SIGNUP).permitAll()
			.antMatchers(HttpMethod.POST,LOGIN).permitAll()
			.anyRequest().authenticated().and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()));
		http.requiresChannel()
	      .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
	      .requiresSecure();
	}
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	 @Override 
	 public void configure(WebSecurity web) throws Exception { 
	     web.ignoring().antMatchers("/","/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**"); 
	 } 

	 @Bean
	    public CorsFilter corsFilter() {
	        final CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(ImmutableList.of("*"));
	        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
	        configuration.setAllowCredentials(true);
	        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
	        configuration.setExposedHeaders(ImmutableList.of("Authorization"));
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return new CorsFilter(source);
	    }
}


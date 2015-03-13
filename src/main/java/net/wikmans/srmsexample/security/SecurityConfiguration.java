package net.wikmans.srmsexample.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Daniel on 2015-03-13.
 * Simple security example.
 */
@SuppressWarnings({"UnusedDeclaration", "SpringFacetCodeInspection"})
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().//
                withUser("dan").password("test").roles("USER").and().//
                withUser("danne").password("test").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().and().authorizeRequests().//
                antMatchers(HttpMethod.POST, "/persons").hasRole("ADMIN").//
                antMatchers(HttpMethod.PUT, "/persons/**").hasRole("ADMIN").//
                antMatchers(HttpMethod.PATCH, "/persons/**").hasRole("ADMIN").and().//
                csrf().disable();
    }
}

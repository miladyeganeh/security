package com.security.customauthmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    private final CustomAuthenticationProvider authenticationProvider;
//
//    CustomSecurityConfiguration(CustomAuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.httpBasic();
       http.authorizeRequests().anyRequest().authenticated();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(this.authenticationProvider);
//    }
}
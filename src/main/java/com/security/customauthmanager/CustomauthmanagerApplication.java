package com.security.customauthmanager;

import com.security.customauthmanager.config.CustomUserDetails;
import com.security.customauthmanager.service.CustomUserDetailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class CustomauthmanagerApplication {

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    CustomUserDetailService customUserDetailService () {
        Collection<UserDetails> user = Arrays.asList(
            new CustomUserDetails("milad", "password", true, "USER"),
            new CustomUserDetails("masod", "password", true, "USER", "ADMIN")
        );
        return new CustomUserDetailService(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomauthmanagerApplication.class, args);
    }
}

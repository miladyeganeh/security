package com.security.customauthmanager;

import com.security.customauthmanager.config.CustomUserDetails;
import com.security.customauthmanager.service.CustomUserDetailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
public class CustomauthmanagerApplication {

//    @Bean
    PasswordEncoder oldPasswordEncoder(){
        String md5 = "MD5";
        return new DelegatingPasswordEncoder(md5, Collections.singletonMap(md5, new MessageDigestPasswordEncoder(md5)));
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CustomUserDetailService customUserDetailService () {
        Collection<UserDetails> user = Arrays.asList(
            new CustomUserDetails("milad", oldPasswordEncoder().encode("password"), true, "USER"),
            new CustomUserDetails("masod", oldPasswordEncoder().encode("password"), true, "USER", "ADMIN")
        );
        return new CustomUserDetailService(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomauthmanagerApplication.class, args);
    }
}

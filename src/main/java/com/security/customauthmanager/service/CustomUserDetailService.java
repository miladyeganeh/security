package com.security.customauthmanager.service;

import com.security.customauthmanager.config.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CustomUserDetailService implements UserDetailsService, UserDetailsPasswordService {

    private final Map<String, UserDetails> users = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    public CustomUserDetailService(Collection<UserDetails> seedUsers) {
        seedUsers.forEach(user -> this.users.put(user.getUsername(), user));
        this.users.forEach((k, v) -> System.out.println(k + ": " + v.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (this.users.containsKey(userName)){
            return this.users.get(userName);
        }

        throw new UsernameNotFoundException(String.format("Could Not found %s:", userName));
    }

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        System.out.println("Prompt to update Password for user: " + userDetails.getUsername() + "to " + newPassword);
        this.users.put(userDetails.getUsername(), new CustomUserDetails(
                userDetails.getUsername(),
                newPassword,
                userDetails.isEnabled(),
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new)
        ));
        return this.loadUserByUsername(userDetails.getUsername());
    }
}

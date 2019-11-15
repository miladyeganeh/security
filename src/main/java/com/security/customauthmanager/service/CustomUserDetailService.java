package com.security.customauthmanager.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomUserDetailService implements UserDetailsService {

    private final Map<String, UserDetails> users = new ConcurrentHashMap<>();

    public CustomUserDetailService(Collection<UserDetails> seedUsers) {
        seedUsers.forEach(user -> this.users.put(user.getUsername(), user));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if (this.users.containsKey(userName)){
            return this.users.get(userName);
        }

        throw new UsernameNotFoundException(String.format("Could Not found %s:", userName));
    }
}

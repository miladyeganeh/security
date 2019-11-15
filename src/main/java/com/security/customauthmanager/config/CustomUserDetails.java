package com.security.customauthmanager.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomUserDetails implements UserDetails {

    private final Set<GrantedAuthority> authorities;
    private final String username;
    private final String password;
    private final boolean isactive;


    public CustomUserDetails(String username, String password, boolean isActive, String ... authorities) {
        this.username = username;
        this.password = password;
        this.isactive = isActive;
        this.authorities = Stream
                .of(authorities)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isactive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isactive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isactive;
    }

    @Override
    public boolean isEnabled() {
        return this.isactive;
    }
}

package com.example.event_ticketing_api.security;

import com.example.event_ticketing_api.entity.Role;
import com.example.event_ticketing_api.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(Role::getName)                       // e.g. "ROLE_ADMIN"
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // modify if you want account expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // modify if you want account locking
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // modify if you want credential expiration
    }

    @Override
    public boolean isEnabled() {
        return true;  // modify if you want to disable users
    }
}
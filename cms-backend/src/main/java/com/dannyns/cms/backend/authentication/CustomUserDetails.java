package com.dannyns.cms.backend.authentication;

import com.dannyns.cms.backend.business.entities.Role;
import com.dannyns.cms.backend.business.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    String username;
    String password;
    Collection roles = Collections.emptySet();

    public CustomUserDetails() {

    }

    public CustomUserDetails(User user) {
        username = user.getUsername();
        password = user.getPassword();
        roles = user.getRoles().stream().map(CustomGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public class CustomGrantedAuthority implements GrantedAuthority {

        private Role role;

        public CustomGrantedAuthority(Role role) {
            this.role = role;
        }

        @Override
        public String getAuthority() {
            return role.getRole();
        }
    }
}

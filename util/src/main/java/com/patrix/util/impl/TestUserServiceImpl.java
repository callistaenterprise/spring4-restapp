package com.patrix.util.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


@Service
@Profile(value = {"test"})
public class TestUserServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return new TestUser();
        }
        throw new UsernameNotFoundException(username);
    }

    public static class TestUser implements UserDetails {

        static Collection<? extends GrantedAuthority> ROLES = Collections.singleton(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        });

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return ROLES;
        }

        @Override
        public String getPassword() {
            return "test";
        }

        @Override
        public String getUsername() {
            return "user";
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
    }
}

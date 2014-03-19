package com.patrix.util.impl;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.patrix.util.UtilService;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@Service
public class UtilServiceImpl implements UtilService {
	
	private static Mapper mapper = new DozerBeanMapper();

    private static final UserDetails NO_USER = new UserDetails() {

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.EMPTY_SET;
        }

        @Override
        public String getPassword() {
            return "";
        }

        @Override
        public String getUsername() {
            return "anonynmous";
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    };

	@Override
	public <T> T map(Object from, Class<T> cls) {
		return (from == null) ? null : mapper.map(from, cls);
	}

	@Override
	public <T> List<T> map(List<?> from, Class<T> cls) {
		if (from == null) {
			return null;
		}
		final List<T> to = new ArrayList<>(from.size());
		for (final Object obj : from) {
			to.add(map(obj, cls));
		}
		return to;
	}

    @Override
    public UserDetails getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!= null) {
            return (UserDetails)authentication.getPrincipal();
        }
        return NO_USER;
    }
}

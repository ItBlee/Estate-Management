package com.itblee.security.utils;

import com.itblee.dto.MyUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class SecurityUtils {

    private SecurityUtils() {
        throw new AssertionError();
    }

    public static MyUserDetail getPrincipal() {
        return (MyUserDetail) getAuthentication().getPrincipal();
    }

    @SuppressWarnings("unchecked")
    public static Set<String> getAuthorities() {
        List<GrantedAuthority> authorities =
                (List<GrantedAuthority>) getAuthentication().getAuthorities();
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean hasRole(String role) {
        String roleCode = role.contains("ROLE_") ? role : "ROLE_" + role;
        return getAuthorities().contains(roleCode);
    }
}

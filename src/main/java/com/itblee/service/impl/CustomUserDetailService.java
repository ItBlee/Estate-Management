package com.itblee.service.impl;

import com.itblee.dto.MyUserDetail;
import com.itblee.dto.UserDTO;
import com.itblee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findOneByUserNameAndStatus(username, 1)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        List<GrantedAuthority> authorities = userDTO.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode()))
                .collect(Collectors.toList());

        UserDetails userDetails = MyUserDetail.builder()
                .username(userDTO.getUserName())
                .password(userDTO.getPassword())
                .disabled(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .authorities(authorities)
                .build();

        MyUserDetail myUserDetail = MyUserDetail.of(userDetails);
        myUserDetail.setId(userDTO.getId());
        myUserDetail.setFullName(userDTO.getFullName());
        return myUserDetail;
    }

}

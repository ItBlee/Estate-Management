package com.itblee.config;

import com.itblee.security.CustomSuccessHandler;
import com.itblee.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static com.itblee.constant.SystemConstant.MANAGER;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable()
                .authorizeRequests()
                        .antMatchers("/admin/user-list",
                                "/admin/user-edit-**",
                                "/admin/user-edit",
                                "/admin/building-edit",
                                "/admin/customer-edit")
                                .hasRole(MANAGER)
                        .antMatchers("/repository/**",
                                "/admin/home",
                                "/admin/building-list",
                                "/admin/customer-list",
                                "/admin/building-edit-**",
                                "/admin/customer-edit-**",
                                "/admin/profile-**")
                                .authenticated()
                        .antMatchers("/login", "/resource/**", "/trang-chu", "/api/**").permitAll()

                .and()
                .formLogin()
                        .loginPage("/login")
                        .usernameParameter("j_username")
                        .passwordParameter("j_password")
                        .permitAll()
                        .loginProcessingUrl("/j_spring_security_check")
                        .successHandler(myAuthenticationSuccessHandler())
                        .failureUrl("/login?incorrectAccount")

                .and()
                .logout()
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")

                .and()
                .exceptionHandling()
                        .accessDeniedPage("/login?accessDenied")

                .and()
                .sessionManagement()
                        .maximumSessions(1)
                        .expiredUrl("/login?sessionTimeout");
    }

}

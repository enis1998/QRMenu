package com.qrmenu.config;

import com.qrmenu.entity.AdminUser;
import com.qrmenu.service.AdminUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AdminUserService userService;

    public SecurityConfig(AdminUserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            AdminUser user = userService.findByUsername(username);
            if (user == null || !user.isEnabled()) {
                throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + username);
            }
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .disabled(!user.isEnabled())
                    .roles(user.getRole().name())
                    .build();
        };
    }

    @Bean
    public DaoAuthenticationProvider authProvider(PasswordEncoder encoder,
                                                  UserDetailsService uds) {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setPasswordEncoder(encoder);
        p.setUserDetailsService(uds);
        return p;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           DaoAuthenticationProvider authProvider) throws Exception {
        http
                .authenticationProvider(authProvider)
                .authorizeHttpRequests(authz -> authz

                        // menü ve statikler:
                        .requestMatchers("/menu/**", "/css/**","/js/**","/images/**", "/admin/login",
                                "/admin/login?error", "/admin/forgotPassword").permitAll()

                        .requestMatchers("/admin/dashboard").hasAnyRole("EMPLOYEE","ADMIN")
                        .requestMatchers("/admin/employee/**").hasAnyRole("EMPLOYEE","ADMIN")

                        // admin-only:
                        //.requestMatchers(HttpMethod.DELETE, "/admin/products/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/menu")
                        .permitAll()
                );
        return http.build();
    }

}


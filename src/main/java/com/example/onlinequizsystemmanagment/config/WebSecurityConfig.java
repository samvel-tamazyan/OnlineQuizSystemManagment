package com.example.onlinequizsystemmanagment.config;

//import com.example.demo_spring_2.handler.CustomAuthenticationFailureHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {


    @Autowired
    @Qualifier("currentUserDetailServiceImpl")
    private UserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/user").permitAll()
                .requestMatchers("/","/login", "/registration", "/static/**", "/activate/*").permitAll()
                .requestMatchers("/user/verify").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/").permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
//                .failureHandler(new CustomAuthenticationFailureHandler())

//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())

                .and()
                .logout()
                .logoutSuccessUrl("/")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID");
//                .and()
//                .rememberMe()
////                .tokenValiditySeconds(2*604800) // 1 week
//                .tokenValiditySeconds(3000) // 1 week
//                .and();
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

//    @Bean
//    public AccessDeniedHandler accessDeniedHandler(){
//        return new CustomAccessDeniedHandler();
//    }
}


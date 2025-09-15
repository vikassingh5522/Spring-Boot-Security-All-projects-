package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    //f i want to add user and password than the acess all this /admin, /about, /contact, /mean, /xyz → Login required  than we use this
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        // Public page (no login)
                        .requestMatchers("/").permitAll()

                        // Require login for these paths
                        .requestMatchers(
                                "/admin",
                                "/about",
                                "/contact",
                                "/mean",
                                "/xyz"
                        ).authenticated()

                        // Deny everything else (optional)
                        .anyRequest().denyAll()
                )

                // Enable form login
                .formLogin(form -> form.permitAll())

                // Enable Basic authentication
                .httpBasic(basic -> {});

        return http.build();





    // if iwant want Not give the user and pass word than / "/contact", "/mean", "/xyz"  . will show
    // only foe /admin /about

//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        // Publicly accessible paths
//                        .requestMatchers("/", "/contact", "/mean", "/xyz").permitAll()
//
//                        // Require authentication for /admin and /about
//                        .requestMatchers("/admin", "/about").authenticated()
//
//                        // Deny everything else (optional safety)
//                        .anyRequest().denyAll()
//                )
//
//                // Enable form login
//                .formLogin(form -> form.permitAll())
//
//                // Enable Basic authentication
//                .httpBasic(basic -> {});
//
//        return http.build();


    }
}

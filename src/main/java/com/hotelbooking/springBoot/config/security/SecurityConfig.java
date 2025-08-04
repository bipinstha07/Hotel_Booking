package com.hotelbooking.springBoot.config.security;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JWTAuthenticationFilter jwtAuthenticationFilter;


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request->
//                        request.requestMatchers("/auth/**","/user/create","/user/booking/create","/user/**","/user/image/**","/admin/room/**","/admin/room/getAll","/api/v1")
//                                .permitAll()
//                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
//                                .requestMatchers("/user/image/**").hasAnyAuthority("CUSTOMER")
//                                .anyRequest()
//                                .authenticated()
//
//                );
//
//        httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        httpSecurity.exceptionHandling(e->e.authenticationEntryPoint(jwtAuthenticationEntryPoint));
//        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return httpSecurity.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->
                        request
                                .requestMatchers(
                                        "/auth/**",
                                        "/admin/room/getAll",
                                        "/admin/room/*/images",
                                        "/admin/room/*/images/*",
                                        "/admin/room/create",

                                        "/user/create",
                                        "/user/rooms",
                                        "/user/booking/create",
                                        "/user/booking/confirm",
                                        "/user/image/**",
                                        "/api/v1",
                                        "/user/{username}",
                                        "/admin/room/booking/getAll"
                                ).permitAll()

                                .requestMatchers(
                                        "/user/booking/*/getAll",
                                        "/user/*/booking",
                                        "/user/{email}/booking",
                                        "/user/booking/confirm",
                                        "/user/booking/get-all",
                                        "/user/booking/*"
                                ).hasAnyAuthority("CUSTOMER")

                                .requestMatchers("/admin/room/booking/update/*").hasAuthority("CUSTOMER")

                                .anyRequest().authenticated()


                );

        System.out.println("HI I AM HERE");
        httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.exceptionHandling(e->e.authenticationEntryPoint(jwtAuthenticationEntryPoint));
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()
//                );
//        return httpSecurity.build();
//    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }



}

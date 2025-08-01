package com.hotelbooking.springBoot.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    private JwtHelper jwtHelper;
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


//        client sends request with header authorization it contains all the header details
        String authorizationHeader = request.getHeader("Authorization");
        logger.trace("Authorization Request");

        String username = null;
        String token = null;

        if(authorizationHeader != null && authorizationHeader.toLowerCase().startsWith("bearer ")){
            try{
//                Get only token by trimming    Extract the token by removing the "Bearer " prefix
                token = authorizationHeader.substring(7);

                // Extract username from the token
                username = jwtHelper.getUserNameFromToken(token);

                // If username is extracted and no authentication is currently set in SecurityContext
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

//                    Load userDetails from username loadByUsername
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if(jwtHelper.isTokenValid(token,userDetails)){

                    }



                }

            }
            catch (){

            }
        }


    }
}

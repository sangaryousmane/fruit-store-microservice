package com.ousmane.authenticationservice.jwt;

import com.ousmane.authenticationservice.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private CustomUserDetailsService userDetailsService;

    public AuthTokenFilter(JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);

//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//        try {
//            String jwt = parseJWT( request, response, filterChain);
//
//            LOGGER.error("AuthTokenFilter | doFilterInternal | jwt: {}", jwt);
//
//            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//                String username = jwtUtils.getUsernameFromJwtToken(jwt);
//                UserDetails userDetails = userDetailsService
//                        .loadUserByUsername(username);
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(userDetails,
//                                null,
//                                userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        } catch (Exception ex) {
//            LOGGER.error("AuthTokenFilter | doFilterInternal | Cannot set user authentication: {}", ex.getMessage());
//        }
//        filterChain.doFilter(request, response);
//    }
//
////    public String parseJWT(@NonNull HttpServletRequest request,
//                           @NonNull HttpServletResponse response,
//                           @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//        final String headerAuth = request.getHeader("Authorization");
//        LOGGER.info("AuthTokenFilter | parseJwt | headerAuth: {}", headerAuth);
//
//        if (headerAuth == null || !headerAuth.startsWith("Bearer ")){
//            filterChain.doFilter(request, response);
//            return null;
//        }
//        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//
//            LOGGER.info("AuthTokenFilter | parseJwt | parseJwt: {}",
//                    headerAuth.substring(7, headerAuth.length()));
//
//            return headerAuth.substring(7, headerAuth.length());
//        }else{
//        return null;
//        }
//    }
//
//

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        try {
            // if the header is null or it doesn't start with the string Bearer then skip
            // Or go to the next filter in the filter chain
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            username = jwtUtils.getUsernameFromJwtToken(jwt);

            // TODO: check if the user isn't authenticated yet and authenticate  otherwise
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtUtils.validateJwtToken(jwt)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }catch (Exception ex){
            LOGGER.error("AuthTokenFilter | doFilterInternal | Cannot set user authentication: {}", ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}

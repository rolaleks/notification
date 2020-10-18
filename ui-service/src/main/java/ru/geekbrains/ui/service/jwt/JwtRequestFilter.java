package ru.geekbrains.ui.service.jwt;


import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.geekbrains.ui.service.bean.Token;
import ru.geekbrains.ui.service.exceptions.JwtAuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final Token token;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (token.getToken() != null && authHeader == null) {
            authHeader = "Bearer " + token.getToken();
        }
        log.info("Authorization token " + authHeader);
        String username = null;
        String jwt = null;
        if (authHeader != null) {
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenProvider.getUsername(jwt);
            } catch (ExpiredJwtException ex) {
                log.info("Token is invalid: " + ex.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "{ msg: The token is expired }");
                return;
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
                    if (authentication != null) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (JwtAuthenticationException e) {
                    SecurityContextHolder.clearContext();
                    response.sendError(e.getHttpStatus().value());
                    throw new JwtAuthenticationException("JWT token is expired or invalid");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}

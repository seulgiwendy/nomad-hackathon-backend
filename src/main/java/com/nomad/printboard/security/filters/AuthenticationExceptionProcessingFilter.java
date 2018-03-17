package com.nomad.printboard.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomad.printboard.exceptions.ExceptionResponseResource;
import com.nomad.printboard.exceptions.security.BaseSecurityException;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationExceptionProcessingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(req, res);
        } catch (BaseSecurityException e) {
            res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            res.setStatus(e.getHttpStatus().value());
            res.getWriter()
                    .write(new ObjectMapper().writeValueAsString(new ExceptionResponseResource(e)));

        }
    }
}

package com.shop.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.common.api.Result;
import com.shop.common.exception.TokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtExceptionHandler extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            filterChain.doFilter(request,response);
        } catch (TokenException e){
            response.setStatus(e.getErrorCodeIfs().getHttpStatusCode());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            String error = objectMapper.writeValueAsString(Result.Error(e.getErrorCodeIfs()));
            response.getWriter().write(error);

        }
    }
}

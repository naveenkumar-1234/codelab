package com.codelab.backend.handler;

import com.codelab.backend.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("ssd");
        response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ObjectMapper mapper = new ObjectMapper();
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .success(false)
                .status(403)
                .message("Access denied")
                .data(null)
                .timestamp(Instant.now().toString())
                .build();
        mapper.writeValue(response.getOutputStream(),apiResponse);
    }
}

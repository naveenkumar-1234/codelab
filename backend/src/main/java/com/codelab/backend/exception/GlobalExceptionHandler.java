package com.codelab.backend.exception;

import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.response.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUsernameNotFound(UsernameNotFoundException ex){
        return ResponseBuilder.error(HttpStatus.UNAUTHORIZED,ex.getMessage(),null);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentials(BadCredentialsException ex){
        return ResponseBuilder.error(HttpStatus.UNAUTHORIZED,"Invalid credential",null);
    }


}

package com.codelab.backend.controller;


import com.codelab.backend.dto.login.LoginRequest;
import com.codelab.backend.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid  LoginRequest request, HttpServletResponse response){
            return authService.login(request,response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        return authService.logout(response);
    }
}

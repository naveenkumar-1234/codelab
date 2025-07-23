package com.codelab.backend.service;

import com.codelab.backend.dto.login.LoginRequest;
import com.codelab.backend.utils.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    public ResponseEntity<?> login(LoginRequest request , HttpServletResponse response){

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateToken(userDetails);


            log.info("User : {}\nToken : {}",userDetails.getUsername(),jwtToken);
            ResponseCookie cookie = ResponseCookie.from("jwt",jwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .maxAge(24*60*60)
                    .path("/")
                    .sameSite("Strict")
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE,cookie.toString());
            return ResponseEntity.ok()
                    .body(Map.of(
                            "email",userDetails.getUsername(),
                            "role",userDetails.getAuthorities().iterator().next().getAuthority()
                    ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    public ResponseEntity<?> logout(HttpServletResponse response){
        ResponseCookie cookie = ResponseCookie.from("jwt","")
                .httpOnly(true)
                .secure(false)
                .maxAge(0)
                .path("/")
                .sameSite("Strict")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
        return ResponseEntity.ok().body("Logout successfully");
    }

}

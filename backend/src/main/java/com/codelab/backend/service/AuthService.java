package com.codelab.backend.service;

import com.codelab.backend.dto.login.LoginRequest;
import com.codelab.backend.dto.user.TeacherResponseDto;
import com.codelab.backend.dto.user.UserResponseDto;
import com.codelab.backend.model.User;
import com.codelab.backend.repo.UserRepository;
import com.codelab.backend.utils.JwtUtils;
import com.codelab.backend.utils.LoginResponseUtils;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final LoginResponseUtils loginResponseUtils;

    public UserResponseDto login(LoginRequest request , HttpServletResponse response){
       try {
           System.out.println("dlksdj");
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())

           );

           System.out.println("Ssf");
           UserDetails userDetails = (UserDetails) authentication.getPrincipal();
           String jwtToken = jwtUtils.generateToken(userDetails);

           log.info("User : {}\nToken : {}", userDetails.getUsername(), jwtToken);
           log.info("Authenticated user: {}", userDetails.getUsername());
           log.info("Authorities: {}", userDetails.getAuthorities());

           ResponseCookie cookie = ResponseCookie.from("jwt", jwtToken)
                   .httpOnly(true)
                   .secure(false)
                   .maxAge(24 * 60 * 60)
                   .path("/")
                   .sameSite("Strict")
                   .build();

           response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

           return loginResponseUtils.getUserResponseDto(userDetails);
       }catch (Exception e){
           throw new UsernameNotFoundException("Invalid email or password");
       }

    }

    public String logout(HttpServletResponse response){
        ResponseCookie cookie = ResponseCookie.from("jwt","")
                .httpOnly(true)
                .secure(false)
                .maxAge(0)
                .path("/")
                .sameSite("Strict")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
        return "logout";
    }






}

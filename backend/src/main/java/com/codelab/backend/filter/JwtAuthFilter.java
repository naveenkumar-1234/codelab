package com.codelab.backend.filter;

import com.codelab.backend.model.User;
import com.codelab.backend.repo.UserRepository;
import com.codelab.backend.service.CustomUserDetailsService;
import com.codelab.backend.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

            log.info("At JwtFilter at once per req(-)");
            String token = getJwtFromCookies(request);
            log.info("Token : {}",token);
            if( token != null && jwtUtils.isValid(token)){
               try{
                   log.info("inside the token not null");
                   String email = jwtUtils.extractEmail(token);
                   String role = jwtUtils.extractRole(token);

                   UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                   if(jwtUtils.isValid(token,userDetails)){
                       UsernamePasswordAuthenticationToken authentication=
                               new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                       System.out.println("SDsd");
                       authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                       SecurityContextHolder.getContext().setAuthentication(authentication);
                       System.out.println(userDetails.getUsername() +" has ROLE " + userDetails.getAuthorities() );
                   }
               }catch (Exception e){
                   log.error("cant set user auth {}", e.getMessage());
               }
            }



            filterChain.doFilter(request,response);
    }

    private String getJwtFromCookies(HttpServletRequest request){
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if("jwt".equals(cookie.getName()))
                    return cookie.getValue();
            }
        }
        return null;
    }
}

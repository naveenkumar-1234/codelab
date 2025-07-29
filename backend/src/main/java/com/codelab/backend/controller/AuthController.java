    package com.codelab.backend.controller;


    import com.codelab.backend.dto.login.LoginRequest;
    import com.codelab.backend.dto.user.UserResponseDto;
    import com.codelab.backend.response.ApiResponse;
    import com.codelab.backend.response.MessageResponse;
    import com.codelab.backend.response.ResponseBuilder;
    import com.codelab.backend.service.AuthService;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.BadCredentialsException;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public class AuthController {

        private final AuthService authService;


        @PostMapping("/login")
        public ResponseEntity<ApiResponse<UserResponseDto>> loginUser(@RequestBody @Valid  LoginRequest request, HttpServletResponse response){
           try{
               return ResponseBuilder.success(
                       authService.login(request,response),
                       "Login successfully"

               );
           }catch (BadCredentialsException | UsernameNotFoundException e){
               return ResponseBuilder.error(HttpStatus.UNAUTHORIZED,e.getMessage(),null);
           }

        }

        @PostMapping("/logout")
        public ResponseEntity<ApiResponse<String>> logout(HttpServletResponse response){
            return ResponseBuilder.success(
                    authService.logout(response),
                    "Logout successfully"
            );
        }

        @GetMapping("/role")
        public ResponseEntity<ApiResponse<MessageResponse>> myRole(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(authentication);

            String role = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("NO_ROLE");
            return ResponseBuilder.success(
                    MessageResponse.builder().message(role).build()
            );
        }
    }

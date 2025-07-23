package com.codelab.backend.controller;

import com.codelab.backend.request.UserCodeRequest;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.service.CompilationService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/compiler")
public class CompilationController {

        private final CompilationService compilationService;

        public CompilationController(CompilationService compilationService){
            this.compilationService = compilationService;
        }

        @GetMapping("/run")
        public ResponseEntity<ApiResponse> javaCompilation(@RequestBody UserCodeRequest userCode){
            System.out.println(userCode);
            ApiResponse res= null;
            try {
                res = compilationService.compileJava(userCode);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity
                    .ok()
                    .body(
                            res
                    );
        }
}
